package Entiteti;


import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kukac extends Zivotinja {
    private int brojOciju;

    public Kukac(String ime, Prehrana hrana, Staniste staniste, int tezina, int brojOciju) {
        super(ime, hrana, staniste, tezina);
        this.brojOciju = brojOciju;
    }
}


