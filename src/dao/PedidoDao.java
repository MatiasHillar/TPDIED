package dao;
	
import java.sql.Connection;
import java.util.List;

import dominio.Pedido;
import dominio.Planta;

public interface PedidoDao {

	public Pedido saveOrUpdate(Pedido p);
	public Pedido buscar(Integer nroPedido, Connection conn);
	//busca todas las que están en estado PROCESADA
	public List<Pedido> filtrarProcesadas();
	//busca todas las que están en estado CREADA
	public List<Pedido> filtrarCreadas();
	public List<Planta> checkPlantas(Integer nro_pedido);
	public void borrar(Integer nro_pedido);

	
}
