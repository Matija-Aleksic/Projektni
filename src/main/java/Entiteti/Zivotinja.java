package Entiteti;

public abstract class Zivotinja {
    private String ime;
    private Prehrana hrana;
    private Staniste staniste;
    private float tezina;

    public Zivotinja(String ime, Prehrana hrana, Staniste staniste, float tezina) {
        this.ime = ime;
        this.hrana = hrana;
        this.staniste = staniste;
        this.tezina = tezina;
    }

    public String getIme() {
        return ime;
    }

    public Prehrana getHrana() {
        return hrana;
    }

    public Staniste getStaniste() {
        return staniste;
    }

    public float getTezina() {
        return tezina;
    }
    public String getTezinaString(){
        return String.valueOf(tezina);
    }
}
