package dominio;

public abstract class Insumo {
	protected String nombre;
	protected String descripcion;
	protected Unidad unidadMedida;
	protected Float costo;
	protected Integer id;
	protected Float peso;
	protected Float cantidadTotal;
	
	
	public Insumo() {
		super();
	}
	public Float getPeso() {
		return pesoPorUnidad();
	};
	
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Unidad getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(Unidad unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public Float getCosto() {
		return costo;
	}
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	public Float pesoPorUnidad() {
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Float cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Insumo other = (Insumo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
