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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer kvadratura;
	

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
	public Stan(Long id, String opis, String naziv, BigDecimal cijena, Integer kvadratura, Stanje stanje) {
		super(id, opis, naziv, cijena, stanje);
		this.kvadratura = kvadratura;
		
	}

	public Integer getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(Integer kvadratura) {
		this.kvadratura = kvadratura;
	}

	@Override
	public String tekstOglasa() throws CijenaJePreniskaException {

		return "Naziv nekretnine: " + getNaziv() + "\n" + "Opis nekretnine: " + getOpis() + "\n"
				+ "Kvadratura nekretnine: " + getKvadratura() + "\n" + "Stanje nekretnine:  " + getStanje() + "\n"
				+ "Porez: " + IzracunajPorez(getCijena()) + "\n";
	}

	@Override
	public String toString() {
		return "Stan "+getOpis()+" "+getNaziv()+" "+getCijena()+" "+getKvadratura()+" "+getStanje();
	}

	

}
