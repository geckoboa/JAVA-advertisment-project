package hr.java.vjezbe.iznimke;

/**
 * Predstavlja ozna�enu iznimku koja �e se pojaviti kada je kod izvr�avanja
 * programa unesena cijena nekretnine manja od 10000
 * 
 * @author Nikola Augustinovi�
 *
 */
public class CijenaJePreniskaException extends Exception {

	private static final long serialVersionUID = 2555341954833609776L;

	public CijenaJePreniskaException() {

		super("Dogodila se pogre�ka u radu programa: Cijena nekretnine je preniska");
	}

	public CijenaJePreniskaException(String message) {
		super(message);
	}

	public CijenaJePreniskaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CijenaJePreniskaException(Throwable cause) {
		super(cause);
	}

}
