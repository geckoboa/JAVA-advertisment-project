package hr.java.vjezbe.iznimke;

/**
 * Predstavlja ozna�enu iznimku koja �e se pojaviti kada se kod izvr�avanja ne
 * mo�e jedna od ponu�enih grupa osiguranja
 * 
 * @author Nikola Augustinovi�
 *
 */
public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {

	private static final long serialVersionUID = -2291535264076831445L;

	public NemoguceOdreditiGrupuOsiguranjaException() {

		super("Dogodila se pogre�ka u radu programa: Nemogu�e odrediti grupu osiguranja ");
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
