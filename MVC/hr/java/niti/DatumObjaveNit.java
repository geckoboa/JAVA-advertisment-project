package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumObjaveNit implements Runnable {

	@Override
	public void run() {
		//prikazat zadnje unesenu prodaju
		Prodaja prodaja;
		try {
			prodaja = BazaPodataka.dohvatiZadnjeUnesenuProdaju();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Korisnik " + prodaja.getKorisnik());
			alert.show();
			
			
			
			//kod za dohvatiti menu controller
			//pozvat nad njim metodu promijeniLabelu
			
			
		} catch (BazaPodatakaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	
	//Thread thread = new Thread();
	//thread.start(); //zove se run metoda
	
	

}
