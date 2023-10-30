package Services;

import chat.domain.Spectacol;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
    void menuUpdate(Spectacol spectacol) throws ProjectException, RemoteException;
}
