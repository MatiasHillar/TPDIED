package dao;

import java.util.List;

import Dominio.Pedido;

public interface PedidoDao {

	public Pedido saveorUpdate(Pedido p);
	public Pedido buscar(Integer nroPedido);
	//busca todas las que están en estado PROCESADA
	public List<Pedido> filtrarProcesadas();
	//busca todas las que están en estado CREADA
	public List<Pedido> filtrarCreadas();
	
	
}
