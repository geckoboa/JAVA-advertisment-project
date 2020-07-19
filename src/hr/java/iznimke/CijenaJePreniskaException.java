package hr.java.vjezbe.iznimke;

/**
 * Predstavlja oznaèenu iznimku koja æe se pojaviti kada je kod izvršavanja
 * programa unesena cijena nekretnine manja od 10000
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class CijenaJePreniskaException extends Exception {

	private static final long serialVersionUID = 2555341954833609776L;

	public CijenaJePreniskaException() {

		super("Dogodila se pogreška u radu programa: Cijena nekretnine je preniska");
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
