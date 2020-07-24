package dominio;

public class InsumoLiquido extends Insumo {
	
	private Float densidad;

	public InsumoLiquido() {
		super();
	}
	
	public Float pesoPorUnidad(Float volumen) {
		return this.densidad*volumen;
	}
}
