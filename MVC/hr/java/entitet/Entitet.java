package hr.java.vjezbe.entitet;

public abstract class Entitet {
	
	private Long id;

	public Entitet(Long id) {
		super();
		this.id = id;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	
	

}
