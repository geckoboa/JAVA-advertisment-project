package hr.java.vjezbe.iznimke;

public class NedozvoljeniNegativneKolicineException extends Exception {

	
	private static final long serialVersionUID = 4578253404867432803L;

	
	public NedozvoljeniNegativneKolicineException() {

		super("Dogodila se pogreška u radu programa: unesen je negativni broj ");
	}

	public NedozvoljeniNegativneKolicineException(String message) {
		super(message);
	}

	public NedozvoljeniNegativneKolicineException(String message, Throwable cause) {
		super(message, cause);
	}

	public NedozvoljeniNegativneKolicineException(Throwable cause) {
		super(cause);
	}
}
