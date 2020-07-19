package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.Main;
import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Predstavlja entitet Automobil koji naslje�uje klasu Artikl i implementira
 * su�elje Vozilo
 * 
 * @author Nikola Augustinovi�
 *
 */
public class Automobil extends Artikl implements Vozilo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private BigDecimal snagaKs;

	
	

	/**
	 * Konstruktor koji sadr�i definirane parametre
	 * 
	 * @param opis    String - opis auta
	 * @param naziv   String - naziv auta
	 * @param cijena  BigDecimal - cijena auta
	 * @param snagaKs BigDecimal - snaga auta u konjskim snagama
	 * 
	 * @author Nikola Augustinovi�
	 */
	public Automobil(Long id, String opis, String naziv, BigDecimal cijena, BigDecimal snagaKs, Stanje stanje) {
		super(id, opis, naziv, cijena, stanje);
		
		this.snagaKs = snagaKs;
		
		

	}

	public BigDecimal getSnagaKs() {
		return snagaKs;
	}

	public void setSnagaKs(BigDecimal snagaKs) {
		this.snagaKs = snagaKs;
	}
	
	

	@Override
	public String toString() {
		return "Automobil "+getOpis()+" "+getNaziv()+" "+getCijena()+" "+getSnagaKs()+" "+getStanje();
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
			System.out.println("Ne mo�e se odrediti grupa osiguranja ");
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
