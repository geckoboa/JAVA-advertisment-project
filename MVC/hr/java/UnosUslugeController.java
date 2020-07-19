package hr.java.vjezbe;

import java.math.BigDecimal;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosUslugeController {
	
	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField cijenaTextField;
	@FXML
	private ComboBox<Stanje> stanjeComboBox;
	
	public void initialize() {
		ObservableList<Stanje> comboStanjeList = FXCollections.observableArrayList(Stanje.values());
		stanjeComboBox.setItems(comboStanjeList);

	}
	
	public void unosUsluga() throws BazaPodatakaException {

		String errorText = "";

		if (naslovTextField.getText().isBlank()) {
			errorText = errorText + "Naslov je obavezan podatak \n";
		}

		if (opisTextField.getText().isBlank()) {
			errorText += "Opis je obavezan podatak \n";
		}

		if (cijenaTextField.getText().toString().isBlank()) {
			errorText += "Cijena je obavezan podatak \n";
		}
		
		

		if (errorText.isEmpty()) {
			
			BigDecimal cijenaBigDecimale = new BigDecimal(cijenaTextField.getText());

			Usluga usluga = new Usluga(null, opisTextField.getText(), naslovTextField.getText(), cijenaBigDecimale, stanjeComboBox.getValue());
			
			BazaPodataka.spremiNovaUsluga(usluga);	
			

			prikaziProzor("Spremanje podataka o novom autu", "Uspješno spremanje auta",
					"Podaci o novom autu su uspješno dodani", AlertType.INFORMATION);

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
