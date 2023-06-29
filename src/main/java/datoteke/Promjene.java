package datoteke;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Promjene implements Serializable {
    String user;
    LocalDateTime vrijeme;
    String sqlupit;

    public Promjene(String user, LocalDateTime vrijeme, String sqlupit) {
        this.user = user;
        this.vrijeme = vrijeme;
        this.sqlupit = sqlupit;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(LocalDateTime vrijeme) {
        this.vrijeme = vrijeme;
    }

    public String getSqlupit() {
        return sqlupit;
    }

    public void setSqlupit(String sqlupit) {
        this.sqlupit = sqlupit;
    }
}
