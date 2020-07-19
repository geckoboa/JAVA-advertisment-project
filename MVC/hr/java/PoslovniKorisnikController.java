package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.List;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PoslovniKorisnikController {

	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField webTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefonTextField;
	@FXML
	private TableView<PoslovniKorisnik> tablicaPoslovnihKorisnika;
	@FXML
	private TableColumn<PoslovniKorisnik, String> nazivStupac;
	@FXML
	private TableColumn<PoslovniKorisnik, String> webStupac;
	@FXML
	private TableColumn<PoslovniKorisnik, String> emailStupac;
	@FXML
	private TableColumn<PoslovniKorisnik, String> telefonStupac;

	List<PoslovniKorisnik> poslovniKorisnici = new ArrayList<PoslovniKorisnik>();

	public void initialize() throws BazaPodatakaException {


		poslovniKorisnici = BazaPodataka.dohvatiPoslovneKorisnikePremaKriterijima(null);

		ObservableList<PoslovniKorisnik> poslovniKorisniciObservable = FXCollections
				.observableArrayList(poslovniKorisnici);
		tablicaPoslovnihKorisnika.setItems(poslovniKorisniciObservable);

		nazivStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("naziv"));

		webStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("web"));

		emailStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("email"));

		telefonStupac.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("telefon"));
	}

	public void uvjetPretrage() throws BazaPodatakaException {
		
		String naziv = null;
		String web = null;
		String email = null;
		String telefon = null;

		

		if (nazivTextField.getText().isBlank() == false) {
			
			naziv = nazivTextField.getText();
		}

		if (webTextField.getText().isBlank() == false) {
			
			web = webTextField.getText();
		}

		if (emailTextField.getText().isBlank() == false) {
			
			email = emailTextField.getText();
		}

		if (telefonTextField.getText().isBlank() == false) {
			
			telefon = telefonTextField.getText();
		}
		
		PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(null, email, telefon, naziv, web);
		
		List<PoslovniKorisnik> filtriraniPoslovniKorisnici = BazaPodataka.dohvatiPoslovneKorisnikePremaKriterijima(poslovniKorisnik);
		
		ObservableList<PoslovniKorisnik> poslovniKorisnici = FXCollections
				.observableArrayList(filtriraniPoslovniKorisnici);

		tablicaPoslovnihKorisnika.setItems(poslovniKorisnici);
		tablicaPoslovnihKorisnika.refresh();
	}

}
