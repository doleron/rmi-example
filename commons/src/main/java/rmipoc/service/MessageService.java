package rmipoc.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import rmipoc.message.Message;

/**
 * 
 * @author doleron
 * 
 *         This is the interface that exposes the services provided by the
 *         remote server
 *
 */
public interface MessageService extends Remote {

	/**
	 * 
	 * @param clientMessage is an object. Remember that in RMI arguments are
	 *                      serializable in such way the remote object will handle a
	 *                      copy of the object, not a reference to the original
	 *                      object as usual in local calls
	 * @return a replied message object
	 * @throws RemoteException
	 */
	Message send(Message clientMessage) throws RemoteException;

}
