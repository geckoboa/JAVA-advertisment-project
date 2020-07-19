package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * 
 * Predstavlja entitet privatnog korisnika koji je vrsta korisnika
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class PrivatniKorisnik extends Korisnik {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;

	/**
	 * 
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param email String - e-mail privatnog korisnika
	 * @param telefon  String - telefonski broj privatnog korisnika
	 * @param ime String - ime privatnog korisnika
	 * @param prezime String - prezime privatnog korisnika
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public PrivatniKorisnik(Long id, String email, String telefon, String ime, String prezime) {
		super(id, email, telefon);
		this.ime = ime;
		this.prezime = prezime;
		
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Override
	public String dohvatiKontakt() {

		return "PrivatniKorisnik " + getIme() + " " + getPrezime() + "\n" + "Email adresa: " + getEmail() + "\n"
				+ "Telefon: " + getTelefon();
	}

	@Override
	public String toString() {
		return "PrivatniKorisnik "+ getEmail()+" "+getTelefon()+" "+getIme()+" "+getPrezime();
	}
	

}
