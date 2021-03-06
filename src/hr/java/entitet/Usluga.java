package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * 
 * Predstavlja entitet usluge koja nasljeđuje klasu Artikl
 * 
 * @author Nikola Augustinović
 *
 */
public class Usluga extends Artikl {

	/**
	 * 
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param opis   String - opis usluge
	 * @param naziv  String - naziv usluge
	 * @param cijena BigDecimal - cijena usluge
	 * 
	 * @author Nikola Augustinović
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
