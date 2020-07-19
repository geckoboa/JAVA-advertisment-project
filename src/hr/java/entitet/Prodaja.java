package hr.java.vjezbe.entitet;

import java.time.LocalDate;
/**
 * 
 * Predstavlja entitet prodaja 
 * 
 * @author Nikola Augustinovi�
 *
 */
public class Prodaja {
	
	private Artikl artikli;
	private Korisnik korisnik;
	private LocalDate datumObjave;
	
	
	/**
	 * 
	 * Konstruktor koji sadr�i definirane parametre
	 * 
	 * @param artikli Artikl - artikl koji se prodaje
	 * @param korisnik Korisnik - korisnik koji ga kupuje
	 * @param datumObjave LocalDate - datum objave prodaje artikla
	 * 
	 * @author Nikola Augustinovi�
	 */
	public Prodaja(Artikl artikli, Korisnik korisnik, LocalDate datumObjave) {
		super();
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
	
	

}
