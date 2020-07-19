package hr.java.vjezbe.baza;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.Main;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {

	private static final String DATABASE_FILE = "Baza.properties";
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static List<Stan> dohvatiStanovePremaKriterijima(Stan stan) throws BazaPodatakaException {
		List<Stan> listaStanova = new ArrayList<>();

		try (Connection veza = spajanjeNaBazu()) {

			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");

			if (Optional.ofNullable(stan).isEmpty() == false) {

				if (Optional.ofNullable(stan).map(Stan::getId).isPresent()) {
					sqlUpit.append(" AND artikl.id = " + stan.getId());

				}

				if (Optional.ofNullable(stan.getNaziv()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND artikl.naslov LIKE '%" + stan.getNaziv() + "%'");

				}

				if (Optional.ofNullable(stan.getOpis()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND artikl.opis LIKE '%" + stan.getOpis() + "%'");
				}

				if (Optional.ofNullable(stan).map(Stan::getCijena).isPresent()) {
					sqlUpit.append(" AND artikl.cijena LIKE '%" + stan.getCijena() + "%'");
				}

				if (Optional.ofNullable(stan).map(Stan::getKvadratura).isPresent()) {
					sqlUpit.append(" AND artikl.kvadratura LIKE '%" + stan.getKvadratura() + "%'");
				}
			}

			Statement query = veza.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				Integer kvadratura = resultSet.getInt("kvadratura");
				String stanje = resultSet.getString("naziv");
				Stan newStan = new Stan(id, naslov, opis, cijena, kvadratura, Stanje.valueOf(stanje.toUpperCase()));
				listaStanova.add(newStan);
			}

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu sa bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaStanova;

	}

	public static List<Automobil> dohvatiAutomobilePremaKriterijima(Automobil automobil) throws BazaPodatakaException {
		List<Automobil> listaAutomobila = new ArrayList<>();

		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil'");

			if (Optional.ofNullable(automobil).isEmpty() == false) {

				if (Optional.ofNullable(automobil).map(Automobil::getId).isPresent()) {
					sqlUpit.append(" AND artikl.id = " + automobil.getId());
				}

				if (Optional.ofNullable(automobil.getOpis()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND artikl.opis LIKE '%" + automobil.getOpis() + "%'");
				}

				if (Optional.ofNullable(automobil.getNaziv()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND artikl.naslov LIKE '%" + automobil.getNaziv() + "%'");
				}

				if (Optional.ofNullable(automobil).map(Automobil::getCijena).isPresent()) {
					sqlUpit.append(" AND artikl.cijena LIKE '%" + automobil.getCijena() + "%'");
				}

				if (Optional.ofNullable(automobil).map(Automobil::getSnagaKs).isPresent()) {
					sqlUpit.append(" AND artikl.snaga LIKE '%" + automobil.getSnagaKs() + "%'");
				}
			}
			Statement query = veza.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				BigDecimal snagaKs = resultSet.getBigDecimal("snaga");
				String opis = resultSet.getString("opis");
				String stanje = resultSet.getString("naziv");
				Automobil newAutomobil = new Automobil(id, opis, naslov, cijena, snagaKs,
						Stanje.valueOf(stanje.toUpperCase()));
				listaAutomobila.add(newAutomobil);
			}

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu sa bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaAutomobila;

	}

	public static PrivatniKorisnik dohvatiKorisnikaSNajduzimImenom()
			throws FileNotFoundException, SQLException, IOException {
		try (Connection connection = spajanjeNaBazu()) {
			String sql = "SELECT korisnik.id, email, telefon,ime, prezime FROM korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika "
					+ "WHERE tipKorisnika.naziv = 'PrivatniKorisnik' order by length(ime) desc limit 1";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String email = resultSet.getString("email");
				String telefon = resultSet.getString("telefon");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");

				PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(id, email, telefon, ime, prezime);
				return privatniKorisnik;
			}

		}

		return null;
	}

	public static Automobil dohvatiZadnjeUneseniAutomobil()
			throws FileNotFoundException, SQLException, IOException {
		try (Connection connection = spajanjeNaBazu()) {
			String sql = "SELECT distinct artikl.* FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla "
					+ "WHERE tipArtikla.naziv = 'Automobil' order by id desc limit 1";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String opis = resultSet.getString("opis");
				String naziv = resultSet.getString("naziv");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				BigDecimal snagaKs = resultSet.getBigDecimal("snaga");
				String stanje = resultSet.getString("stanje");
				Stanje opetStanje = Stanje.valueOf(stanje.toUpperCase());

				Automobil zadnjiAuto = new Automobil(id, opis, naziv, cijena, snagaKs, opetStanje);
				return zadnjiAuto;
			}

		}

		return null;
	}

	public static List<PrivatniKorisnik> dohvatiPrivatneKorisnikePremaKriterijima(PrivatniKorisnik privatniKorisnici)
			throws BazaPodatakaException {
		List<PrivatniKorisnik> listaPrivatniKorisnika = new ArrayList<>();

		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT distinct korisnik.id, email, telefon, ime, prezime "
					+ "FROM korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika WHERE tipKorisnika.naziv = 'PrivatniKorisnik'");

			if (Optional.ofNullable(privatniKorisnici).isEmpty() == false) {

				if (Optional.ofNullable(privatniKorisnici).map(PrivatniKorisnik::getId).isPresent()) {
					sqlUpit.append(" AND korisnik.id = " + privatniKorisnici.getId());
				}

				if (Optional.ofNullable(privatniKorisnici.getEmail()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.email LIKE '%" + privatniKorisnici.getEmail() + "%'");
				}

				if (Optional.ofNullable(privatniKorisnici.getTelefon()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.telefon LIKE '%" + privatniKorisnici.getTelefon() + "%'");
				}

				if (Optional.ofNullable(privatniKorisnici.getIme()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.ime LIKE '%" + privatniKorisnici.getIme() + "%'");
				}

				if (Optional.ofNullable(privatniKorisnici.getPrezime()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.prezime LIKE '%" + privatniKorisnici.getPrezime() + "'%");
				}
			}

			Statement query = veza.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String email = resultSet.getString("email");
				String telefon = resultSet.getString("telefon");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");

				PrivatniKorisnik newPrivatniKorisnik = new PrivatniKorisnik(id, email, telefon, ime, prezime);
				listaPrivatniKorisnika.add(newPrivatniKorisnik);
			}

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu sa bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaPrivatniKorisnika;

	}

	public static List<Usluga> dohvatiUslugePremaKriterijima(Usluga usluge) throws BazaPodatakaException {
		List<Usluga> listaUsluga = new ArrayList<>();

		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv "
					+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");

			if (Optional.ofNullable(usluge).isEmpty() == false) {

				if (Optional.ofNullable(usluge).map(Usluga::getId).isPresent()) {
					sqlUpit.append(" AND artikl.id = " + usluge.getId());
				}

				if (Optional.ofNullable(usluge.getOpis()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND artikl.opis LIKE '%" + usluge.getOpis() + "%'");
				}

				if (Optional.ofNullable(usluge.getNaziv()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND artikl.naslov LIKE '%" + usluge.getNaziv() + "%'");
				}

				if (Optional.ofNullable(usluge).map(Usluga::getCijena).isPresent()) {
					sqlUpit.append(" AND artikl.cijena LIKE '%" + usluge.getCijena() + "%'");
				}
			}

			Statement query = veza.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String opis = resultSet.getString("opis");
				String naslov = resultSet.getString("naslov");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				String stanje = resultSet.getString("naziv");
				Usluga newUsluga = new Usluga(id, opis, naslov, cijena, Stanje.valueOf(stanje.toUpperCase()));
				listaUsluga.add(newUsluga);
			}

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu sa bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaUsluga;

	}

	public static List<PoslovniKorisnik> dohvatiPoslovneKorisnikePremaKriterijima(PoslovniKorisnik poslovniKorisnici)
			throws BazaPodatakaException {
		List<PoslovniKorisnik> listaPoslovniKorsnika = new ArrayList<>();

		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct korisnik.id, email, telefon, korisnik.naziv, web "
							+ "FROM korisnik inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika WHERE tipKorisnika.naziv = 'PoslovniKorisnik'");

			if (Optional.ofNullable(poslovniKorisnici).isEmpty() == false) {

				if (Optional.ofNullable(poslovniKorisnici).map(PoslovniKorisnik::getId).isPresent()) {
					sqlUpit.append(" AND korisnik.id = " + poslovniKorisnici.getId());
				}

				if (Optional.ofNullable(poslovniKorisnici.getEmail()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.email LIKE '%" + poslovniKorisnici.getEmail() + "%'");
				}

				if (Optional.ofNullable(poslovniKorisnici.getTelefon()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.telefon LIKE '%" + poslovniKorisnici.getTelefon() + "%'");
				}

				if (Optional.ofNullable(poslovniKorisnici.getNaziv()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.naziv LIKE '%" + poslovniKorisnici.getNaziv() + "%'");
				}

				if (Optional.ofNullable(poslovniKorisnici.getWeb()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND korisnik.web LIKE '%" + poslovniKorisnici.getWeb() + "%'");
				}
			}

			Statement query = veza.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String email = resultSet.getString("email");
				String telefon = resultSet.getString("telefon");
				String naziv = resultSet.getString("naziv");
				String web = resultSet.getString("web");
				PoslovniKorisnik newPoslovniKorisnik = new PoslovniKorisnik(id, email, telefon, naziv, web);
				listaPoslovniKorsnika.add(newPoslovniKorisnik);
			}

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu sa bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaPoslovniKorsnika;

	}

	public static Prodaja dohvatiZadnjeUnesenuProdaju() throws BazaPodatakaException {

		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika, \r\n"
							+ "korisnik.naziv as nazivKorisnika, web, email, telefon, \r\n"
							+ "korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla,\r\n"
							+ "artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura,\r\n"
							+ "artikl.snaga, stanje.naziv as stanje, prodaja.datumObjave, artikl.id as idArtikla\r\n"
							+ "from korisnik inner join \r\n"
							+ "tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join\r\n"
							+ "prodaja on prodaja.idKorisnik = korisnik.id inner join\r\n"
							+ "artikl on artikl.id = prodaja.idArtikl inner join\r\n"
							+ "tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join\r\n"
							+ "stanje on stanje.id = artikl.idStanje order by datumObjave desc\r\n" + "limit 1\r\n");

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Korisnik korisnik = null;
				if (resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
					korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("prezime"),
							resultSet.getString("email"), resultSet.getString("telefon"), resultSet.getString("ime"));
				} else if (resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
					korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("web"),
							resultSet.getString("telefon"), resultSet.getString("email"),
							resultSet.getString("nazivKorisnika"));
				}
				Artikl artikl = null;
				if (resultSet.getString("tipArtikla").equals("Automobil")) {
					artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("opis"),
							resultSet.getString("naslov"), resultSet.getBigDecimal("cijena"),
							resultSet.getBigDecimal("snaga"),
							Stanje.valueOf(resultSet.getString("stanje").toUpperCase()));

				} else if (resultSet.getString("tipArtikla").equals("Usluga")) {
					artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("opis"),
							resultSet.getString("naslov"), resultSet.getBigDecimal("cijena"),

							Stanje.valueOf(resultSet.getString("stanje").toUpperCase()));
				} else if (resultSet.getString("tipArtikla").equals("Stan")) {
					artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("opis"),
							resultSet.getString("naslov"), resultSet.getBigDecimal("cijena"),
							resultSet.getInt("kvadratura"),
							Stanje.valueOf(resultSet.getString("stanje").toUpperCase()));

				}
				Prodaja novaProdaja = new Prodaja(artikl, korisnik,
						resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				return novaProdaja;
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return null;
	}

	public static List<Prodaja> dohvatiProdajuPremaKriterijima(Prodaja prodaja)
			throws BazaPodatakaException, FileNotFoundException, SQLException, IOException {
		List<Prodaja> listaProdaje = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"select distinct korisnik.id as idKorisnika, tipKorisnika.naziv as tipKorisnika, \r\n"
							+ "korisnik.naziv as nazivKorisnika, web, email, telefon, \r\n"
							+ "korisnik.ime, korisnik.prezime, tipArtikla.naziv as tipArtikla,\r\n"
							+ "artikl.naslov, artikl.opis, artikl.cijena, artikl.kvadratura,\r\n"
							+ "artikl.snaga, stanje.naziv as stanje, prodaja.datumObjave, artikl.id as idArtikla\r\n"
							+ "from korisnik inner join \r\n"
							+ "tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika inner join\r\n"
							+ "prodaja on prodaja.idKorisnik = korisnik.id inner join\r\n"
							+ "artikl on artikl.id = prodaja.idArtikl inner join\r\n"
							+ "tipArtikla on tipArtikla.id = artikl.idTipArtikla inner join\r\n"
							+ "stanje on stanje.id = artikl.idStanje where 1=1");

			if (Optional.ofNullable(prodaja).isEmpty() == false) {

				if (Optional.ofNullable(prodaja.getArtikli()).isPresent())
					sqlUpit.append(" AND prodaja.idArtikl = " + prodaja.getArtikli().getId());
				if (Optional.ofNullable(prodaja.getKorisnik()).isPresent())
					sqlUpit.append(" AND prodaja.idArtikl = " + prodaja.getKorisnik().getId());
				if (Optional.ofNullable(prodaja.getDatumObjave()).isPresent()) {

					sqlUpit.append(" AND prodaja.datumObjave = '"
							+ prodaja.getDatumObjave().format(DateTimeFormatter.ISO_DATE) + "'");
				}
			}

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Korisnik korisnik = null;
				if (resultSet.getString("tipKorisnika").equals("PrivatniKorisnik")) {
					korisnik = new PrivatniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("prezime"),
							resultSet.getString("email"), resultSet.getString("telefon"), resultSet.getString("ime"));
				} else if (resultSet.getString("tipKorisnika").equals("PoslovniKorisnik")) {
					korisnik = new PoslovniKorisnik(resultSet.getLong("idKorisnika"), resultSet.getString("web"),
							resultSet.getString("telefon"), resultSet.getString("email"),
							resultSet.getString("nazivKorisnika"));
				}
				Artikl artikl = null;
				if (resultSet.getString("tipArtikla").equals("Automobil")) {
					artikl = new Automobil(resultSet.getLong("idArtikla"), resultSet.getString("opis"),
							resultSet.getString("naslov"), resultSet.getBigDecimal("cijena"),
							resultSet.getBigDecimal("snaga"),
							Stanje.valueOf(resultSet.getString("stanje").toUpperCase()));

				} else if (resultSet.getString("tipArtikla").equals("Usluga")) {
					artikl = new Usluga(resultSet.getLong("idArtikla"), resultSet.getString("opis"),
							resultSet.getString("naslov"), resultSet.getBigDecimal("cijena"),

							Stanje.valueOf(resultSet.getString("stanje").toUpperCase()));
				} else if (resultSet.getString("tipArtikla").equals("Stan")) {
					artikl = new Stan(resultSet.getLong("idArtikla"), resultSet.getString("opis"),
							resultSet.getString("naslov"), resultSet.getBigDecimal("cijena"),
							resultSet.getInt("kvadratura"),
							Stanje.valueOf(resultSet.getString("stanje").toUpperCase()));

				}
				Prodaja novaProdaja = new Prodaja(artikl, korisnik,
						resultSet.getTimestamp("datumObjave").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				listaProdaje.add(novaProdaja);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}

		return listaProdaje;
	}

	public static List<Artikl> dohvatiNajjeftinijiArtikl() throws FileNotFoundException, SQLException, IOException {
		List<Artikl> artikli = new ArrayList<Artikl>();
		try (Connection connection = spajanjeNaBazu()) {
			String sql = "SELECT distinct artikl.id as idArtikla, naslov, opis, cijena, snaga, kvadratura, stanje.naziv as stanje,"
					+ " tipArtikla.naziv as tipArtikla FROM artikl inner join stanje on stanje.id = artikl.idStanje"
					+ " inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla order by cijena asc limit 1";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String tipArtikl = resultSet.getString("tipArtikla");

				if (tipArtikl.equals("Automobil")) {
					Long idArtikla = resultSet.getLong("idArtikla");
					String opis = resultSet.getString("opis");
					String naziv = resultSet.getString("naziv");
					BigDecimal cijena = resultSet.getBigDecimal("cijena");
					BigDecimal snagaKs = resultSet.getBigDecimal("snaga");
					String stanje = resultSet.getString("stanje");
					Stanje opetStanje = Stanje.valueOf(stanje.toUpperCase());

					Automobil auto = new Automobil(idArtikla, opis, naziv, cijena, snagaKs, opetStanje);
					artikli.add(auto);

				}
				if (tipArtikl.equals("Usluga")) {
					Long idArtikla = resultSet.getLong("idArtikla");
					String opis = resultSet.getString("opis");
					String naziv = resultSet.getString("naziv");
					BigDecimal cijena = resultSet.getBigDecimal("cijena");
					String stanje = resultSet.getString("stanje");
					Stanje opetStanje = Stanje.valueOf(stanje.toUpperCase());

					Usluga usluga = new Usluga(idArtikla, opis, naziv, cijena, opetStanje);
					artikli.add(usluga);
				}

				if (tipArtikl.equals("Stan")) {
					Long idArtikla = resultSet.getLong("idArtikla");
					String opis = resultSet.getString("opis");
					String naziv = resultSet.getString("naziv");
					BigDecimal cijena = resultSet.getBigDecimal("cijena");
					Integer kvadratura = resultSet.getInt("kvadratura");
					String stanje = resultSet.getString("stanje");
					Stanje opetStanje = Stanje.valueOf(stanje.toUpperCase());

					Stan stan = new Stan(idArtikla, opis, naziv, cijena, kvadratura, opetStanje);
					artikli.add(stan);
				}

			}

		}
		return artikli;

	}

	public static List<Artikl> dohvatArtikala()
			throws FileNotFoundException, SQLException, IOException, BazaPodatakaException {

		List<Artikl> artikli = new ArrayList<Artikl>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id as idArtikla, naslov, opis, cijena, snaga,\r\n"
							+ " kvadratura, stanje.naziv as stanje, tipArtikla.naziv as tipArtikla\r\n"
							+ "FROM artikl inner join\r\n" + " stanje on stanje.id = artikl.idStanje inner join\r\n"
							+ " tipArtikla on tipArtikla.id = artikl.idTipArtikla\r\n" + "");

			Statement naredba = connection.createStatement();
			ResultSet resultSet = naredba.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				String tipArtikl = resultSet.getString("tipArtikla");

				if (tipArtikl.equals("Automobil")) {
					Long idArtikla = resultSet.getLong("idArtikla");
					String opis = resultSet.getString("opis");
					String naziv = resultSet.getString("naziv");
					BigDecimal cijena = resultSet.getBigDecimal("cijena");
					BigDecimal snagaKs = resultSet.getBigDecimal("snaga");
					String stanje = resultSet.getString("stanje"); // "NOVO"
					Stanje opetStanje = Stanje.valueOf(stanje.toUpperCase()); // STANJE NOVO

					Automobil auto = new Automobil(idArtikla, opis, naziv, cijena, snagaKs, opetStanje);
					artikli.add(auto);

				}
				if (tipArtikl.equals("Usluga")) {
					Long idArtikla = resultSet.getLong("idArtikla");
					String opis = resultSet.getString("opis");
					String naziv = resultSet.getString("naziv");
					BigDecimal cijena = resultSet.getBigDecimal("cijena");
					String stanje = resultSet.getString("stanje");
					Stanje opetStanje = Stanje.valueOf(stanje.toUpperCase());

					Usluga usluga = new Usluga(idArtikla, opis, naziv, cijena, opetStanje);
					artikli.add(usluga);
				}

				if (tipArtikl.equals("Stan")) {
					Long idArtikla = resultSet.getLong("idArtikla");
					String opis = resultSet.getString("opis");
					String naziv = resultSet.getString("naziv");
					BigDecimal cijena = resultSet.getBigDecimal("cijena");
					Integer kvadratura = resultSet.getInt("kvadratura");
					String stanje = resultSet.getString("stanje");
					Stanje opetStanje = Stanje.valueOf(stanje.toUpperCase());

					Stan stan = new Stan(idArtikla, opis, naziv, cijena, kvadratura, opetStanje);
					artikli.add(stan);
				}
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}

		return artikli;

	}

	public static List<Korisnik> dohvatKorisnika()
			throws FileNotFoundException, SQLException, IOException, BazaPodatakaException {

		List<Korisnik> korisnici = new ArrayList<Korisnik>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct korisnik.id as idKorisnika, email, telefon, ime, prezime,\r\n"
							+ " korisnik.naziv, web, tipKorisnika.naziv as tipKorisnika\r\n"
							+ "FROM korisnik inner join\r\n"
							+ " tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika\r\n" + "");

			Statement naredba = connection.createStatement();
			ResultSet resultSet = naredba.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				String tipKorisnik = resultSet.getString("tipKorisnika");

				if (tipKorisnik.equals("PrivatniKorisnik")) {
					Long idArtikla = resultSet.getLong("idKorisnika");
					String email = resultSet.getString("email");
					String telefon = resultSet.getString("telefon");
					String ime = resultSet.getString("ime");
					String prezime = resultSet.getString("prezime");

					PrivatniKorisnik privatniKorisnik = new PrivatniKorisnik(idArtikla, email, telefon, ime, prezime);
					korisnici.add(privatniKorisnik);

				}
				if (tipKorisnik.equals("PoslovniKorisnik")) {
					Long idKorisnika = resultSet.getLong("idKorisnika");
					String email = resultSet.getString("email");
					String telefon = resultSet.getString("telefon");
					String naziv = resultSet.getString("naziv");
					String web = resultSet.getString("web");

					PoslovniKorisnik poslovniKorisnik = new PoslovniKorisnik(idKorisnika, email, telefon, naziv, web);
					korisnici.add(poslovniKorisnik);
				}

			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}

		return korisnici;

	}

	private static Connection spajanjeNaBazu() throws FileNotFoundException, IOException, SQLException {
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("URL");
		String korisnickoIme = svojstva.getProperty("Username");
		String lozinka = svojstva.getProperty("Password");
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		return veza;
	}

	public static void spremiNoviStan(Stan stan) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, kvadratura, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 3);");
			preparedStatement.setString(2, stan.getOpis());
			preparedStatement.setString(1, stan.getNaziv());
			preparedStatement.setBigDecimal(3, stan.getCijena());
			preparedStatement.setInt(4, stan.getKvadratura());
			preparedStatement.setLong(5, (stan.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNoviAutomobil(Automobil automobil) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, snaga, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 1);");
			preparedStatement.setString(2, automobil.getOpis());
			preparedStatement.setString(1, automobil.getNaziv());
			preparedStatement.setBigDecimal(3, automobil.getCijena());
			preparedStatement.setBigDecimal(4, automobil.getSnagaKs());
			preparedStatement.setLong(5, (automobil.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate(); //
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNovaUsluga(Usluga usluga) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(2, usluga.getOpis());
			preparedStatement.setString(1, usluga.getNaziv());
			preparedStatement.setBigDecimal(3, usluga.getCijena());
			preparedStatement.setLong(4, (usluga.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNovogPrivatnogKorisnika(PrivatniKorisnik privatniKorisnik) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Email, Telefon, Ime, Prezime, idTipKorisnika) " + "values (?, ?, ?, ?, 1);");
			preparedStatement.setString(1, privatniKorisnik.getEmail());
			preparedStatement.setString(2, privatniKorisnik.getTelefon());
			preparedStatement.setString(3, privatniKorisnik.getIme());
			preparedStatement.setString(4, privatniKorisnik.getPrezime());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNovogPoslovnogKorisnika(PoslovniKorisnik poslovniKorisnik) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Email, Telefon, Naziv, Web, idTipKorisnika) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(1, poslovniKorisnik.getEmail());
			preparedStatement.setString(2, poslovniKorisnik.getTelefon());
			preparedStatement.setString(3, poslovniKorisnik.getNaziv());
			preparedStatement.setString(4, poslovniKorisnik.getWeb());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNovuProdaju(Prodaja prodaja)
			throws SQLException, FileNotFoundException, IOException, BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into prodaja(idartikl, idkorisnik, datumobjave) values (?,?,?)");
			preparedStatement.setLong(1, prodaja.getArtikli().getId());
			preparedStatement.setLong(2, prodaja.getKorisnik().getId());
			preparedStatement.setDate(3, Date.valueOf(prodaja.getDatumObjave()));
			preparedStatement.executeUpdate();

		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}
}