package com.example.projektni;

import java.util.List;

public class templatei<T,K> {
private T prvi;
private K drugi;

    public templatei(T prvi, K drugi) {
        this.prvi = prvi;
        this.drugi = drugi;
    }
    public templatei() {

    }

    public T getPrvi() {
        return prvi;
    }

    public void setPrvi(T prvi) {
        this.prvi = prvi;
    }

    public K getDrugi() {
        return drugi;
    }

    public void setDrugi(K drugi) {
        this.drugi = drugi;
    }
}
