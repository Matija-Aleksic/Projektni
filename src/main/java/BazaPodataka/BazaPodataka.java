package BazaPodataka;
import Entiteti.*;
import javafx.scene.control.Alert;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.example.projektni.LoginFormController.upozorenje;

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

    public static List<Ptice> dohvatiptice(String ime, Prehrana hrana, Staniste staniste, float tezina, int rasponKrila) throws SQLException, IOException {
        List<Ptice> lista = new ArrayList<>();
        Connection veza = spajanjeNaBazu();
        String upit ="SELECT * FROM ptice WHERE 1=1 ";
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
        if (rasponKrila!=0) {
            upit+="AND RASPONKRILA = '"+rasponKrila+ "'";
        }
        System.out.println(upit);
        Statement a= veza.createStatement();
        ResultSet resultSet = a.executeQuery(upit);
        while (resultSet.next()) {
            String imena =resultSet.getString("ime");
            Prehrana prehrana= Prehrana.valueOf(resultSet.getString("prehrana"));
            Staniste staniste1= Staniste.valueOf(resultSet.getString("staniste"));
            float tezina1= Float.parseFloat(resultSet.getString("tezina"));
            String rasponkrila= resultSet.getString("rasponkrila");
            Ptice novaPtica = new Ptice(imena, prehrana, staniste1, (int) tezina1, rasponkrila);
            lista.add(novaPtica);
        }
        return lista;
    }
    public static void dodajPtice(Ptice ptice) throws SQLException, IOException {
        Connection veza = spajanjeNaBazu();
        PreparedStatement statement = veza.prepareStatement("INSERT INTO ptice (ime, prehrana, staniste, tezina, rasponKrila) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, ptice.getIme());
        statement.setString(2, ptice.getHrana().toString());
        statement.setString(3, ptice.getStaniste().toString());
        statement.setFloat(4, ptice.getTezina());
        statement.setString(5, ptice.getSirinaKrila());
        statement.executeUpdate();
        System.out.println("ptica dodana u bazu");
        upozorenje("uspjesno");
    }

    public static void obrisiptice(Ptice ptice) throws SQLException, IOException {

            //todo napravit ak ne postoji zivotinja i spremanje promjene u binarnu datoteku
            Connection veza= spajanjeNaBazu();
            PreparedStatement statement = veza.prepareStatement("DELETE FROM Ptice WHERE IME = ?");
            statement.setString(1, ptice.getIme());
            statement.execute();

    }

    public static void editPtica(Ptice stari, Ptice novi) throws SQLException, IOException {

            Connection veza = spajanjeNaBazu();
            PreparedStatement preparedStatement = veza.prepareStatement("UPDATE PTICE SET IME = ?, PREHRANA = ? ,STANISTE = ? ,TEZINA = ?, RASPONKRILA = ? WHERE IME = ?");
            preparedStatement.setString(1, novi.getIme());
            preparedStatement.setString(2,novi.getHrana().toString());
            preparedStatement.setString(3,novi.getStaniste().toString());
            preparedStatement.setString(4,novi.getTezinaString());
            preparedStatement.setString(5, novi.getSirinaKrila());
            preparedStatement.setString(6,stari.getIme());
            preparedStatement.execute();
            System.out.println("updated ptica");


    }

    public static List<Ribe> dohvatiRibe(String ime, Prehrana hrana, Staniste staniste, float tezina, Voda voda) throws SQLException, IOException {
        List<Ribe> lista = new ArrayList<>();
        Connection veza = spajanjeNaBazu();
        String upit ="SELECT * FROM RIBE WHERE 1=1 ";
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
        if (Optional.ofNullable(voda).isPresent()) {
            upit+="AND VODA = '"+voda.toString()+ "'";
        }
        System.out.println(upit);
        Statement a= veza.createStatement();
        ResultSet resultSet = a.executeQuery(upit);
        while (resultSet.next()) {
            Staniste staniste1 = Staniste.RijekaiJezera;
            String imena =resultSet.getString("ime");
            Prehrana prehrana= Prehrana.valueOf(resultSet.getString("prehrana"));

            float tezina1= Float.parseFloat(resultSet.getString("tezina"));
            Voda voda1= Voda.valueOf(resultSet.getString("Voda"));
            if (voda1==Voda.Morsko){
                 staniste1= Staniste.More;
            }

            Ribe novariba = new Ribe(imena, prehrana, staniste1, tezina1, voda1);
            lista.add(novariba);
        }
        return lista;
    }

    public static void obrisiRibu(Ribe riba) throws SQLException, IOException {

        Connection veza= spajanjeNaBazu();
        PreparedStatement statement = veza.prepareStatement("DELETE FROM RIBE WHERE IME = ?");
        statement.setString(1, riba.getIme());
        statement.execute();

    }

    public static void editRiba(Ribe stari, Ribe novi) throws SQLException, IOException {

        Connection veza = spajanjeNaBazu();
        PreparedStatement preparedStatement = veza.prepareStatement("UPDATE RIBE SET IME = ?, PREHRANA = ? ,STANISTE = ? ,TEZINA = ?, VODA = ? WHERE IME = ?");
        preparedStatement.setString(1, novi.getIme());
        preparedStatement.setString(2,novi.getHrana().toString());
        preparedStatement.setString(3,novi.getStaniste().toString());
        preparedStatement.setString(4,novi.getTezinaString());
        preparedStatement.setString(5, novi.getVoda().toString());
        preparedStatement.setString(6,stari.getIme());
        preparedStatement.execute();
        System.out.println("updated riba");


    }

    public static void dodajRibe(Ribe tempRiba) throws SQLException, IOException {
        Connection veza = spajanjeNaBazu();
        PreparedStatement statement = veza.prepareStatement("INSERT INTO ribe (ime, prehrana, staniste, tezina, voda) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1,tempRiba.getIme());
        statement.setString(2,tempRiba.getHrana().toString());
        statement.setString(3,tempRiba.getStaniste().toString());
        statement.setFloat(4, tempRiba.getTezina());
        statement.setString(5,tempRiba.getVoda().toString());
        statement.executeUpdate();
        System.out.println("riba dodana u bazu");
        upozorenje("uspjesno");
    }

    public static void dodajSisavca(Sisavci tempSisavac) throws SQLException, IOException {
        Connection veza = spajanjeNaBazu();
        PreparedStatement statement = veza.prepareStatement("INSERT INTO sisavci (ime, prehrana, staniste, tezina, bojakrzna) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1,tempSisavac.getIme());
        statement.setString(2,tempSisavac.getHrana().toString());
        statement.setString(3,tempSisavac.getStaniste().toString());
        statement.setFloat(4, tempSisavac.getTezina());
        statement.setString(5,tempSisavac.getBojaKrzna());
        statement.executeUpdate();
        System.out.println("Sisavac dodan u bazu");
        upozorenje("uspjesno");

    }
}
