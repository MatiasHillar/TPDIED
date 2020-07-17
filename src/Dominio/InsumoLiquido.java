package dominio;

public class InsumoLiquido extends Insumo {
	private Float densidad;

	public Float pesoPorUnidad(Float volumen) {
		return this.densidad*volumen;
	}
}
