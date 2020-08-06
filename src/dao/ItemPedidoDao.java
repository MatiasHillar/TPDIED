package dao;

import java.sql.Connection;
import java.util.List;

import dominio.ItemPedido;

public interface ItemPedidoDao {
	
	public ItemPedido saveOrUpdate(ItemPedido item);
	public ItemPedido buscarPorInsumo(Integer idInsumo);
	public ItemPedido buscarPorPedido(Integer idPedido);
	public ItemPedido buscarPorInsumoPedido(Integer idInsumo, Integer idPedido);
	public void borrar(Integer idInsumo, Integer idPedido);
	public List<ItemPedido> selectItems(Integer nro_pedido, Connection conn);
}
