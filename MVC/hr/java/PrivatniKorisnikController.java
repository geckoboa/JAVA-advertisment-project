package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrivatniKorisnikController {

	@FXML
	private TextField imeTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;
	@FXML
	private TableView<PrivatniKorisnik> tablicaPivatnihKorisnika;
	@FXML
	private TableColumn<PrivatniKorisnik, String> imeStupac;
	@FXML
	private TableColumn<PrivatniKorisnik, String> prezimeStupac;
	@FXML
	private TableColumn<PrivatniKorisnik, String> emailStupac;
	@FXML
	private TableColumn<PrivatniKorisnik, String> telefonStupac;
	

	List<PrivatniKorisnik> privatniKorisnici = new ArrayList<PrivatniKorisnik>();
	

	public void initialize() throws BazaPodatakaException {
		

		privatniKorisnici = BazaPodataka.dohvatiPrivatneKorisnikePremaKriterijima(null);

		ObservableList<PrivatniKorisnik> privatniKorisniciObservable = FXCollections
				.observableArrayList(privatniKorisnici);
		tablicaPivatnihKorisnika.setItems(privatniKorisniciObservable);

		imeStupac.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("ime"));

		prezimeStupac.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("prezime"));

		emailStupac.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("email"));

		telefonStupac.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("telefon"));
		
		
	}

	public void uvjetPretrage() throws BazaPodatakaException {

		String ime = null;
		String prezime = null;
		String email = null;
		String telefon = null;
		
		if (imeTextField.getText().isBlank() == false) {
			
			ime = imeTextField.getText();
		}

		if (prezimeTextField.getText().isBlank() == false) {
			
			prezime = prezimeTextField.getText();
		}

		if (emailTextField.getText().isBlank() == false) {
			
			email = emailTextField.getText();
		}

		if (telefonTextField.getText().isBlank() == false) {
			telefon = telefonTextField.getText();
		}
		
		PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(null, email, telefon, ime, prezime);
		
		List<PrivatniKorisnik> filtriraniPrivatniKorisnici = BazaPodataka.dohvatiPrivatneKorisnikePremaKriterijima(privatniKorisnik);
		
		ObservableList<PrivatniKorisnik> privatniKorisnici = FXCollections
				.observableArrayList(filtriraniPrivatniKorisnici);

		tablicaPivatnihKorisnika.setItems(privatniKorisnici);
		tablicaPivatnihKorisnika.refresh();
	}

}
