package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;

import hr.java.vjezbe.entitet.Kategorija;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.CijenaJePreniskaException;
import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * @author Nikola Augustinoviæ
 *
 */
public class Glavna {

	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	private static Map<Kategorija<Artikl>, List<Artikl>> mapaPojedineKategorije = new HashMap<>();

	/**
	 * Glavna metoda za izvršavanje, u kojoj se radi unos podataka i pozivaju
	 * dodatne metode.
	 * 
	 * @param args - ulazni parametri kod pokretanja glavne metode
	 *
	 * @author Nikola Augustinoviæ
	 * @throws CijenaJePreniskaException
	 * @throws NemoguceOdreditiGrupuOsiguranjaException
	 *
	 */
	public static void main(String[] args) throws NemoguceOdreditiGrupuOsiguranjaException, CijenaJePreniskaException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Unesi koliko korisnika želiš unijeti: ");
		int num = sc.nextInt();
		sc.nextLine();
		List<Korisnik> korisnici = new ArrayList<Korisnik>();
		for (int i = 0; i < num; i++) {
			korisnici.add(unosKorisnika(sc, i));
		}

		System.out.println("Unesi koliko kategorija želiš unijeti: ");
		num = sc.nextInt();
		sc.nextLine();
		List<Kategorija<Artikl>> kategorije = new ArrayList<Kategorija<Artikl>>();
		for (int i = 0; i < num; i++) {
			kategorije.add(unosKategorija(sc, i));
		}

//		System.out.println("Unesi koliko artikala želiš unijeti: ");
//		num = sc.nextInt();
//		sc.nextLine();
//		List<Artikl> artikli = new ArrayList<Artikl>();
//		for (int i = 0; i < num; i++) {
//			artikli.add(unosArtikla(sc, i));
//
//		}

		System.out.println("Unesite broj artikala koji su aktivno na prodaju: ");
		num = sc.nextInt();
		sc.nextLine();
		List<Prodaja> prodaja = new ArrayList<Prodaja>();
		for (int i = 0; i < num; i++) {
			prodaja.add(unosProdaje(sc, i, kategorije, korisnici));
		}

		sc.close();

		long startTime = System.currentTimeMillis();

		// ispis mape forom
		for (Entry<Kategorija<Artikl>, List<Artikl>> map : mapaPojedineKategorije.entrySet()) {
			System.out.println(map.getKey().getNaziv());
			for (Artikl art : map.getValue()) {
				System.out.println(art.tekstOglasa());
			}
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Ispis for petlje je trajao: " + (endTime - startTime) + "ms");

		long startMjerenja = System.currentTimeMillis();

		// ispis mape kategorija lambdom
		mapaPojedineKategorije.entrySet().forEach(p -> {
			System.out.println(p.getKey().getNaziv() + " ima artikle: ");
			p.getValue().stream().forEach(m -> {
				try {
					System.out.print(m.tekstOglasa());
				} catch (NemoguceOdreditiGrupuOsiguranjaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CijenaJePreniskaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

		);
		long krajMjerenja = System.currentTimeMillis();

		System.out.println("Ispis lambde je trajalo: " + (krajMjerenja - startMjerenja) + "ms");
		
		

		for (Kategorija<Artikl> cijenaArtikla : kategorije) {
			cijenaArtikla.vracajListu().stream()
					.filter(p -> p.getCijena().intValue() > 50 && p.getCijena().intValue() < 100)
					.forEach(s -> System.out.println("Artikl koji ima cijenu izmeðu 50 i 100 je: " + s.getNaziv()));
		}

		for (Kategorija<Artikl> sumaCijene : kategorije) {
			double suma = sumaCijene.vracajListu().stream().mapToDouble(p -> p.getCijena().doubleValue()).sum();
			System.out.println("Suma cijena artikla je: " + suma);
		}
		
		for (Kategorija<Artikl> prosjekCijene : kategorije) {
			OptionalDouble avgCijene = prosjekCijene.vracajListu().stream().mapToDouble(p -> p.getCijena().doubleValue())
					.average();
			System.out.println(
					"Prosjek cijena artikla " + prosjekCijene.getNaziv() + " je " + avgCijene.getAsDouble());
		}
		

	}

	/**
	 * 
	 * Unos podataka o korisniku
	 * 
	 * @param sc Scanner - objekt za unos podataka preko tastature
	 * @param i  int - redni broj korisnika koji služi za ispis upita o novom
	 *           korisniku
	 * 
	 * @return PrivatniKorisnik - objekt kreiran iz unesenih podataka ako je odabran
	 * @return PoslovniKorisnik - objekt kreiran iz unesenih podataka ako je odabran
	 * 
	 * @author Nikola Augustinoviæ
	 */
	private static Korisnik unosKorisnika(Scanner sc, int i) {
		String tipKorisnika = " ";
		do {
			System.out.println("Unesi tip " + (i + 1) + " osobe" + "\n" + " 1. Privatni" + "\n" + " 2. Poslovni");

			tipKorisnika = sc.nextLine();
			if (tipKorisnika.equals("1")) {
				System.out.println("Unesi ime " + (i + 1) + " privatne osobe");
				String ime = sc.nextLine();

				System.out.println("Unesi prezime " + (i + 1) + " privatne osobe");
				String prezime = sc.nextLine();

				System.out.println("Unesi email " + (i + 1) + " privatne osobe");
				String email = sc.nextLine();

				System.out.println("Unesi telefon " + (i + 1) + " privatne osobe");
				String telefon = sc.nextLine();

				PrivatniKorisnik privatni = new PrivatniKorisnik(email, telefon, ime, prezime);

				return privatni;
			}

			if (tipKorisnika.equals("2")) {
				System.out.println("Unesi naziv " + (i + 1) + " poslovne osobe");
				String naziv = sc.nextLine();

				System.out.println("Unesi email " + (i + 1) + " poslovne osobe");
				String email = sc.nextLine();

				System.out.println("Unesi web " + (i + 1) + " poslovne osobe");
				String web = sc.nextLine();

				System.out.println("Unesi telefon " + (i + 1) + " poslovne osobe");
				String telefon = sc.nextLine();

				PoslovniKorisnik poslovni = new PoslovniKorisnik(email, telefon, naziv, web);

				return poslovni;
			}
		} while (tipKorisnika.equals("1") || tipKorisnika.equals("2"));

		return null;

		// dva objekta, privatni i poslovni korisnik
		// dva moguæa unosa
	}

	/**
	 * 
	 * Unos podataka o kategoriji
	 * 
	 * @param sc Scanner - objekt za unos podataka preko tastature
	 * @param i  int - redni broj korisnika koji služi za ispis upita o novom
	 *           korisniku
	 * 
	 * @return Kategrija - objekt kreiran iz unesenih podataka
	 * 
	 * @author Nikola Augustinoviæ
	 * @param <T>
	 * @param <T>
	 * @param <T>
	 */
	private static Kategorija<Artikl> unosKategorija(Scanner sc, int i) {

		System.out.println("Unesi naziv " + (i + 1) + " kategorije: ");
		String naziv = sc.nextLine();

		System.out.println("Unesite broj artikala koji želite unijeti za unesenu kategoriju: ");
		Integer broj = sc.nextInt();
		sc.nextLine();

//		boolean ispravanUnos = false;
//		Integer tipArtikla = 0;
//		do {
//			System.out.println(
//					"Unesi tip " + (i + 1) + " artikla" + "\n" + " 1. Usluga" + "\n" + " 2. Auto" + "\n" + " 3. Stan");
//
//			try {
//				tipArtikla = sc.nextInt();
//				sc.nextLine();
//				if (tipArtikla < 1) {
//					logger.error("Broj artikala ne smije biti manji od 1");
//				} else
//					ispravanUnos = true;
//
//			} catch (Exception ex) { // InputMismatchException
//				logger.error("Krivi unos broja artikla " + ex);
//				sc.nextLine();
//			}
//
//		} while (ispravanUnos == false);
//		logger.info("Ispravan unos broja artikala");
//		

		Kategorija<Artikl> kategorija = new Kategorija<Artikl>(naziv);

		for (int j = 0; j < broj.intValue(); j++) {
			Artikl artikl = unosArtikla(sc, i);
			kategorija.dodajArtikl(artikl);

		}

		mapaPojedineKategorije.put(kategorija, kategorija.vracajListu());
		//
		kategorija.vracajListu().stream().forEach(
				p -> System.out.println("Artikl: " + p.getNaziv() + " pripada kategoriji " + kategorija.getNaziv()));

		return kategorija;
	}

	/**
	 * 
	 * Unos podataka o artiklu
	 * 
	 * @param sc Scanner - objekt za unos podataka preko tastature
	 * @param i  int - redni broj korisnika koji služi za ispis upita o novom
	 *           korisniku
	 * 
	 * @return Usluga - objekt kreiran iz unesenih podataka ako je odabran
	 * @return Automobil - objekt kreiran iz unesenih podataka ako je odabran
	 * @return Stan - objet kreiran iz unesenih podataka ako je odabran
	 * 
	 * @author Nikola Augustinoviæ
	 */
	private static Artikl unosArtikla(Scanner sc, int i) {

		String tipArtikla = " ";

		do {
			System.out.println("Unesi broj" + (i + 1) + "artikla za unesenu kategoriju" + "\n" + "1. Usluga" + "\n"
					+ " 2. Auto" + "\n" + " 3. Stan");
			tipArtikla = sc.nextLine();

			if (tipArtikla.equals("1")) {
				System.out.println("Unesi naziv " + (i + 1) + ". usluge");
				String opis = sc.nextLine();

				System.out.println("Unesi opis " + (i + 1) + ". usluge");
				String naziv = sc.nextLine();

				boolean ispravanUnos = false;
				BigDecimal cijena = null;

				do {

					System.out.println("Unesi cijenu " + (i + 1) + ". usluge");
					try {
						cijena = sc.nextBigDecimal();
						sc.nextLine();
						ispravanUnos = true;
					} catch (Exception ex) {
						logger.error("Krivi unos cijene usluge " + ex);

					}
				} while (ispravanUnos == false);
				logger.info("Ispravan unos cijene usluge ");

				System.out.println("Unesi stanje " + (i + 1) + " usluge");
				Stanje unosUsluge = unosStanja(sc);

				Usluga novaUsluga = new Usluga(opis, naziv, cijena, unosUsluge);
				return novaUsluga;
			}
			if (tipArtikla.equals("2")) {
				System.out.println("Unesi naziv " + (i + 1) + ". auta");
				String opis = sc.nextLine();

				System.out.println("Unesi opis " + (i + 1) + ". auta");
				String naziv = sc.nextLine();

				System.out.println("Unesi cijenu " + (i + 1) + ". auta");
				BigDecimal cijena = sc.nextBigDecimal();
				sc.nextLine();

				System.out.println("Unesi marku" + (i + 1) + " auta");
				String marka = sc.next();

				boolean unos = false;
				BigDecimal snagaKs = null;
				do {

					System.out.println("Unesi snagu u ks " + (i + 1) + ". auta");

					try {
						snagaKs = sc.nextBigDecimal();
						sc.nextLine();
						unos = true;
					} catch (Exception ex) {

						logger.error("Krivi unos konjskih snaga auta ");
					}
				} while (unos == false);
				logger.info("Ispravan unos ks auta ");

				System.out.println("Unesi stanje " + (i + 1) + ". auta");
				Stanje stanjeAuta = unosStanja(sc);

				Automobil noviAuto = new Automobil(opis, naziv, cijena, snagaKs, stanjeAuta, marka);
				return noviAuto;
			}

			if (tipArtikla.equals("3")) {
				Stan stanovi = unosStanova(sc, i);
				return stanovi;
			}
		} while (tipArtikla.equals("1") || tipArtikla.equals("2") || tipArtikla.equals("3"));

		return null;
	}

	/**
	 * 
	 * Unos podataka o prodaji
	 * 
	 * @param sc         Scanner - objekt za unos podataka preko tastature
	 * @param i          int - redni broj korisnika koji služi za ispis upita o
	 *                   novom korisniku
	 * @param kategorije List<Kategorija> - lista kategorija iz kojih se odabire
	 *                   jedan artikl
	 * @param korisnici  List<Korisnik> - lista korisnika iz kojih se odabire jedan
	 *                   korisnik
	 * 
	 * @return Prodaja - objekt kreiran iz unesenih podataka
	 * 
	 * @author Nikola Augustinoviæ
	 */
	private static Prodaja unosProdaje(Scanner sc, int i, List<Kategorija<Artikl>> kategorije,
			List<Korisnik> korisnici) {

		boolean ispravanUnosKorisnika = false;
		Integer korisnikProdaje = 0;
		do {
			System.out.println("Odaberi korisnika: ");
			try {
				for (int j = 0; j < korisnici.size(); j++) {
					System.out
							.println((j + 1) + " " + korisnici.get(j).getEmail() + " " + korisnici.get(j).getTelefon());
				}
				sc.nextInt();
				sc.nextLine();
				ispravanUnosKorisnika = false;
			} catch (Exception ex) {
				logger.error("Krivi odabir korisnika ");
			}
		} while (korisnikProdaje < 1 || korisnikProdaje > korisnici.size());
		Korisnik odabraniKorisnik = korisnici.get(korisnikProdaje - 1);

		boolean ispravanUnosProdaje = false;
		Integer kategorijaProdaje = 0;
		do {
			System.out.println("Odaberi kategoriju: ");
			try {
				for (int j = 0; j < kategorije.size(); j++) {
					System.out.println((j + 1) + " " + kategorije.get(j).getNaziv());
				}
				sc.nextInt();
				sc.nextLine();
				ispravanUnosProdaje = true;
			} catch (Exception ex) {
				logger.error("Krivi odabir kategorija ");
			}
		} while (kategorijaProdaje < 1 || kategorijaProdaje > kategorije.size());

		Kategorija<Artikl> odabranaKategorija = kategorije.get(kategorijaProdaje - 1);

		boolean ispravanUnosArtikla = false;
		Integer artiklProdaje = 0;
		do {
			System.out.println("Odaberi artikl: ");
			try {
				i = 0;
				for (Artikl odabraniArtikl : odabranaKategorija.vracajListu()) {
					System.out.println((i + 1) + ". " + odabraniArtikl.getNaziv());
					i++;
				}
				sc.nextInt();
				sc.nextLine();
				ispravanUnosProdaje = true;
			} catch (Exception ex) {
				logger.error("Krivi odabir artikla ");
			}
		} while (artiklProdaje < 1 || artiklProdaje > kategorije.size());

		ArrayList<Artikl> listaArtikala = new ArrayList<>(odabranaKategorija.vracajListu()); // ili foreach nad setom
		Artikl odabraniArtikl = listaArtikala.get(artiklProdaje - 1);

		System.out.println("Unesi datum objave prodaje");

		String datumProdajeString = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		LocalDate datumProdaje = LocalDate.parse(datumProdajeString, formatter);

		Prodaja novaProdaja = new Prodaja(odabraniArtikl, odabraniKorisnik, datumProdaje);

		return novaProdaja;
	}

	/**
	 * Unos podataka o stanovima
	 * 
	 * @param sc Scanner - objekt za unos podataka preko tastature
	 * @param i  int - redni broj korisnika koji služi za ispis upita o novom
	 *           korisniku
	 * @return Stan - objekt kreiran iz unesenih podataka
	 * 
	 * @author Nikola Augustinoviæ
	 */
	private static Stan unosStanova(Scanner sc, int i) {

		System.out.println("Unesi naziv " + (i + 1) + " oglasa stana: ");
		String naziv = sc.nextLine();

		System.out.println("Unesi opis " + (i + 1) + " oglasa stana: ");
		String opis = sc.nextLine();

		boolean ispravanUnos = false;
		BigDecimal cijena = null;

		do {

			System.out.println("Unesi cijenu " + (i + 1) + ". oglasa stana");
			try {
				cijena = sc.nextBigDecimal();
				sc.nextLine();
				ispravanUnos = true;
			} catch (Exception ex) {
				logger.error("Krivi unos cijene stana " + ex);

			}
		} while (ispravanUnos == false);
		logger.info("Ispravan unos cijene stana ");

		boolean ispravanUnosKvadrature = false;
		int kvadratura = 0;
		do {
			System.out.println("Unesi kvadraturu " + (i + 1) + " oglasa stana");
			try {
				kvadratura = sc.nextInt();
				sc.nextLine();
				ispravanUnosKvadrature = true;

			} catch (Exception ex) {
				logger.error("Krivi unos broja artikla " + ex);
				sc.nextLine();
			}

		} while (ispravanUnosKvadrature == false);
		logger.info("Ispravan unos broja artikala");

		System.out.println("Unesi stanje " + (i + 1) + " stana");
		Stanje unosStanjaStana = unosStanja(sc);

		Stan stanovi = new Stan(opis, naziv, cijena, kvadratura, unosStanjaStana);
		return stanovi;

	}

	/**
	 * Unos stanja usluge, automobila ili stana
	 * 
	 * @param sc Scanner - objekt za unos podataka preko tastature
	 * @return Stanje - objekt kreiran iz unesenih podataka
	 * 
	 * @author Nikola Augustinoviæ
	 */
	private static Stanje unosStanja(Scanner sc) {
		for (int i = 0; i < Stanje.values().length; i++) {
			System.out.println((i + 1) + ". " + Stanje.values()[i]);
		}
		Integer stanjeRedniBroj = null;
		while (true) {
			System.out.print("Odabir stanja artikla >> ");
			stanjeRedniBroj = unosInt(sc);
			if (stanjeRedniBroj >= 1 && stanjeRedniBroj <= Stanje.values().length) {
				return Stanje.values()[stanjeRedniBroj - 1];
			} else {
				System.out.println("Neispravan unos!");
			}
		}

	}

	/**
	 * Provjera unosa brojèane vrijednosti. Ako se ne unese broj, hvata se greška
	 * 
	 * @param sc Scanner - objekt za unos podataka preko tastature
	 * @return Integer - broj unosa
	 * 
	 * @author Nikola Augustinoviæ
	 */
	private static Integer unosInt(Scanner sc) {

		Integer unos = null;
		do {
			try {
				unos = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException ex) {
				logger.error("Nije unesen broj ");
				System.out.println("Treba unijeti cijeli broj umjesto slova ");
			}

		} while (unos == null);
		return unos;
	}

	public static void ispisSortiranihArtikala(Kategorija<Artikl> kategorija) {

		kategorija.vracajListu().stream().sorted((o1, o2) -> o1.getNaziv().compareTo(o2.getNaziv())).forEach(p -> {
			try {
				System.out.println(p.tekstOglasa());
			} catch (NemoguceOdreditiGrupuOsiguranjaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CijenaJePreniskaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public static void ispisSortiranihArtikalaPoCijeni(Kategorija<Artikl> kategorija) {

		kategorija.vracajListu().stream().sorted((o1, o2) -> o1.getCijena().compareTo(o2.getCijena())).forEach(p -> {
			try {
				System.out.println(p.tekstOglasa());
			} catch (NemoguceOdreditiGrupuOsiguranjaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CijenaJePreniskaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

}