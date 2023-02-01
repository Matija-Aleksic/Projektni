package Entiteti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ribe extends VodeneZivotinje {
    Logger logger = LoggerFactory.getLogger(Ribe.class);
    public Ribe(String ime, Prehrana hrana, Staniste staniste, int tezina, Voda voda) {
        super(ime, hrana, staniste, tezina, voda);
        logger.info("stvorena nova riba");
    }
}
