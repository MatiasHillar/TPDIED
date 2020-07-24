package dominio;

public abstract class Insumo {
	protected String descripcion;
	protected Unidad unidadMedida;
	protected Float costo;
	protected Integer id;
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
}
