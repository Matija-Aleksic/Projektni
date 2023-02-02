package BazaPodataka;
import javafx.scene.control.Alert;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class BazaPodataka {
    private static boolean isAdmin;
    private static final String DATABASE_FILE = "src/main/resources/Properties";
    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));
        String urlBazePodataka = svojstva.getProperty("url");
        String korisnickoIme = svojstva.getProperty("username");
        String lozinka = svojstva.getProperty("password");
        Connection veza = DriverManager.getConnection(urlBazePodataka,
                korisnickoIme,lozinka);
        return veza;
    }

    public static void ProvjeriDaliPostoji(String ime, String sifra) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("SELECT role FROM users WHERE username = ? and PASSWORD = ?");
        statement.setString(1,ime);
        statement.setString(2,sifra);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String role = rs.getString("role");
            if (role.equals("admin")) {
                isAdmin = TRUE;
            } else {
                isAdmin= FALSE;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("user nije pronaden");
            alert.showAndWait();
        }
    }
}
