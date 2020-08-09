package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.utils.DB;
import dominio.ItemPedido;
import dominio.Pedido;
import dominio.Ruta;

public class ItemPedidoDaoPostgreSQL implements ItemPedidoDao{
	
	private static final String INSERT_ITEM_PEDIDO =
			"INSERT INTO ITEM_PEDIDO VALUES(?, ?, ?)";
	
	private static final String SELECT_ITEMPEDIDO = 
			"SELECT * FROM ITEM_PEDIDO "
			+ "WHERE NRO_PEDIDO = ?";
	
	private static final String DELETE_ITEMPEDIDO = 
			"DELETE FROM ITEM_PEDIDO"
			+ " WHERE ID_INSUMO = ?"
			+ " AND NRO_PEDIDO = ?";
	
	private InsumoDao insumodao = new InsumoDaoPostgreSQL();
//	public ItemPedido saveOrUpdate(ItemPedido i) {
//		Connection conn = DB.getConexion();
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement(INSERT_ITEM_PEDIDO);
//			pstmt.setInt(1, i.getPedido().getNroPedido());
//			pstmt.setInt(2, i.getInsumo().getId());
//			pstmt.setFloat(3, i.getCtidad());
//			pstmt.executeUpdate();
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(conn!=null) conn.close();
//			}
//			catch(SQLException e){
//				e.printStackTrace();
//			}
//		}
//		return i;
//	}
	
	@Override
	public List<ItemPedido> saveOrUpdate(Integer nroPedido, List<ItemPedido> lista, Connection conn) {
		PreparedStatement pstmt = null;
		try {
				pstmt = conn.prepareStatement(INSERT_ITEM_PEDIDO);
				pstmt.setInt(1, nroPedido);
				for(int i = 1; i<=lista.size(); i++) {
					pstmt.setInt(2, lista.get(i).getInsumo().getId());
					pstmt.setFloat(3, lista.get(i).getCtidad());
					pstmt.executeUpdate();
				}
		}
			catch(SQLException e) {
				e.printStackTrace();	
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	@Override
	public List<ItemPedido> selectItems(Integer nro_pedido, Connection conn){
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemPedido i = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ITEMPEDIDO);
			pstmt.setInt(1, nro_pedido);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				i = new ItemPedido();
				i.setCtidad(rs.getFloat("CANTIDAD"));
				i.setInsumo(insumodao.buscar(rs.getInt("ID_INSUMO"), conn));		
				lista.add(i);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public ItemPedido buscarPorInsumo(Integer idInsumo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemPedido buscarPorPedido(Integer idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemPedido buscarPorInsumoPedido(Integer idInsumo, Integer idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrar(Integer idInsumo, Integer idPedido) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_ITEMPEDIDO);
			pstmt.setInt(1, idInsumo);
			pstmt.setInt(2, idPedido);
			pstmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
