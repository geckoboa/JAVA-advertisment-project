package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import hr.java.vjezbe.niti.DohvatAutomobilaAsinkrono;
import hr.java.vjezbe.niti.UnosAutomobilaAsinkrono;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

public class AutomobiliController {

	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField snagaTextField;
	@FXML
	private TextField cijenaTextField;
	@FXML
	private TableView<Automobil> tablicaAutomobila;
	@FXML
	private TableColumn<Automobil, String> naslovStupac;
	@FXML
	private TableColumn<Automobil, String> opisStupac;
	@FXML
	private TableColumn<Automobil, BigDecimal> snagaStupac;
	@FXML
	private TableColumn<Automobil, BigDecimal> cijenaStupac;
	@FXML
	private TableColumn<Automobil, Stanje> stanjeStupac;
	@FXML
	private Button pretragaButton;
	

	List<Automobil> automobili = new ArrayList<Automobil>();

	
	
	public void initialize() throws BazaPodatakaException {

		
		
		automobili = BazaPodataka.dohvatiAutomobilePremaKriterijima(null);

		ObservableList<Automobil> autiObservable = FXCollections.observableArrayList(automobili);
		tablicaAutomobila.setItems(autiObservable);

		naslovStupac.setCellValueFactory(new PropertyValueFactory<Automobil, String>("naziv"));

		opisStupac.setCellValueFactory(new PropertyValueFactory<Automobil, String>("opis"));

		snagaStupac.setCellValueFactory(new PropertyValueFactory<Automobil, BigDecimal>("snagaKs"));

		cijenaStupac.setCellValueFactory(new PropertyValueFactory<Automobil, BigDecimal>("cijena"));

		stanjeStupac.setCellValueFactory(new PropertyValueFactory<Automobil, Stanje>("stanje"));
		
	


		pretragaButton.setStyle("-fx-base: #ff1111");	
		
		

	}

	public void uvjetPretrage() throws BazaPodatakaException {
		
		String naslov = null;
		String opis = null;
		BigDecimal snagaKs = null;
		BigDecimal cijena = null;

		

		if (naslovTextField.getText().isBlank() == false) {
			
			naslovTextField.setStyle("-fx-text-box-border: green");		
			naslov = naslovTextField.getText();
			
		} else if (naslovTextField.getText().isBlank() == true) {
			naslovTextField.setStyle("-fx-text-box-border: red");
		}

		if (opisTextField.getText().isBlank() == false) {

			opis = opisTextField.getText();
		}
		if (snagaTextField.getText().isBlank() == false) {
			
			snagaKs = new BigDecimal(snagaTextField.getText()); 
		}
		if (cijenaTextField.getText().isBlank() == false) {
			
			cijena = new BigDecimal(cijenaTextField.getText()); 
		}
		
		Automobil automobil = new Automobil(null, opis, naslov, snagaKs, cijena, null);
		
		//List<Automobil> filtriraniAuti = BazaPodataka.dohvatiAutomobilePremaKriterijima(automobil);
		
		DohvatAutomobilaAsinkrono dohvatAutomobilaAsinkrono = new DohvatAutomobilaAsinkrono(automobil);
		
		Thread thread = new Thread(dohvatAutomobilaAsinkrono); 
		thread.start();
	
		//ObservableList<Automobil> auti = FXCollections.observableArrayList(filtriraniAuti);
		//tablicaAutomobila.setItems(auti);
		tablicaAutomobila.refresh();
	}
}
