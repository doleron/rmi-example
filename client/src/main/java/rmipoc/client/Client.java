package rmipoc.client;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmipoc.message.Message;
import rmipoc.service.MessageService;

public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		final String IP = "192.168.1.80";
		final int PORT = 1043;
		final String OBJECT_NAME = "MessageService";
		
		try {
			
			Registry registry = LocateRegistry.getRegistry(IP, PORT);
			MessageService server = (MessageService) registry.lookup(OBJECT_NAME);
			
			Message message = new Message();
			message.setText("Hello Server, I'm the client.");
			
			Message response = server.send(message);
			
			System.out.println("Server replied: \"" + response.getText() + "\"");
			
		} catch (ConnectException conex) {
			
			System.err.println("Connection exception while attempting to connect to " + IP + ":" + PORT);
			
		} catch (NotBoundException nbex) {
			
			System.err.println("Object named das " + OBJECT_NAME + " was not found in remote server");
			
		}

	}

}
