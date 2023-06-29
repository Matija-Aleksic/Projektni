package datoteke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.projektni.PrikazPromjenaController.promjenes;

public class ZapisPromjene {

    private static List<Promjene> svepromjene = new ArrayList<>();
    private static List<Promjene> listaPromjena = new ArrayList<>();
    private static String datoteka = "src/main/resources/com/example/projektni/promjene";

    public static void dodajPromjenu(Promjene promjena) {
        listaPromjena.add(promjena);
    }

    public static synchronized void save() {
        List<Promjene> postojecePromjene = loadChangeLogs(); // Učitaj postojeće promjene iz datoteke

        postojecePromjene.addAll(listaPromjena); // Dodaj nove promjene na postojeće
        System.out.println("Sadržaj liste postojecePromjene nakon dodavanja:");
        for (Promjene promjena : postojecePromjene) {
            System.out.println(promjena);
        }

        try (FileOutputStream fos = new FileOutputStream(datoteka);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(postojecePromjene); // Spremi sve promjene u datoteku
        } catch (IOException e) {
            e.printStackTrace();
        }
        listaPromjena.clear();
        System.out.println("saving...");
    }

    public static synchronized List<Promjene> loadChangeLogs() {
        List<Promjene> logs = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(datoteka);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            logs = (List<Promjene>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return logs;
    }
}

