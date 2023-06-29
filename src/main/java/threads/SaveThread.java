package threads;

import datoteke.ZapisPromjene;

public class SaveThread implements Runnable {
    @Override
    public void run() {
        ZapisPromjene.save();
        System.out.println("spremljene promjene");
    }
}
