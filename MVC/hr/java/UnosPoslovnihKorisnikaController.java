package hr.java.vjezbe;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosPoslovnihKorisnikaController {
	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField webTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;

	public void unosPoslovnihKorisnika() throws BazaPodatakaException {

		String errorText = "";

		if (nazivTextField.getText().isBlank()) {
			errorText = errorText + "Naziv je obavezan podatak \n";
		}

		if (webTextField.getText().isBlank()) {
			errorText += "Web je obavezan podatak \n";
		}

		if (emailTextField.getText().toString().isBlank()) {
			errorText += "E-mail je obavezan podatak \n";
		}

		if (telefonTextField.getText().toString().isBlank()) {
			errorText += "Telefon je obavezan podatak \n";
		}

		if (errorText.isEmpty()) {
			
			PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(null, emailTextField.getText(), telefonTextField.getText(), 
					nazivTextField.getText(), webTextField.getText());
			
			BazaPodataka.spremiNovogPoslovnogKorisnika(poslovniKorisnik);

			prikaziProzor("Spremanje podataka o novom korisniku", "Uspješno spremanje korisniku",
					"Podaci o novom korisniku su uspješno dodani", AlertType.INFORMATION);

		} else {
			prikaziProzor("Ispravite sljedeæe pograške", "Pogrešan unos podataka", errorText, AlertType.ERROR);
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
