package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * 
 * Predstavlja entitet stan koji nasljeðuje klasu Artikl i implementira suèelje
 * Nekretnina
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class Stan extends Artikl implements Nekretnina {

	private int kvadratura;

	/**
	 * 
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param opis       String - opis stana
	 * @param naziv      String - naziv stana
	 * @param cijena     BigDecimal - cijena stana
	 * @param kvadratura int - kvadratura stana
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public Stan(String opis, String naziv, BigDecimal cijena, int kvadratura, Stanje stanje) {
		super(opis, naziv, cijena, stanje);
		this.kvadratura = kvadratura;
	}

	public int getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(int kvadratura) {
		this.kvadratura = kvadratura;
	}

	@Override
	public String tekstOglasa() throws CijenaJePreniskaException {

		return "Naziv nekretnine: " + getNaziv() + "\n" + "Opis nekretnine: " + getOpis() + "\n"
				+ "Kvadratura nekretnine: " + getKvadratura() + "\n" + "Stanje nekretnine:  " + getStanje() + "\n"
				+ "Porez: " + IzracunajPorez(getCijena()) + "\n";
	}

}
