package hr.java.vjezbe;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MenuController {
	
	@FXML
	Label labelZadnjiAutomobil;
	
	public void promijeniLabelu(String text) {
		labelZadnjiAutomobil.setText(text);
	}
	
	public void prikaziUnosProdaje() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosProdaje.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	
	public void prikaziProdaju() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Prodaja.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void prikaziUnosPoslovnihKorisnika() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosPoslovnihKorisnika.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void prikaziUnosPrivatneKorisnike() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosPrivatnihKorisnika.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void prikaziUnosUsluga() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosUsluge.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void prikaziUnosStanova() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosStanova.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void prikaziUnosAutomobila() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosAutomobili.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void prikaziPretraguArtikla() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Artikli.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziPretraguAutomobila() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Automobili.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziPretraguStanova() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Stanovi.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziPretraguUsluga() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Usluge.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziPretraguKorisnika() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Korisnici.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziPretraguPrivatnihKorisnika() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("PrivatniKorisnik.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void prikaziPretraguPoslovnihKorisnika() {
	BorderPane root;
	try {
		root = (BorderPane) FXMLLoader.load(getClass().getResource("PoslovniKorisnik.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
