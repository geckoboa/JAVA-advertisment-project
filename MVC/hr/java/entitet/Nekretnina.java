package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * 
 * Predstavlja su�elje nekretnine
 * 
 * @author Nikola Augustinovi�
 *
 */
public interface Nekretnina {

	/**
	 * Ra�una porez na nekretninu, 3% na cijenu te nekretnine. Ako je cijena nekretnine manja od 10000 kuna, 
	 * baca se pogre�ka CijenaJePreniskaException
	 * 
	 * @param cijenaNekretnine BigDecimal - iznos nekretnine za koju se ra�una prosjek
	 * @return BigDecimal - 3% poreza na cijenu nekretnine
	 * @throws CijenaJePreniskaException - gre�ka koja se baca ako je cijena nekretnine manja od 10000 kuna
	 * 
	 * @author Nikola Augustinovi�
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
