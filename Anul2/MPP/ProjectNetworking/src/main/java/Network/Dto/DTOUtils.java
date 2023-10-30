package Network.Dto;

import chat.domain.Angajat;

public class DTOUtils {
    public static Angajat getFromDTO(AngajatDTO usdto){
        String email = usdto.getEmail();
        String parola = usdto.getParola();

        Angajat em =new Angajat(email,parola);

        return em;

    }

    public static AngajatDTO getDTO(Angajat user){
        String email = user.getEmail();
        String parola = user.getParola();

        return new AngajatDTO(email, parola);
    }



}
