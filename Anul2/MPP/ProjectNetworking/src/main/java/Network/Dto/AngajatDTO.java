package Network.Dto;

import java.io.Serializable;

public class AngajatDTO implements Serializable {

    private String email;
    private String parola;

    public AngajatDTO(String email, String parola) {
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
