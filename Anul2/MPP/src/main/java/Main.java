import Service.Service;
import domain.Angajat;
import domain.Bilet;
import repository.Repository;
import repository.dbrepos.AngajatDBRepo;
import repository.dbrepos.BiletDBRepo;
import repository.dbrepos.SpectacolDBRepo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        BiletDBRepo biletDBRepo = new BiletDBRepo(props);
        AngajatDBRepo angajatDBRepo = new AngajatDBRepo(props);
        SpectacolDBRepo spectacolDBRepo = new SpectacolDBRepo(props);
        Service srv;

        Repository<Long, Angajat> angajatRepository = new AngajatDBRepo(props);
        Repository<Long, Bilet> biletRepository = new BiletDBRepo(props);
        srv = new Service(angajatRepository, biletRepository, spectacolDBRepo);

       // srv.addAngajat(new Angajat("andreisefu@email.com","33333"));
        //angajatDBRepo.delete(Long.parseLong("3"));

        srv.getAllAngajati().forEach(x->{
            System.out.println("Angajatul cu id-ul: " + x.getId() + x);
        });
        srv.getAllBilete().forEach(x->{
            System.out.println(x);
        });
        srv.getAllSpectacole().forEach(x->{
            System.out.println(x);
        });



        srv.getSpectacolbyArtist("Irina Rimes").forEach(System.out::println);

        System.out.println("Cumpara bilete...");
        //System.out.println("Introdu nume: ");

        //String numeB = scanner.nextLine();
       // System.out.println("Introdu nr. bilete dorite: ");

        //int nrbilete = scanner.nextInt();
        int nrbilete = 15;

        String numeB = "Andrei Rares";
        Bilet bilet = new Bilet(numeB, nrbilete);

        //System.out.println("Introdu id-ul spectacolului");
        //Long idSpectacol = scanner.nextLong();
        Long idSpectacol = Long.parseLong("2");

        srv.buyTicket(idSpectacol,bilet);


        MainFX gui = new MainFX();
        gui.main(args);


    }
}
