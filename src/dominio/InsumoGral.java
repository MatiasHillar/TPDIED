package dominio;

public class InsumoGral extends Insumo {
	private Float peso;

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}
	@Override
	public Float pesoPorUnidad() {
		return this.peso;
	}

}
