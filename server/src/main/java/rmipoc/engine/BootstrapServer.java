package rmipoc.engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import rmipoc.service.MessageService;

/**
 * 
 * @author doleron
 * 
 *         This class is the start point of the RMI application. The main method
 *         is in charge to instantiate the server object and expose it through
 *         RMI.
 * 
 *         By creating a registry RMI starts a no-daemon Thread to keep the
 *         application listening for incoming connections. This thread can be
 *         finalized by calling UnicastRemoteObject.unexportObject
 *
 */
public class BootstrapServer {

	public static void main(String[] args) throws RemoteException {

		/*
		 * Connection parameters Although they are hard coded here, it is preferable to
		 * provide these parameters by a configuration file (usually as a .properties
		 * file)
		 */
		final String resolutionIp = "192.168.1.80";
		final int port = 1043;
		final String objectName = "MessageService";

		/*
		 * Usually RMI use different ports to keep the conversation between the client
		 * and the server object. This behavior is preferable in terms of performance
		 * but can lead to connection issues on narrowed situations such as
		 * communication through a firewall. For this purpose you can specify the port
		 * to perform the further communication by assign a value of port than 0.
		 * replyPort = 0 means for "any port" and RMI will choose a random port such as
		 * 41444, 60402, etc
		 */
		final int replyPort = port;

		/*
		 * this property can be passed as a VM parameter. For instance
		 * -Djava.rmi.server.hostname=192.168.1.80
		 */
		System.setProperty("java.rmi.server.hostname", resolutionIp);

		MessageServiceImpl serverObject = new MessageServiceImpl();

		/*
		 * Since MessageServiceImpl is not an actual extension of UnicastRemoteObject,
		 * one must to create a stub for provide a low level object able to deal with
		 * the remote calls. In a scenario where MessageServiceImpl extends
		 * UnicastRemoteObject, avoid the following line and bind serverObject directly
		 * to the registry
		 */
		MessageService stub = (MessageService) UnicastRemoteObject.exportObject(serverObject, replyPort);

		/*
		 * As the name may suggest, createRegistry create a new registry. You can use
		 * LocateRegistry.getRegistry​ instead to reuse a previously created registry. This can be
		 * particularly useful on the cases where you have different objects being bound
		 * from different moments/conditions or locations in the code.
		 */
		Registry registry = LocateRegistry.createRegistry(port);
		registry.rebind(objectName, stub);

		System.out.println(objectName + " running on port " + port);

	}

}
