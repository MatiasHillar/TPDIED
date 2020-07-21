package dao;

import Dominio.ItemPedido;

public interface ItemPedidoDao {

	public ItemPedido saveOrUpdate(ItemPedido item);
	public ItemPedido buscarPorInsumo(Integer idInsumo);
	public ItemPedido buscarPorPedido(Integer idPedido);
	public ItemPedido buscarPorInsumoPedido(Integer idInsumo, Integer idPedido);
	public Void borrar(Integer idInsumo, Integer idPedido);
}
