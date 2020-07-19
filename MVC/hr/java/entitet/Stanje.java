package hr.java.vjezbe.entitet;

public enum Stanje {
	
	
	NOVO ("novo"), //Stanje.values()[0
	IZVRSNO ("izvrsno"),
	RABLJENO ("rabljeno"),
	NEISPRAVNO("neispravno");

	private String string;
	
	Stanje(String string) {
		this.string = string;
	}

	public String toString() {
		return string;
	}
	
	/*public static Stanje valueOf(String stanje) {
		switch (stanje) {
		case "novo":
			return Stanje.NOVO;
			break;
		case "izvrsno":
			return Stanje.IZVRSNO;
			break;
		case "rabljeno":
			return Stanje.RABLJENO;
			break;
		case "neispravno":
			return Stanje.NEISPRAVNO;
			break;

		default:
			return null;
			break;
		}
		
		
	}*/
	
	

}

