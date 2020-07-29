package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.utils.DB;
import dominio.ItemPedido;

public class ItemPedidoDaoPostgreSQL {

	private static final String UPDATE_ITEM_PEDIDO = 
			"UPDATE ITEM_PEDIDO"
			+ "SET CANTIDAD = ?"
			+ "WHERE ID_INSUMO = ? AND NRO_PEDIDO = ?";
	//CHEQUEAR CHECKNULL
//	public ItemPedido saveOrUpdate(ItemPedido i) {
//		Connection conn = DB.getConexion();
//		PreparedStatement pstmt = null;
//		try {
//			if()
//			pstmt = conn.prepareStatement(UPDATE_ITEM_PEDIDO);
//			pstmt.setInt(1, i.getCtidad());
//			pstmt.setInt(2, i.getInsumo().getId());
//			pstmt.setInt(3, i.getPedido().getNroPedido());
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
//	
}
