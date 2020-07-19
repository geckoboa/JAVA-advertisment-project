package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * 
 * Predstavlja entitet kategorije 
 * 
 * @author Nikola Augustinoviæ
 *
 */
public class Kategorija<T> extends Artikl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String naziv;
	private List<T> elementi;
	
	
	/**
	 * Konstruktor koji sadrži definirane parametre
	 * 
	 * @param naziv String - naziv kategorije
	 * 
	 * 
	 * @author Nikola Augustinoviæ
	 */
	public Kategorija(Long id, String naziv) {
		super(id);
		this.naziv = naziv;		
		elementi = new ArrayList<>();
		
	}
	
	public void dodajArtikl(T artikl) {
		elementi.add(artikl);
	}
	
	public T dohvatiArtikl (int index) {
		return elementi.get(index);
	}
	
	public List<T> vracajListu(){
		return elementi;
		
	}
	
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
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
		Kategorija other = (Kategorija) obj;
		if (naziv == null) {
			if (other.naziv != null)
				return false;
		} else if (!naziv.equals(other.naziv))
			return false;
		return true;
	}


	@Override
	public String tekstOglasa() throws NemoguceOdreditiGrupuOsiguranjaException, CijenaJePreniskaException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
	

}
