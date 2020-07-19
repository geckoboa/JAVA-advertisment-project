package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * 
 * Predstavlja suèelje nekretnine
 * 
 * @author Nikola Augustinoviæ
 *
 */
public interface Nekretnina {

	/**
	 * Raèuna porez na nekretninu, 3% na cijenu te nekretnine. Ako je cijena nekretnine manja od 10000 kuna, 
	 * baca se pogreška CijenaJePreniskaException
	 * 
	 * @param cijenaNekretnine BigDecimal - iznos nekretnine za koju se raèuna prosjek
	 * @return BigDecimal - 3% poreza na cijenu nekretnine
	 * @throws CijenaJePreniskaException - greška koja se baca ako je cijena nekretnine manja od 10000 kuna
	 * 
	 * @author Nikola Augustinoviæ
	 */
	default public BigDecimal IzracunajPorez (BigDecimal cijenaNekretnine) throws CijenaJePreniskaException {
		
		double porez =0;
		porez=cijenaNekretnine.doubleValue()*0.03;
		
		if(cijenaNekretnine.doubleValue()<10000) {
			throw new CijenaJePreniskaException();
			
		}
		
		
		return BigDecimal.valueOf(porez);
		
	}

}
