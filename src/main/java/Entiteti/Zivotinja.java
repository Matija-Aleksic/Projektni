package Entiteti;

public abstract class Zivotinja {
    private String ime;
    private Prehrana hrana;
    private Staniste staniste;
    private int tezina;

    public Zivotinja(String ime, Prehrana hrana, Staniste staniste, int tezina) {
        this.ime = ime;
        this.hrana = hrana;
        this.staniste = staniste;
        this.tezina = tezina;
    }
}
