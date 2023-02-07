package Entiteti;

import Loger.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ribe extends Zivotinja {
 Voda voda;

    public Ribe(String ime, Prehrana hrana, Staniste staniste, float tezina, Voda voda) {
        super(ime, hrana, staniste, tezina);
        this.voda = voda;
    }

    public Voda getVoda() {
        return voda;
    }
    Ribe(Builder builder) {
        super(builder.ime, builder.hrana,builder.staniste,builder.tezina);
        this.voda = builder.voda;
    }
}
