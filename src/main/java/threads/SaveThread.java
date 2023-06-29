package threads;

import datoteke.ZapisPromjene;

public class SaveThread implements Runnable {
    private String ime;

    public SaveThread(String a) {
        this.ime = a;
    }

    @Override
    public void run() {
        ZapisPromjene.save();
        System.out.println("spremljene promjene");
    }
}
