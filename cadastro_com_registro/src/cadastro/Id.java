package cadastro;

public final class Id {	
	private final String id;
	
	public Id(int id) {	
		this.id = String.format("%06d", id).trim();			
	}		
	
	public String getId() {
		return id;
	}		
	
}