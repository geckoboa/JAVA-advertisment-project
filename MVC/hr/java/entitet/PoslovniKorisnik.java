package hr.java.vjezbe.entitet;

/**
 * 
 * Predstavlja entitet poslovnog korisnika koji je vrsta korisnika
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class PoslovniKorisnik extends Korisnik {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String naziv;
	private String web;

	/**
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param email String - e-mail poslovnog korisnika
	 * @param telefon String - telefonski broj poslovnog korisnika
	 * @param naziv String - naziv poslovnog korisnika
	 * @param web String - web adresa poslovnog korisnika
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public PoslovniKorisnik(Long id, String email, String telefon, String naziv, String web) {
		super(id, email, telefon);
		this.naziv = naziv;
		this.web = web;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Override
	public String dohvatiKontakt() {

		return "Poslovni korisnik: " + getNaziv() + "\n" + "Email adresa: " + getEmail() + "\n" + "Web adresa: "
				+ getWeb() + "\n" + "Telefon: " + getTelefon();
	}

	@Override
	public String toString() {
		return "PoslovniKorisnik: " +getEmail()+" "+getTelefon()+" "+getNaziv()+" "+getWeb();
	}
	

}
