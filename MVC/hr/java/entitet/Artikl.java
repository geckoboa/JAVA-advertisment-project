package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * 
 * Predstavlja entitet artikla kojeg korisnik kupuje
 * 
 * @author Nikola Augustinoviæ
 *
 */
public abstract class Artikl extends Entitet implements Serializable {
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Artikl(Long id) {
		super(id);
	}

	/**
	 * Apstraktna metoda koja vraæa tip podatka String i baca greške
	 * NemoguceOdreditiGrupuOsiguranjaException i CijenaJePreniskaException
	 * 
	 * @return String
	 * @throws NemoguceOdreditiGrupuOsiguranjaException -  greška koja se baca kada je nemoguæe odrediti grupu osiguranja
	 * @throws CijenaJePreniskaException - greška koja se baca kada je cijena nekretnine preniska
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public abstract String tekstOglasa() throws NemoguceOdreditiGrupuOsiguranjaException, CijenaJePreniskaException;

	private String opis;
	protected String naziv;
	private BigDecimal cijena;
	private Stanje stanje;

	/**
	 * 
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param opis   String - opis artikla koji se kupuje
	 * @param naziv  String - naziv artikla koji se kupuje
	 * @param cijena BigDecimal - cijena artikla koji se kupuje
	 * @param stanje Stanje - stanje artikla
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public Artikl(Long id, String opis, String naziv, BigDecimal cijena, Stanje stanje) {
		super(id);
		this.opis = opis;
		this.naziv = naziv;
		this.cijena = cijena;
		this.stanje = stanje;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public BigDecimal getCijena() {
		return cijena;
	}

	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}
	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	@Override
	public String toString() {
		return "Artikl: "+getOpis()+" "+getNaziv()+" "+getCijena()+" "+getStanje();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cijena == null) ? 0 : cijena.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((stanje == null) ? 0 : stanje.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikl other = (Artikl) obj;
		if (cijena == null) {
			if (other.cijena != null)
				return false;
		} else if (!cijena.equals(other.cijena))
			return false;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (stanje != other.stanje)
			return false;
		return true;
	}
	

}
