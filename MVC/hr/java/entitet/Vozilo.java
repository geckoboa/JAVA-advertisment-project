package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * 
 * Predstavlja suèelje vozila
 * 
 * @author Nikola Augustinoviæ
 *
 */
public interface Vozilo {

	/**
	 * 
	 * Zadana metoda koja pretvara konjske snage u kilovate pomoæu koeficijenta umnoška
	 * 
	 * @param kSnaga BigDecimal - konjska snaga vozila
	 * @return BigDecimal - izraèunati kilovati
	 * 
	 * @author Nikola Augustinoviæ
	 */
	default public BigDecimal izracunajKw(BigDecimal kSnaga) {

		double kilovati = 0;
		kilovati = kSnaga.doubleValue() * 0.745;

		return new BigDecimal(kilovati);

	}

	/**
	 * 
	 * Metoda bez implementacije koja vraæa BigDecimal
	 * 
	 * @return BigDecimal
	 * @throws NemoguceOdreditiGrupuOsiguranjaException - greška kada se ne može odrediti grupa osiguranja
	 * @throws CijenaJePreniskaException - greška kada je cijena nekretnine preniska
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException, CijenaJePreniskaException;

	/**
	 * 
	 * Zadana metoda koja izraèunava cijenu osiguranja tako da poziva metodu izracunajGrupuOsiguranja() i 
	 * dodjeluje joj vrijednosti ovisno o kojoj se grupi osiguranja radi
	 * 
	 * @return BigDecimal  - cijena osiguranja 
	 * @throws CijenaJePreniskaException - greška ako je cijena preniska
	 * @throws NemoguceOdreditiGrupuOsiguranjaException - greška ako se ne može odrediti grupa osiguranja
	 * 
	 * @author Nikola Augustinoviæ
	 */
	default public BigDecimal izracunajCijenuOsiguranja() throws CijenaJePreniskaException, NemoguceOdreditiGrupuOsiguranjaException {

		Integer provjeraVrijednosti = izracunajGrupuOsiguranja().intValue();

		switch (provjeraVrijednosti) {
		case 1:
			return new BigDecimal(1000);// () cijena osiguranja
		case 2:
			return new BigDecimal(1100);
		case 3:
			return new BigDecimal(1200);
		case 4:
			return new BigDecimal(1300);
		case 5:
			return new BigDecimal(1500);
		default:
			return null;
		}

	}

}
