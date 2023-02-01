    package Entiteti;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    public class Kukac extends KopneneZivotinje{
        Logger logger = LoggerFactory.getLogger(Entiteti.Kukac.class);
        private int brojOciju;

        public Kukac(String ime, Prehrana hrana, Staniste staniste, int tezina, int brojNogu, int brojOciju) {
            super(ime, hrana, staniste, tezina, brojNogu);
            this.brojOciju = brojOciju;
            logger.info("Stvoren novi kukac");
        }
    }


