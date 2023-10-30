package Server;

import Services.IObserver;
import Services.IService;
import Services.ProjectException;
import chat.domain.Angajat;
import chat.domain.Bilet;
import chat.domain.Spectacol;
import chat.repository.AngajatRepo;
import chat.repository.BiletRepo;
import chat.repository.SpectacolRepo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServerImpl implements IService {

    private AngajatRepo AngajatRepo;
    private SpectacolRepo SpectacolRepo;
    private BiletRepo BiletRepo;
    private Map<String, IObserver> loggedClients;

    public ServerImpl(AngajatRepo angajatRepo,SpectacolRepo spectacolRepo, BiletRepo biletRepo){
        this.AngajatRepo = angajatRepo;
        this.SpectacolRepo = spectacolRepo;
        this.BiletRepo = biletRepo;

        loggedClients = new ConcurrentHashMap<>();
    }

    public synchronized Angajat login(String user,String pw, IObserver client) throws ProjectException{
        System.out.println("loginImpl");
        this.AngajatRepo.findAll().forEach(x-> System.out.println(x));
        Iterable<Angajat> lis = this.AngajatRepo.findAll();
        Angajat angajat = null;
        for (Angajat angajat1 : lis){
            if(angajat1.getEmail().equals(user) && angajat1.getParola().equals(pw))
                angajat = angajat1;
        }
        if(angajat != null){
            if(loggedClients.get(angajat.getEmail())!=null)
                throw new ProjectException("User already logged in");
            loggedClients.put(angajat.getEmail(),client);

        }else{
            throw new ProjectException("autentication failed");
        }
        System.out.println("////");
        System.out.println(angajat.getId());
        System.out.println(angajat.getEmail());

        return angajat;
    }

    public synchronized Iterable<Angajat> getAllAngajati()throws ProjectException{
        return AngajatRepo.findAll();
    }
    public synchronized ArrayList<Spectacol> AllSpectacole()throws ProjectException{
        return (ArrayList<Spectacol>) StreamSupport
                .stream(SpectacolRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public  synchronized ArrayList<Spectacol> getALlShows_by_Artist(String Artist)  {
        ArrayList<Spectacol> shows = new ArrayList<Spectacol>();
        SpectacolRepo.findAll().forEach(x->{
            if(Artist.equals(x.getArtist()))
            {
                shows.add(x);
            }
        });

        System.out.println(shows);

        return shows;
    }

    public synchronized  Iterable<Bilet> getAllBilete(){
        return BiletRepo.findAll();
    }


    public synchronized  ArrayList<Spectacol> getSpectacolbyArtist(String Artist) throws ProjectException{
        ArrayList<Spectacol> spectacole = new ArrayList<Spectacol>();
        SpectacolRepo.findAll().forEach(x->{
            if(x.getArtist().equals(Artist))
                spectacole.add(x);
        });

        return spectacole;

    }
    public synchronized void buyTicket(Long id,Bilet bilet)throws ProjectException{
        System.out.println("AAAAAAAAAAA");
        BiletRepo.save(bilet);
        System.out.println("AAAAAAAAAAA");
        Spectacol spectacol = SpectacolRepo.findOne(id);
        spectacol.setLocuriDisponibile(spectacol.getLocuriDisponibile() - bilet.getNrloc_dorite());
        spectacol.setLocuriVandute(spectacol.getLocuriVandute() + bilet.getNrloc_dorite());
        SpectacolRepo.update(spectacol);
        notifyClients(spectacol);

    }

//    public synchronized void saveShow(Spectacol newS)throws ProjectException {
//        System.out.println(newS.getLocuriVandute() + " rares2");
//        SpectacolRepo.save(newS);
//        notifyClients(newS);
//
//    }
    private final int defaultThreadsNo = 5;


    private void notifyClients(Spectacol show) {
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);
        for(String clientLogged : loggedClients.keySet()) {
            IObserver client = loggedClients.get(clientLogged);
            if(client!=null)
            {
                executor.execute(() -> {
                    System.out.println("Notifying [" + clientLogged + "]");
                    System.out.println(client);
                    synchronized (client) {

                        try {
                            System.out.println("Notifying"+ client);
                            client.menuUpdate(show);
                        } catch (ProjectException|RemoteException e) {
                            System.out.println("Error notifying " + e);
                            e.printStackTrace();
                        }

                    }
                    System.out.println("Notified");
                });
            }
        }
        executor.shutdown();
    }
}

