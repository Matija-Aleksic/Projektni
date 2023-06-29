package Entiteti;

public class Ptice extends Zivotinja {

    public String sirinaKrila;

    public Ptice(String ime, Prehrana hrana, Staniste staniste, float tezina, String sirinaKrila) {
        super(ime, hrana, staniste, tezina);
        this.sirinaKrila = sirinaKrila;
    }


    public String getSirinaKrila() {
        return sirinaKrila;
    }

    public Ptice(Builder builder) {
        super(builder.ime, builder.hrana, builder.staniste, builder.tezina);
        this.sirinaKrila = builder.sirinaKrila;
    }


}

