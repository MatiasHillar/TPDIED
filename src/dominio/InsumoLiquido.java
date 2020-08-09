package dominio;

public class InsumoLiquido extends Insumo {
	
	private Float densidad;

	public InsumoLiquido() {
		super();
	}
	
	public Float pesoPorUnidad() {
		if(this.getUnidadMedida().getSimbolo().equals("M3")) return this.densidad;
		else return this.densidad/1000;
	}

	public Float getDensidad() {
		return densidad;
	}

	public void setDensidad(Float densidad) {
		this.densidad = densidad;
	}
}
