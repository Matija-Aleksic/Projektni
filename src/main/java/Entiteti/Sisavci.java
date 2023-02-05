package Entiteti;

import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sisavci extends Zivotinja{

    String ime;
    Prehrana hrana;
    Staniste staniste;
    float tezina;
    String bojaKrzna;

    public Sisavci(String ime, Prehrana hrana, Staniste staniste, float tezina, String bojaKrzna) {
        super(ime, hrana, staniste, tezina);
        this.ime = ime;
        this.hrana = hrana;
        this.staniste = staniste;
        this.tezina = tezina;
        this.bojaKrzna = bojaKrzna;
    }

    public String getBojaKrzna() {
        return bojaKrzna;
    }
    public void setBojaKrzna(String bojaKrzna) {
        this.bojaKrzna = bojaKrzna;
    }

}
