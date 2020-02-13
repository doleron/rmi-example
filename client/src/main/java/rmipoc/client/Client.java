package rmipoc.client;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmipoc.message.Message;
import rmipoc.service.MessageService;

/**
 * 
 * @author doleron
 * 
 *         This is an example of a RMI client
 *
 */
public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {

		/*
		 * Connection parameters Although they are hard coded here, it is preferable to
		 * provide these parameters by a configuration file (usually as a .properties
		 * file)
		 */
		final String serverIp = "192.168.1.80";
		final int serverPort = 1043;
		final String serverObjectName = "MessageService";

		try {

			// Connection
			Registry registry = LocateRegistry.getRegistry(serverIp, serverPort);
			MessageService server = (MessageService) registry.lookup(serverObjectName);

			// Preparing call
			Message message = new Message();
			message.setText("Hello Server, I'm the client.");

			// Actual calling the server
			Message response = server.send(message);

			// post processing server response
			System.out.println("Server replied: \"" + response.getText() + "\"");

		} catch (ConnectException connectException) {

			System.err.println("Connection exception while attempting to connect to " + serverIp + ":" + serverPort);

		} catch (NotBoundException notBoundException) {

			System.err.println("Object named das " + serverObjectName + " was not found in remote server");

		}

	}

}
