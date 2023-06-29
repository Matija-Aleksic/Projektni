package datoteke;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.projektni.PrikazPromjenaController.promjenes;

public class ZapisPromjene {

    private static final List<Promjene> svepromjene = new ArrayList<>();
    private static final List<Promjene> listaPromjena = new ArrayList<>();
    private static final String datoteka = "src/main/resources/com/example/projektni/promjene";

    public static void dodajPromjenu(Promjene promjena) {
        listaPromjena.add(promjena);
    }

    public static synchronized void save() {
        List<Promjene> postojecePromjene = loadChangeLogs();

        postojecePromjene.addAll(listaPromjena);


        try (FileOutputStream fos = new FileOutputStream(datoteka);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(postojecePromjene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listaPromjena.clear();

        promjenes.clear();
        promjenes.addAll(loadChangeLogs());
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

