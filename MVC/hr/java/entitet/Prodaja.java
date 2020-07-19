package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * Predstavlja entitet prodaja
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class Prodaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Artikl artikli;
	private Korisnik korisnik;
	private LocalDate datumObjave;
	private Long id;

	/**
	 * 
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param artikli     Artikl - artikl koji se prodaje
	 * @param korisnik    Korisnik - korisnik koji ga kupuje
	 * @param datumObjave LocalDate - datum objave prodaje artikla
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public Prodaja( Artikl artikli, Korisnik korisnik, LocalDate datumObjave) {
		super();
		this.id = id;
		this.artikli = artikli;
		this.korisnik = korisnik;
		this.datumObjave = datumObjave;

	}

	public Artikl getArtikli() {
		return artikli;
	}

	public void setArtikli(Artikl artikli) {
		this.artikli = artikli;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public LocalDate getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(LocalDate datumObjave) {
		this.datumObjave = datumObjave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Prodaja " + getArtikli() + " Korisnika " + getKorisnik() + " Datuma "
				+ getDatumObjave();
	}

}
