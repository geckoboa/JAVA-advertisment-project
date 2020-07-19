package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Predstavlja entitet Automobil koji nasljeðuje klasu Artikl i implementira
 * suèelje Vozilo
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class Automobil extends Artikl implements Vozilo {

	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	private BigDecimal snagaKs;
	private String marka;

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	/**
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param opis    String - opis auta
	 * @param naziv   String - naziv auta
	 * @param cijena  BigDecimal - cijena auta
	 * @param snagaKs BigDecimal - snaga auta u konjskim snagama
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public Automobil(String opis, String naziv, BigDecimal cijena, BigDecimal snagaKs, Stanje stanje, String marka) {
		super(opis, naziv, cijena, stanje);
		this.snagaKs = snagaKs;
		this.marka = marka;
	}

	public BigDecimal getSnagaKs() {
		return snagaKs;
	}

	public void setSnagaKs(BigDecimal snagaKs) {
		this.snagaKs = snagaKs;
	}

	@Override
	public String tekstOglasa() {

		try {
			return "Naslov auta: " + getNaziv() + "\n" + "Opis automobila: " + getOpis() + "\n" + "Snaga automobila: "
					+ snagaKs + "\n" + "Stanje auta: " + getStanje() + "\n" + "Cijena osiguranja"
					+ izracunajCijenuOsiguranja() + "\n" + "Cijena auta: " + getCijena();
		} catch (CijenaJePreniskaException e) {
			logger.error(e.getMessage());
			System.out.println("Cijena osiguranja je preniska ");
		} catch (NemoguceOdreditiGrupuOsiguranjaException e) {
			logger.error(e.getMessage());
			System.out.println("Ne može se odrediti grupa osiguranja ");
		}
		return null;

	}

	@Override
	public BigDecimal izracunajGrupuOsiguranja() {

		if (snagaKs.intValue() >= 50 && snagaKs.intValue() <= 100) {// konjske snage
			return new BigDecimal(1); // pozivamo case 1
		}
		if (snagaKs.intValue() > 100 && snagaKs.intValue() <= 150) {
			return new BigDecimal(2); // pozivamo case 2
		}
		if (snagaKs.intValue() > 150 && snagaKs.intValue() <= 200) {
			return new BigDecimal(3); // pozivamo case 3
		}
		if (snagaKs.intValue() > 200 && snagaKs.intValue() <= 250) {
			return new BigDecimal(4); // pozivamo case 4
		}
		if (snagaKs.intValue() > 250 && snagaKs.intValue() <= 300) {
			return new BigDecimal(5); // pozivamo case 5
		}

		return new BigDecimal(5); // da ga stavi u najvecu kategoriju

	}

}
