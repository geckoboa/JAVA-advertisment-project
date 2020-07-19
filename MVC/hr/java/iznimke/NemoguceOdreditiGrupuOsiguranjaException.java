package hr.java.vjezbe.iznimke;

/**
 * Predstavlja oznaèenu iznimku koja æe se pojaviti kada se kod izvršavanja ne
 * može jedna od ponuðenih grupa osiguranja
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {

	private static final long serialVersionUID = -2291535264076831445L;

	public NemoguceOdreditiGrupuOsiguranjaException() {

		super("Dogodila se pogreška u radu programa: Nemoguæe odrediti grupu osiguranja ");
	}

	public NemoguceOdreditiGrupuOsiguranjaException(String message) {
		super(message);
	}

	public NemoguceOdreditiGrupuOsiguranjaException(String message, Throwable cause) {
		super(message, cause);
	}

	public NemoguceOdreditiGrupuOsiguranjaException(Throwable cause) {
		super(cause);
	}
}
