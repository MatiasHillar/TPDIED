package dominio;

public class Stock {
	private Insumo insumo;
	private Float ctidad;
	private Float puntoRepo;
	private Planta p;
	
	public Stock() {
		super();
	}
	
	public Stock(Insumo insumo, Float ctidad, Float puntoRepo, Planta p) {
		super();
		this.insumo = insumo;
		this.ctidad = ctidad;
		this.puntoRepo = puntoRepo;
		this.p = p;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((insumo == null) ? 0 : insumo.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
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
		if (insumo == null) {
			if (other.insumo != null)
				return false;
		} else if (!insumo.equals(other.insumo))
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
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
