
    package Entiteti;

    public abstract class LeteceZivotinje {
        private String ime;
        private Prehrana hrana;
        private Staniste staniste;
        private int tezina;
        private int rasponKrila;

        public LeteceZivotinje(String ime, Prehrana hrana, Staniste staniste, int tezina, int rasponKrila) {
            this.ime = ime;
            this.hrana = hrana;
            this.staniste = staniste;
            this.tezina = tezina;
            this.rasponKrila = rasponKrila;
        }
    }


