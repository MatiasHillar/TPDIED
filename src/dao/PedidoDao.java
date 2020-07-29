package dao;

import java.util.List;

import dominio.Pedido;

public interface PedidoDao {

	public Pedido saveOrUpdate(Pedido p);
	public Pedido buscar(Integer nroPedido);
	//busca todas las que están en estado PROCESADA
	public List<Pedido> filtrarProcesadas();
	//busca todas las que están en estado CREADA
	public List<Pedido> filtrarCreadas();

	
}
