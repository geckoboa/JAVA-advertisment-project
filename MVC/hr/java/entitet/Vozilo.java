package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * 
 * Predstavlja su�elje vozila
 * 
 * @author Nikola Augustinovi�
 *
 */
public interface Vozilo {

	/**
	 * 
	 * Zadana metoda koja pretvara konjske snage u kilovate pomo�u koeficijenta umno�ka
	 * 
	 * @param kSnaga BigDecimal - konjska snaga vozila
	 * @return BigDecimal - izra�unati kilovati
	 * 
	 * @author Nikola Augustinovi�
	 */
	default public BigDecimal izracunajKw(BigDecimal kSnaga) {

		double kilovati = 0;
		kilovati = kSnaga.doubleValue() * 0.745;

		return new BigDecimal(kilovati);

	}

	/**
	 * 
	 * Metoda bez implementacije koja vra�a BigDecimal
	 * 
	 * @return BigDecimal
	 * @throws NemoguceOdreditiGrupuOsiguranjaException - gre�ka kada se ne mo�e odrediti grupa osiguranja
	 * @throws CijenaJePreniskaException - gre�ka kada je cijena nekretnine preniska
	 * 
	 * @author Nikola Augustinovi�
	 */
	public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException, CijenaJePreniskaException;

	/**
	 * 
	 * Zadana metoda koja izra�unava cijenu osiguranja tako da poziva metodu izracunajGrupuOsiguranja() i 
	 * dodjeluje joj vrijednosti ovisno o kojoj se grupi osiguranja radi
	 * 
	 * @return BigDecimal  - cijena osiguranja 
	 * @throws CijenaJePreniskaException - gre�ka ako je cijena preniska
	 * @throws NemoguceOdreditiGrupuOsiguranjaException - gre�ka ako se ne mo�e odrediti grupa osiguranja
	 * 
	 * @author Nikola Augustinovi�
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
