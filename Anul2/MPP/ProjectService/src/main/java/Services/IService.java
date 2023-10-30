package Services;

import chat.domain.Angajat;
import chat.domain.Bilet;
import chat.domain.Spectacol;

import java.util.ArrayList;

public interface IService {
    Angajat login(String user, String pw, IObserver client) throws ProjectException;
    public Iterable<Angajat> getAllAngajati()throws ProjectException;
    public ArrayList<Spectacol> AllSpectacole()throws ProjectException ;
    public ArrayList<Spectacol> getALlShows_by_Artist(String Artist)  throws ProjectException;
    public Iterable<Bilet> getAllBilete() throws ProjectException;
    public ArrayList<Spectacol> getSpectacolbyArtist(String Artist) throws ProjectException;
    public void buyTicket(Long id,Bilet bilet) throws ProjectException;
    //public void saveShow(Spectacol spectacol) throws ProjectException;
}
