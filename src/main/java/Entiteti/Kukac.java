    package Entiteti;


import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    public class Kukac extends KopneneZivotinje{
        private int brojOciju;

        public Kukac(String ime, Prehrana hrana, Staniste staniste, int tezina, int brojNogu, int brojOciju) {
            super(ime, hrana, staniste, tezina, brojNogu);
            this.brojOciju = brojOciju;
            Log.info("Stvoren novi kukac " + ime);
        }
    }


