package Entiteti;

public class Builder {
    public Voda voda;
    public String bojaKrzna;
    String ime;
    Prehrana hrana;
    Staniste staniste;
    float tezina;
    String sirinaKrila;

    public Builder withIme(String ime) {
        this.ime = ime;
        return this;
    }

    public Builder withPrehrana(Prehrana hrana) {
        this.hrana = hrana;
        return this;
    }

    public Builder withStaniste(Staniste staniste) {
        this.staniste = staniste;
        return this;
    }

    public Builder withTezina(float tezina) {
        this.tezina = tezina;
        return this;
    }

    public Builder withSirinaKrila(String sirinaKrila) {
        this.sirinaKrila = sirinaKrila;
        return this;
    }

    public Builder withBojaKrzna(String krzno) {
        this.bojaKrzna = krzno;
        return this;
    }

    public Builder withVoda(Voda voda) {
        this.voda = voda;
        return this;
    }

    public Ptice buildPtica() {
        return new Ptice(this);
    }

    public Sisavci buildSisavac() {
        return new Sisavci(this);
    }

    public Ribe buildRiba() {
        return new Ribe(this);
    }

}

