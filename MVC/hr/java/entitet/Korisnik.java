package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * 
 * Predstavlja entitet korisnika 
 * 
 * @author Nikola Augustinoviæ
 *
 */
public abstract class Korisnik extends Entitet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public abstract String dohvatiKontakt();
	
	
	private String email;
	private String telefon;
	
	
	/**
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param email String - e-mail korisnika
	 * @param telefon String - telefonski broj korisnika
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public Korisnik(Long id, String email, String telefon) {
		super(id);
		
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


	@Override
	public String toString() {
		return "Korisnik "+getEmail() + " " + getTelefon()+" ";
	}
	
	
}
