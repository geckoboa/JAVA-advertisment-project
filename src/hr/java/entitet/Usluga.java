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
	 * Konstruktor koji sadr�i definirane parametre
	 * 
	 * @param opis   String - opis usluge
	 * @param naziv  String - naziv usluge
	 * @param cijena BigDecimal - cijena usluge
	 * 
	 * @author Nikola Augustinovi�
	 */
	public Usluga(String opis, String naziv, BigDecimal cijena, Stanje stanje) {
		super(opis, naziv, cijena, stanje);

	}

	@Override
	public String tekstOglasa() {

		return "Naziv usluge: " + getNaziv() + "\n" + "Opis: " + getOpis() + "\n" + "Stanje usluge: " + getStanje()
				+ "\n" + "Cijena: " + getCijena();
	}
	// ake ne zelimo tu metodu, klasa mora bit apstraktna
}
