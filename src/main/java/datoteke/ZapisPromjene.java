package datoteke;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.projektni.PrikazPromjenaController.promjenes;

public class ZapisPromjene {

    private static List<Promjene> svepromjene=new ArrayList<>();
        private static List<Promjene> listaPromjena = new ArrayList<>();
        private static String datoteka="src/main/resources/com/example/projektni/promjene";


        public static void dodajPromjenu(Promjene promjena) {
            listaPromjena.add(promjena);
        }

        public static synchronized void save() {
            try (FileOutputStream fos = new FileOutputStream(datoteka);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                 oos.writeObject(listaPromjena);
            } catch (IOException e) {
                e.printStackTrace();
            }
            listaPromjena.clear();
            System.out.println("saving...");
        }

    public static synchronized void loadChangeLogs() {
        try (FileInputStream fis = new FileInputStream(datoteka);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Promjene> logs = (List<Promjene>) ois.readObject();
            promjenes.addAll(logs);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
