package Entiteti;

import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sisavci extends KopneneZivotinje{

    public Sisavci(String ime, Prehrana hrana, Staniste staniste, int tezina, int brojNogu) {
        super(ime, hrana, staniste, tezina, brojNogu);
        Log.info("stvoren novi sisavac ");
    }
}
