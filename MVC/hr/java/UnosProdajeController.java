package hr.java.vjezbe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;

public class UnosProdajeController {
	
	@FXML
	private ComboBox<Artikl> artiklComboBox;
	@FXML
	private ComboBox<Korisnik> korisnikComboBox;
	@FXML
	private DatePicker datumUnosa;
	
	public void initialize() throws FileNotFoundException, SQLException, IOException, BazaPodatakaException {
		
		List<Artikl> listaArtikla = BazaPodataka.dohvatArtikala();
		ObservableList<Artikl> comboArtiklList = FXCollections.observableArrayList(listaArtikla);
		artiklComboBox.setItems(comboArtiklList);
		
		List<Korisnik> listaKorisnika = BazaPodataka.dohvatKorisnika();
		ObservableList<Korisnik> comboKorisnikList = FXCollections.observableArrayList(listaKorisnika);
		korisnikComboBox.setItems(comboKorisnikList);
	}
	
	public void unosProdaje() throws FileNotFoundException, SQLException, IOException, BazaPodatakaException {
		
		String errorText ="";
		
		if (artiklComboBox.getValue() == null) {
			errorText = errorText + "Artikl je obavezan podatak \n";
		}
		
		if (korisnikComboBox.getValue() == null) {
			errorText = errorText + "Korisnik je obavezan podatak \n";
		}
		if (datumUnosa.getValue() == null) {
			errorText = errorText + "Datum je obavezan podatak \n";
		}
		
		
		if(errorText.isEmpty()) {
			
			Prodaja prodaja = new Prodaja(artiklComboBox.getValue(), korisnikComboBox.getValue(), datumUnosa.getValue());
			BazaPodataka.spremiNovuProdaju(prodaja);
			
			prikaziProzor("Spremanje podataka o novom autu", "Uspješno spremanje auta",
					"Podaci o novom autu su uspješno dodani", AlertType.INFORMATION);
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
