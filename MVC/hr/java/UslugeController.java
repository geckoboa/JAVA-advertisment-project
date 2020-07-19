package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UslugeController {

	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField cijenaTextField;


	@FXML
	private TableView<Usluga> tablicaUsluga;
	@FXML
	private TableColumn<Usluga, String> naslovStupac;
	@FXML
	private TableColumn<Usluga, String> opisStupac;
	@FXML
	private TableColumn<Usluga, BigDecimal> cijenaStupac;
	@FXML
	private TableColumn<Usluga, Stanje> stanjeStupac;
	

	List<Usluga> usluge = new ArrayList<Usluga>();

	public void initialize() throws BazaPodatakaException {


		usluge = BazaPodataka.dohvatiUslugePremaKriterijima(null);

		ObservableList<Usluga> uslugeObservable = FXCollections.observableArrayList(usluge);
		tablicaUsluga.setItems(uslugeObservable);

		naslovStupac.setCellValueFactory(new PropertyValueFactory<Usluga, String>("naziv"));

		opisStupac.setCellValueFactory(new PropertyValueFactory<Usluga, String>("opis"));

		cijenaStupac.setCellValueFactory(new PropertyValueFactory<Usluga, BigDecimal>("cijena"));

		stanjeStupac.setCellValueFactory(new PropertyValueFactory<Usluga, Stanje>("stanje"));
		
		


	}

	public void uvjetPretrage() throws BazaPodatakaException {
		
		String naslov = null;
		String opis = null;
		BigDecimal cijena = null;

		

		if (naslovTextField.getText().isBlank() == false) {
			naslov = naslovTextField.getText();
		}

		if (opisTextField.getText().isBlank() == false) {
			opis = opisTextField.getText();
		}

		if (cijenaTextField.getText().isBlank() == false) {
			cijena = new BigDecimal(cijenaTextField.getText());
			
		}
		
		Usluga usluga = new Usluga(null,opis, naslov, cijena, null);
		List<Usluga> filtriraneUsluge = BazaPodataka.dohvatiUslugePremaKriterijima(usluga);

		ObservableList<Usluga> usluge = FXCollections.observableArrayList(filtriraneUsluge);

		tablicaUsluga.setItems(usluge);
		tablicaUsluga.refresh();
	}

}
