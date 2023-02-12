package datoteke;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
public class ZapisPromjene {


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
            System.out.println("saving...");
        }
//todo thread koji svakih x sekundi zove save()
}
