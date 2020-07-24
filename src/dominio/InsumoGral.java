package dominio;

public class InsumoGral extends Insumo {

	public InsumoGral() {
		super();
	}
	
	@Override
	public Float pesoPorUnidad() {
		return this.peso;
	}

}
