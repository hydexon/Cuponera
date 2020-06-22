package sv.edu.udb.Cuponera.pojo;

public class Rubro {
	private int id;
	private String rubro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Rubro))
			return false;
		
		Rubro r = (Rubro)obj;
		return (this.id == r.id);
	}
	
	
}
