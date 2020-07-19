package hr.java.vjezbe.entitet;

/**
 * 
 * Predstavlja entitet korisnika 
 * 
 * @author Nikola Augustinovi�
 *
 */
public abstract class Korisnik {
	
	public abstract String dohvatiKontakt();
	
	
	private String email;
	private String telefon;
	
	
	/**
	 * Konstruktor koji sadr�i definirane parametre
	 * 
	 * @param email String - e-mail korisnika
	 * @param telefon String - telefonski broj korisnika
	 * 
	 * @author Nikola Augustinovi�
	 */
	public Korisnik(String email, String telefon) {
		super();
		
		this.email = email;
		this.telefon = telefon;
	}
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
}
