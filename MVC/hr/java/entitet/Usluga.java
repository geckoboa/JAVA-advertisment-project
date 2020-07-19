package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * 
 * Predstavlja entitet usluge koja naslje�uje klasu Artikl
 * 
 * @author Nikola Augustinovi�
 *
 */
public class Usluga extends Artikl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Konstruktor koji sadr�i definirane parametre
	 * 
	 * @param opis   String - opis usluge
	 * @param naziv  String - naziv usluge
	 * @param cijena BigDecimal - cijena usluge
	 * 
	 * @author Nikola Augustinovi�
	 */
	public Usluga(Long id, String opis, String naziv, BigDecimal cijena, Stanje stanje) {
		super(id, opis, naziv, cijena, stanje);
			
	}
	
	@Override
	public String toString() {
		return "Usluga "+getOpis()+" "+getNaziv()+" "+getCijena()+" "+getStanje();
	}

	@Override
	public String tekstOglasa() {

		return "Naziv usluge: " + getNaziv() + "\n" + "Opis: " + getOpis() + "\n" + "Stanje usluge: " + getStanje()
				+ "\n" + "Cijena: " + getCijena();
	}
	// ake ne zelimo tu metodu, klasa mora bit apstraktna

	

	
}
