package chat.domain;

public class Angajat extends Entity<Long>{
    String email;
    String parola;

    public Angajat(String email, String parola) {
        this.email = email;
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return " are email-ul : " + email + ' ' +
                "si  parola: " + parola + ' ' ;
    }
}
