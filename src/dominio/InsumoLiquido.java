package dominio;

public class InsumoLiquido extends Insumo {
	
	private Float densidad;

	public InsumoLiquido() {
		super();
	}
	
	public Float pesoPorUnidad(Float volumen) {
		return this.densidad*volumen;
	}

	public Float getDensidad() {
		return densidad;
	}

	public void setDensidad(Float densidad) {
		this.densidad = densidad;
	}
}
