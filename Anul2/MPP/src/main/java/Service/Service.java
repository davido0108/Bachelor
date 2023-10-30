package Service;

import domain.Angajat;
import domain.Bilet;
import domain.Spectacol;
import repository.Repository;
import repository.dbrepos.AngajatDBRepo;

import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service {
    Repository<Long, Angajat> repoAngajat;
    Repository<Long, Bilet> repoBilet;
    Repository<Long, Spectacol> repoSpectacol;

    Properties props = new Properties();
    AngajatDBRepo angajatDBRepo = new AngajatDBRepo(props);

    public Service(Repository<Long, Angajat> repoAngajat,Repository<Long, Bilet> repoBilet,Repository<Long, Spectacol> repoSpectacol)
    {
        this.repoAngajat = repoAngajat;
        this.repoBilet = repoBilet;
        this.repoSpectacol = repoSpectacol;
    }

    public Iterable<Angajat> getAllAngajati(){
        return repoAngajat.findAll();
    }
    public void addAngajat(Angajat angajat){
        repoAngajat.save(angajat);
        System.out.println("Angajat adaugat");
    }

    public Iterable<Bilet> getAllBilete(){
        return repoBilet.findAll();
    }
    public ArrayList<Spectacol> getAllSpectacole(){
        return (ArrayList<Spectacol>) StreamSupport.stream(repoSpectacol.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public ArrayList<Spectacol> getSpectacolbyArtist(String Artist){
        ArrayList<Spectacol> spectacole = new ArrayList<Spectacol>();
        repoSpectacol.findAll().forEach(x->{
            if(x.getArtist().equals(Artist))
                spectacole.add(x);
        });

        return spectacole;

    }

    public void buyTicket(Long id,Bilet bilet){
        repoBilet.save(bilet);

        Spectacol spectacol = repoSpectacol.findOne(id);
        spectacol.setNrloc_d(spectacol.getLocuriDisponibile() - bilet.getNrloc_dorite());
        spectacol.setNrloc_v(spectacol.getLocuriVandute() + bilet.getNrloc_dorite());
        repoSpectacol.update(spectacol);

    }

}
