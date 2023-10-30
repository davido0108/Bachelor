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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;

public class ClientRpcReflectionWorker implements Runnable, IObserver {
    private IService server;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientRpcReflectionWorker(IService srv, Socket connection){
        this.server = srv;
        this.connection = connection;

        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Response okResponse=new Response.Builder().type(ResponseType.OK).build();

    @Override
    public void run() {
        while(connected){
            try {
                Object request=input.readObject();
                Response response=handleRequest((Request)request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }
    private Response handleRequest(Request request){

        System.out.println(request);
        System.out.println("SSSS");
        Response response=null;
        String handlerName="handle"+(request).type();
        System.out.println(handlerName + "   george");
        System.out.println("HandlerName "+handlerName);
        try {
            Method method=this.getClass().getDeclaredMethod(handlerName, Request.class);
            response=(Response)method.invoke(this,request);
            System.out.println("Method "+handlerName+ " invoked");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("Sending response " + response);
        synchronized (output) {
            output.writeObject(response);
            output.flush();
        }

    }

    @Override
    public void menuUpdate(Spectacol spectacol) {
        Response response = new Response.Builder().type(ResponseType.UPDATE).data(spectacol).build();
        try{
            sendResponse(response);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private Response handleLOGIN(Request request){
        System.out.println("sadsadawd");
        System.out.println("Login request ..."+request.type());
        AngajatDTO udto=(AngajatDTO) request.data();
        System.out.println(udto);
        Angajat angajat = DTOUtils.getFromDTO(udto);
        try {
            server.login(angajat.getEmail(),angajat.getParola(),this);
            //return okResponse;
            return new Response.Builder().type(ResponseType.OK).data(angajat).build();
        } catch (ProjectException e) {
            connected=false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }


    private Response handleGET_SHOWS(Request request){
        System.out.println("aci");
        try{

            ArrayList<Spectacol> shows = server.AllSpectacole();
            return new Response.Builder().type(ResponseType.GET_SHOWS).data(shows).build();
        }
        catch (ProjectException e) {
            System.out.println("ss");
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }
    private Response handleGET_Employee(Request request){
        System.out.println("aci");
        try{

            ArrayList<Angajat> em = (ArrayList<Angajat>) server.getAllAngajati();
            return new Response.Builder().type(ResponseType.GET_Employee).data(em).build();
        }
        catch (ProjectException e) {
            System.out.println("ss");
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }
    private Response handleGET_SHOWS_BY_DATE(Request request){
        System.out.println("aci");
        try{
            String dat = (String) request.data();
            System.out.println(dat);
            ArrayList<Spectacol> em =  server.getALlShows_by_Artist(dat);
            return new Response.Builder().type(ResponseType.GET_SHOWS_BY_DATE).data(em).build();
        }
        catch (ProjectException e) {
            System.out.println("ss");
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }
    private Response handleGET_TICKETS(Request request){

        try{
            ArrayList<Bilet> tickets = (ArrayList<Bilet>) server.getAllBilete();
            return new Response.Builder().type(ResponseType.GET_TICKETS).data(tickets).build();
        }
        catch (ProjectException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }

    }

    private Response handleSAVE_TICKET(Request request){
        try{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@");
            //Long id = (Long) request.data();
//            String nume = (String) request.data();
//            Integer nr_loc = (Integer) request.data();
//            Bilet bilet = new Bilet(nume,nr_loc);
            BuyTicket buyTicket = (BuyTicket) request.data();
            Long id = buyTicket.getId();
            Bilet ticks = buyTicket.getBilet();
            System.out.println("????????????????????");
            System.out.println(ticks);
           // System.out.println(nr_loc);

            server.buyTicket(id, ticks);
            return new Response.Builder().type(ResponseType.SAVE_TICKET).data(ticks).build();
        }
        catch (ProjectException e) {

            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }

    }
}
