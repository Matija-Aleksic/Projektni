package Entiteti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sisavci extends KopneneZivotinje{
    Logger logger = LoggerFactory.getLogger(Sisavci.class);
    public Sisavci(String ime, Prehrana hrana, Staniste staniste, int tezina, int brojNogu) {
        super(ime, hrana, staniste, tezina, brojNogu);
        logger.info("stvoren novi sisavac ");
    }
}
