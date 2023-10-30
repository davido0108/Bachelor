package Network.Utils;

import Network.RpcProtocol.ClientRpcReflectionWorker;
import Services.IService;

import java.net.Socket;

public class ConcurrentServer extends AbstractConcurrentServer  {

    private IService service;

    public ConcurrentServer(int port, IService srv)
    {
        super(port);
        this.service = srv;
        System.out.println("ConccurentServer =  we accesed the concurrent server");


    }

    @Override
    protected Thread createWorker(Socket client) {
        ClientRpcReflectionWorker worker = new ClientRpcReflectionWorker(service,client);

        Thread tw  = new Thread(worker);
        return tw;
    }
}
