package Entiteti;

public abstract class KopneneZivotinje {
    private String ime;
    private Prehrana hrana;
    private Staniste staniste;
    private int tezina;
    private int brojNogu;

    public KopneneZivotinje(String ime, Prehrana hrana, Staniste staniste, int tezina, int brojNogu) {
        this.ime = ime;
        this.hrana = hrana;
        this.staniste = staniste;
        this.tezina = tezina;
        this.brojNogu = brojNogu;
    }
}
