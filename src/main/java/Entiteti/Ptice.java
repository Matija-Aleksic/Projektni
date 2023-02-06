package Entiteti;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ptice extends Zivotinja{

public String sirinaKrila;

    public Ptice(String ime, Prehrana hrana, Staniste staniste, float tezina, String sirinaKrila) {
        super(ime, hrana, staniste, tezina);
        this.sirinaKrila = sirinaKrila;
    }

    public String getSirinaKrila() {
        return sirinaKrila;
    }
}
