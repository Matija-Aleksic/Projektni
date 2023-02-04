package Entiteti;

import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sisavci extends Zivotinja{
    String bojaKrzna;

    public Sisavci(String ime, Prehrana hrana, Staniste staniste, int tezina, String bojaKrzna) {
        super(ime, hrana, staniste, tezina);
        this.bojaKrzna = bojaKrzna;
    }
}
