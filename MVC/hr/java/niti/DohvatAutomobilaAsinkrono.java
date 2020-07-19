package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class DohvatAutomobilaAsinkrono implements Runnable {

private Automobil automobil;
	
	public DohvatAutomobilaAsinkrono(Automobil automobil) {
		this.automobil = automobil;
	}
	
	@Override
	public void run() {
		
		try {
			BazaPodataka.dohvatiAutomobilePremaKriterijima(automobil);
			
			
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	

}
