package dominio;

public abstract class Insumo {
	protected String nombre;
	protected String descripcion;
	protected Unidad unidadMedida;
	protected Float costo;
	protected Integer id;
	protected Float peso;
	
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
}
