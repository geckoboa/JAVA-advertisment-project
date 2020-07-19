package hr.java.vjezbe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StanoviController {

	@FXML
	private TextField naslovTextField;
	@FXML
	private TextField opisTextField;
	@FXML
	private TextField kvadraturaTextField;
	@FXML
	private TextField cijenaTextField;

	@FXML
	private TableView<Stan> tablicaStanova;
	@FXML
	private TableColumn<Stan, String> naslovStupac;
	@FXML
	private TableColumn<Stan, String> opisStupac;
	@FXML
	private TableColumn<Stan, Integer> kvadraturaStupac;
	@FXML
	private TableColumn<Stan, BigDecimal> cijenaStupac;
	

	List<Stan> stanovi = new ArrayList<Stan>();

	public void initialize() throws BazaPodatakaException {
		
		stanovi = BazaPodataka.dohvatiStanovePremaKriterijima(null);

		ObservableList<Stan> stanoviObservable = FXCollections.observableArrayList(stanovi);
		tablicaStanova.setItems(stanoviObservable);

		naslovStupac.setCellValueFactory(new PropertyValueFactory<Stan, String>("naziv"));

		opisStupac.setCellValueFactory(new PropertyValueFactory<Stan, String>("opis"));

		kvadraturaStupac.setCellValueFactory(new PropertyValueFactory<Stan, Integer>("kvadratura"));

		cijenaStupac.setCellValueFactory(new PropertyValueFactory<Stan, BigDecimal>("cijena"));
		
		

		
	}

	public void uvjetPretrage() throws BazaPodatakaException {
		String naslov = null;
		String opis = null;
		Integer kvadratura = null;
		BigDecimal cijena = null;
		
		
		
		if (naslovTextField.getText().isBlank() == false) {
			//filtriraniStanovi = stanovi.stream().filter(p -> p.getNaziv().startsWith(naslovTextField.getText()))
			//		.collect(Collectors.toList());
			naslov = naslovTextField.getText();
		}
		if (opisTextField.getText().isBlank() == false) {
			//filtriraniStanovi = stanovi.stream().filter(p -> p.getOpis().startsWith(opisTextField.getText()))
			//		.collect(Collectors.toList());
			opis = opisTextField.getText();
		}
		if (kvadraturaTextField.getText().isBlank() == false) {
			//filtriraniStanovi = stanovi.stream()
			//		.filter(p -> p.getKvadratura().toString().startsWith(kvadraturaTextField.getText()))
			//		.collect(Collectors.toList());
			 kvadratura = Integer.valueOf(kvadraturaTextField.getText())  ;
		}
		if (cijenaTextField.getText().isBlank() == false) {
			//filtriraniStanovi = stanovi.stream()
			//		.filter(p -> p.getCijena().toString().startsWith(cijenaTextField.getText()))
			//		.collect(Collectors.toList());
			
			cijena = new BigDecimal(cijenaTextField.getText());
			
		}
		
		
		 
		Stan stan = new Stan(null,opis, naslov, cijena, kvadratura, null);
		List<Stan> filtriraniStanovi = BazaPodataka.dohvatiStanovePremaKriterijima(stan);
		
		ObservableList<Stan> stanovi = FXCollections.observableArrayList(filtriraniStanovi);
		tablicaStanova.setItems(stanovi);
		tablicaStanova.refresh();
	}

}
