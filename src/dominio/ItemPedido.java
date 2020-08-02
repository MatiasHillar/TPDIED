package dominio;

public class ItemPedido {
	private Insumo insumo;
	private Float ctidad;
	private Pedido pedido;
	
	public Float getPrecio() {
		return insumo.getCosto()*this.ctidad;
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
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	@Override
	public String toString() {
		return insumo + "(" + ctidad + " " + insumo.getUnidadMedida().getSimbolo() +") - $" + this.getPrecio();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((insumo == null) ? 0 : insumo.hashCode());
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
		ItemPedido other = (ItemPedido) obj;
		if (insumo == null) {
			if (other.insumo != null)
				return false;
		} else if (!insumo.equals(other.insumo))
			return false;
		return true;
	}
	

}
