package rmipoc.engine;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Date;

import rmipoc.message.Message;
import rmipoc.service.MessageService;

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
