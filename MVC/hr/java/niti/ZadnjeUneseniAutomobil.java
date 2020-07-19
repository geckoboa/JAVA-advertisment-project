package hr.java.vjezbe.niti;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ZadnjeUneseniAutomobil implements Runnable {

	@Override
	public void run() {
		
		try {
			Automobil automobil = BazaPodataka.dohvatiZadnjeUneseniAutomobil();
			prikaziProzor("Zadnje unesen automobil", "Uspješni prikaz zadnje unesenog auta",
					automobil.tekstOglasa(), AlertType.INFORMATION);
			
			
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
