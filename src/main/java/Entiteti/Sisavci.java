package Entiteti;

import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sisavci extends KopneneZivotinje{
    String bojaKrzna;
    public Sisavci(String ime, Prehrana hrana, Staniste staniste, int tezina, int brojNogu,String krzno) {
        super(ime, hrana, staniste, tezina);
        this.bojaKrzna = krzno;
        Log.info("stvoren novi sisavac ");
    }

}
