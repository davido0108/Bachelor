package Network.RpcProtocol;

import Network.Dto.AngajatDTO;
import Network.Dto.DTOUtils;

import Services.IObserver;
import Services.IService;
import Services.ProjectException;
import chat.domain.Angajat;
import chat.domain.Bilet;
import chat.domain.BuyTicket;
import chat.domain.Spectacol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static Network.RpcProtocol.ResponseType.OK;

public class ServicesRpcProxy implements IService {

    private String host;
    private int port;

    private IObserver client;


    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;


    public ServicesRpcProxy(String host, int port )
    {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<Response>();

    }

    @Override
    public Angajat login(String user, String pw, IObserver client) throws ProjectException {
        System.out.println("login");
        initializeConnection();
        Angajat angajat = new Angajat(user, pw);
        AngajatDTO udto = DTOUtils.getDTO(angajat);
        Request req=new Request.Builder().type(RequestType.LOGIN).data(udto).build();

        sendRequest(req);

        Response response = readResponse();
        System.out.println("poate aici");


        if(response.type() == OK){
            this.client = client;

            System.out.println("finish login");
            System.out.println(angajat.getEmail());
            System.out.println(angajat.getParola());
            return angajat;
        }
        System.out.println("login2");
        if(response.type() == ResponseType.ERROR){
            String err = response.data().toString();
            closeConnection();
            throw new ProjectException(err);
        }
        throw new ProjectException();

    }

    @Override
    public Iterable<Angajat> getAllAngajati() throws ProjectException {
        Request cer = new Request.Builder().type(RequestType.GET_Employee).data(null).build();
        sendRequest(cer);
        Response rsp = readResponse();
        if(rsp.type()== ResponseType.ERROR)
        {
            String err = rsp.data().toString();
            throw new ProjectException(err);
        }
        ArrayList<Angajat> empl = (ArrayList<Angajat>) rsp.data();
        return empl;
    }

    @Override
    public ArrayList<Spectacol> AllSpectacole() throws ProjectException {
        Request cer = new Request.Builder().type(RequestType.GET_SHOWS).data(null).build();
        System.out.println(cer);
        sendRequest(cer);

        Response rsp = readResponse();
        if(rsp.type()== ResponseType.ERROR)
        {
            String err = rsp.data().toString();
            throw new ProjectException(err);
        }
        ArrayList<Spectacol> shows = (ArrayList<Spectacol>) rsp.data();
        return shows;
    }

    @Override
    public ArrayList<Spectacol> getALlShows_by_Artist(String Artist) throws ProjectException {
        System.out.println("????");
        System.out.println(Artist);
        System.out.println("????????");
        Request cer = new Request.Builder().type(RequestType.GET_SHOWS_BY_DATE).data(Artist).build();
        System.out.println(cer);
        sendRequest(cer);

        Response rsp = readResponse();
        if(rsp.type()== ResponseType.ERROR)
        {
            String err = rsp.data().toString();
            throw new ProjectException(err);
        }
        ArrayList<Spectacol> shows = (ArrayList<Spectacol>) rsp.data();
        System.out.println("????");
        shows.forEach(x-> System.out.println(x.getArtist()));
        System.out.println("????");
        return shows;
    }

    @Override
    public Iterable<Bilet> getAllBilete() throws ProjectException {
        Request cer = new Request.Builder().type(RequestType.GET_TICKETS).data(null).build();
        sendRequest(cer);
        Response rsp = readResponse();
        if(rsp.type()== ResponseType.ERROR)
        {
            String err = rsp.data().toString();
            throw new ProjectException(err);
        }
        ArrayList<Bilet> tickets = (ArrayList<Bilet>) rsp.data();
        return tickets;
    }

    @Override
    public ArrayList<Spectacol> getSpectacolbyArtist(String Artist) throws ProjectException {
        System.out.println("????");
        System.out.println(Artist);
        System.out.println("????????");
        Request cer = new Request.Builder().type(RequestType.GET_SHOWS_BY_DATE).data(Artist).build();
        System.out.println(cer);
        sendRequest(cer);

        Response rsp = readResponse();
        if(rsp.type()== ResponseType.ERROR)
        {
            String err = rsp.data().toString();
            throw new ProjectException(err);
        }
        ArrayList<Spectacol> shows = (ArrayList<Spectacol>) rsp.data();
        System.out.println("????");
        shows.forEach(x-> System.out.println(x.getArtist()));
        System.out.println("????");
        return shows;
    }

    @Override
    public void buyTicket(Long id, Bilet bilet) throws ProjectException {
        System.out.println("ceauasdadwadasdawdawdadsd");
        BuyTicket buyTicket = new BuyTicket(id,bilet);
        Request cer = new Request.Builder().type(RequestType.SAVE_TICKET).data(buyTicket).build();
        sendRequest(cer);
        Response rsp = readResponse();
        if(rsp.type()== ResponseType.ERROR)
        {
            String err = rsp.data().toString();
            throw new ProjectException(err);
        }
    }

//    @Override
//    public void saveShow(Spectacol spectacol) throws ProjectException {
//        Request cer = new Request.Builder().type(RequestType.SAVE_SHOW).data(spectacol).build();
//        sendRequest(cer);
//        Response rsp = readResponse();
//        if(rsp.type()== ResponseType.ERROR)
//        {
//            String err = rsp.data().toString();
//            throw new ProjectException(err);
//        }
//    }

    private void initializeConnection() throws ProjectException{
        try {
            connection=new Socket(host,port);
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }
    private void sendRequest(Request request)throws ProjectException {
        try {
            System.out.println(request + "  geor");
            output.writeObject(request);
            System.out.println("cccc");
            output.flush();
            System.out.println("cccc");
        } catch (IOException e) {
            throw new ProjectException("Error sending object "+e);
        }

    }

    private Response readResponse() throws ProjectException {
        System.out.println("da");
        Response response=null;
        try{
            System.out.println(qresponses);

            response=qresponses.take();
            System.out.println("aici");
            System.out.println(response);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleUpdate(Response response) throws RemoteException {

        Spectacol show = (Spectacol) response.data();
        System.out.println(show);
        System.out.println("menu Update #########");
        client.menuUpdate(show);
        System.out.println("menu Update #########");
//        } catch (ProjectException e) {
//            e.printStackTrace();
//        }


//        Request cer = new Request.Builder().type(RequestType.SAVE_SHOW).data(newS).build();
//        sendRequest(cer);
//        Response rsp = readResponse();
//        if(rsp.type()== ResponseType.ERROR)
//        {
//            String err = rsp.data().toString();
//            throw new ProjectException(err);
//        }


    }


    private boolean isUpdate(Response response){
        return response.type()== ResponseType.UPDATE ;
    }


    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Object response=input.readObject();
                    System.out.println("response received "+response);
                    if (isUpdate((Response)response)){
                        handleUpdate((Response)response);
                    }else{

                        try {
                            qresponses.put((Response)response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }
}
