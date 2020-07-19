package hr.java.vjezbe.niti;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PrivatniKorisnikNajduzeImeNit implements Runnable  {

	@Override
	public void run() {
		
		try {
			PrivatniKorisnik privatniKorisnik = BazaPodataka.dohvatiKorisnikaSNajduzimImenom();
			
			prikaziProzor("Korisnik sa najdužim imenom", "Uspješni prikaz korisnika",
					privatniKorisnik.dohvatiKontakt(), AlertType.INFORMATION);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void prikaziProzor(String header, String title, String content, AlertType type) {
		Alert ale = new Alert(type);
		ale.setHeaderText(header);
		ale.setTitle(title);
		ale.setContentText(content);
		ale.show();
	}
	

}
