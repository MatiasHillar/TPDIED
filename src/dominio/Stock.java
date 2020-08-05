package dominio;

public class Stock {
	private Integer id;
	private Insumo insumo;
	private Float ctidad;
	private Float puntoRepo;
	private Planta p;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	public Float getCtidad() {
		return ctidad;
	}
	public void setCtidad(Float ctidad) {
		this.ctidad = ctidad;
	}
	public Float getPuntoRepo() {
		return puntoRepo;
	}
	public void setPuntoRepo(Float puntoRepo) {
		this.puntoRepo = puntoRepo;
	}
	public Planta getP() {
		return p;
	}
	public void setP(Planta p) {
		this.p = p;
	}

	
}
