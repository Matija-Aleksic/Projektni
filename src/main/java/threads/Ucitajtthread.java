package threads;
import datoteke.ZapisPromjene;

public class Ucitajtthread implements Runnable{
    @Override
    public void run() {
        ZapisPromjene.loadChangeLogs();
        System.out.println("ucitane promjene");
    }
}
