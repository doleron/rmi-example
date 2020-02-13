package rmipoc.engine;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Date;

import rmipoc.message.Message;
import rmipoc.service.MessageService;

/**
 * 
 * @author doleron
 * 
 *         This class is a concrete implementation of the MessageService
 *         contract. Depending on the project requirements, this class might
 *         extend java.rmi.server.UnicastRemoteObject but it is not mandatory.
 * 
 *         Extend java.rmi.server.UnicastRemoteObject only if you actual need
 *         environmental resources such as retrieve client IP or so.
 *
 */
public class MessageServiceImpl implements MessageService {

	@Override
	public Message send(Message sourceMessage) throws RemoteException {

		Message result = null;

		if (sourceMessage != null) {
			System.out.println("New income message arrived: \"" + sourceMessage.getText() + "\" at "
					+ DateFormat.getDateTimeInstance().format(new Date()));

			result = new Message();
			result.setText("I've received: " + sourceMessage.getText());
		}

		return result;
	}

}
