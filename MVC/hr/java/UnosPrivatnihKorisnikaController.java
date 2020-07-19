package hr.java.vjezbe;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosPrivatnihKorisnikaController {
	@FXML
	private TextField imeTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;
	

	public void unosPrivatnihKorisnika() throws BazaPodatakaException {

		String errorText = "";
		

		if (imeTextField.getText().isBlank()) {
			errorText = errorText + "Ime je obavezan podatak \n";
		}

		if (prezimeTextField.getText().isBlank()) {
			errorText += "Prezime je obavezan podatak \n";
		}

		if (emailTextField.getText().toString().isBlank()) {
			errorText += "E-mail je obavezan podatak \n";
		}
		

		if (telefonTextField.getText().toString().isBlank()) {
			errorText += "Telefon je obavezan podatak \n";
		}
		

		if (errorText.isEmpty()) {

			PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(null, emailTextField.getText(),
					telefonTextField.getText(), imeTextField.getText(), prezimeTextField.getText());

			BazaPodataka.spremiNovogPrivatnogKorisnika(privatniKorisnik);

			prikaziProzor("Spremanje podataka o novom korisniku", "Uspješno spremanje korisnika",
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
