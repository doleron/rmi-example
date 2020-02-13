package rmipoc.message;

import java.io.Serializable;

/**
 * 
 * @author doleron
 * 
 *         This class represents a packet used to wrap out data to be exchanged
 *         between client and server. Note that the objects passed as parameters
 *         in RMI calls must be serializable
 *
 */
@SuppressWarnings("serial")
public class Message implements Serializable {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
