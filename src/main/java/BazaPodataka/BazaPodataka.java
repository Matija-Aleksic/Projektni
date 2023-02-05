package BazaPodataka;
import Entiteti.Prehrana;
import Entiteti.Sisavci;
import Entiteti.Staniste;
import javafx.scene.control.Alert;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class BazaPodataka {

    //-1 nije uspjesan login
    //0 basic user
    //1 admin user
    public static int isAdmin=-1;
    private static final String DATABASE_FILE = "src/main/resources/Properties";
    public static Connection spajanjeNaBazu() throws SQLException, IOException {
        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));
        String urlBazePodataka = svojstva.getProperty("url");
        String korisnickoIme = svojstva.getProperty("username");
        String lozinka = svojstva.getProperty("password");
        Connection veza = DriverManager.getConnection(urlBazePodataka,
                korisnickoIme,lozinka);
        return veza;
    }

//    public static void ProvjeriDaliPostoji(String ime, String sifra) throws SQLException, IOException {
//        Connection connection = spajanjeNaBazu();
//        PreparedStatement statement = connection.prepareStatement("SELECT role FROM users WHERE username = ? and PASSWORD = ?");
//        statement.setString(1,ime);
//        statement.setString(2,sifra);
//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            String role = rs.getString("role");
//            if (role.equals("admin")) {
//                isAdmin = 1;
//            } else {
//                isAdmin= 0;
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("user nije pronaden");
//            alert.showAndWait();
//        }
//    }

    public static List<Sisavci> dohvatiSisavce(String ime, Prehrana hrana, Staniste staniste, float tezina, String bojaKrzna) throws SQLException, IOException {
        List<Sisavci> lista = new ArrayList<>();
        Connection veza = spajanjeNaBazu();
        String upit ="SELECT * FROM SISAVCI WHERE 1=1 ";
        if (ime.contains("null")==false){
            upit += "AND IME = '" +ime+"' ";
        }
        if (Optional.ofNullable(hrana).isPresent()) {
            upit+="AND PREHRANA = '"+hrana.toString()+ "'";
        }
        if (Optional.ofNullable(staniste).isPresent()) {
            upit+="AND STANISTE = '"+staniste.toString()+ "'";
        }
        if (tezina!=0) {
            upit+="AND TEZINA = "+ tezina ;
        }
        if (bojaKrzna.contains("null")==false) {
            upit+="AND BOJAKRZNA = '"+bojaKrzna+ "'";
        }
        System.out.println(upit);
        Statement a= veza.createStatement();
        ResultSet resultSet = a.executeQuery(upit);
        while (resultSet.next()) {
        String imena =resultSet.getString("ime");
        Prehrana prehrana= Prehrana.valueOf(resultSet.getString("prehrana"));
        Staniste staniste1= Staniste.valueOf(resultSet.getString("staniste"));
        float tezina1= Float.parseFloat(resultSet.getString("tezina"));
        String bojakrzna1= resultSet.getString("bojakrzna");
        Sisavci noviSisavac = new Sisavci(imena, prehrana, staniste1, tezina1, bojakrzna1);
        lista.add(noviSisavac);
        }
        return lista;
    }



    public static void obrisi(Sisavci sisavci) throws SQLException, IOException {
        //todo napravit ak ne postoji zivotinja i spremanje promjene u binarnu datoteku

        Connection veza= spajanjeNaBazu();
        PreparedStatement statement = veza.prepareStatement("DELETE FROM SISAVCI WHERE IME = ?");
        statement.setString(1, sisavci.getIme());
        statement.execute();


    }

    public static void editSisavac(Sisavci stari, Sisavci novi) throws SQLException, IOException {
        Connection veza = spajanjeNaBazu();
        PreparedStatement preparedStatement = veza.prepareStatement("UPDATE SISAVCI SET IME = ?, PREHRANA = ? ,STANISTE = ? ,TEZINA = ?, BOJAKRZNA = ? WHERE IME = ?");
        preparedStatement.setString(1, novi.getIme());
        preparedStatement.setString(2,novi.getHrana().toString());
        preparedStatement.setString(3,novi.getStaniste().toString());
        preparedStatement.setString(4,novi.getTezinaString());
        preparedStatement.setString(5, novi.getBojaKrzna());
        preparedStatement.setString(6,stari.getIme());
        preparedStatement.execute();
        System.out.println("updated sisavac");
    }
}
