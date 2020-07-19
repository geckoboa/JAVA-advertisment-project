package hr.java.vjezbe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ProdajaController {
	
	@FXML
	private ComboBox<Artikl> artiklComboBox;
	@FXML
	private ComboBox<Korisnik> korisnikComboBox;
	@FXML
	private DatePicker datumUnosa;
	
	@FXML
	private TableView<Prodaja> tablicaProdaje;
	@FXML
	private TableColumn<Prodaja, String> oglasStupac;
	@FXML
	private TableColumn<Prodaja, String> korisnikStupac;
	@FXML
	private TableColumn<Prodaja, String> datumStupac;
	
	public void initialize() throws FileNotFoundException, SQLException, IOException, BazaPodatakaException {
		
		List<Prodaja> prodaja = BazaPodataka.dohvatiProdajuPremaKriterijima(null);
		
		ObservableList<Prodaja> value = FXCollections.observableArrayList(prodaja);
		tablicaProdaje.setItems(value );
		
		
		List<Artikl> listaArtikala = BazaPodataka.dohvatArtikala();
		ObservableList<Artikl> artikli = FXCollections.observableArrayList(listaArtikala);
			
		artiklComboBox.setItems(artikli);
		
		
		List<Korisnik> listaKorisnika = BazaPodataka.dohvatKorisnika();
		ObservableList<Korisnik> korisnici = FXCollections.observableArrayList(listaKorisnika);
		
		korisnikComboBox.setItems(korisnici);
		
		
		
		
		oglasStupac.setCellValueFactory(new PropertyValueFactory<Prodaja, String>("artikli"));
		
		korisnikStupac.setCellValueFactory(new PropertyValueFactory<Prodaja, String>("korisnik"));
		
		datumStupac.setCellValueFactory(new PropertyValueFactory<Prodaja, String>("datumObjave"));	
		
	}
	
	

}
