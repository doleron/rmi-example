package rmipoc.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import rmipoc.message.Message;

public interface MessageService extends Remote {

  Message send(Message clientMessage) throws RemoteException;
  
}
