package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.iznimke.BazaPodatakaException;



public class UnosAutomobilaAsinkrono implements Runnable {

	private Automobil automobil;
	
	public UnosAutomobilaAsinkrono(Automobil automobil) {
		this.automobil = automobil;
	}
	
	@Override
	public void run() {
		
		try {
			BazaPodataka.spremiNoviAutomobil(automobil);
			
			
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
