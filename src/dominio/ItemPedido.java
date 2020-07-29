package dominio;

public class ItemPedido {
	private Insumo insumo;
	private Integer ctidad;
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
	public Integer getCtidad() {
		return ctidad;
	}
	public void setCtidad(Integer ctidad) {
		this.ctidad = ctidad;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	

}
