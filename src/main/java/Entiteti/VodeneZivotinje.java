package Entiteti;

public abstract class VodeneZivotinje {
    String ime;
    Prehrana hrana;
    Staniste staniste;
    int tezina;
    Voda voda;

    public VodeneZivotinje(String ime, Prehrana hrana, Staniste staniste, int tezina, Voda voda) {
        this.ime = ime;
        this.hrana = hrana;
        this.staniste = staniste;
        this.tezina = tezina;
        this.voda = voda;
    }
}
