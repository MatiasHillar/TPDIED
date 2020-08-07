package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dao.utils.DB;
import dominio.Unidad;

public class UnidadDaoPostgreSQL implements UnidadDao {

	private static final String SELECT_ALL_UNIDAD = 
			"SELECT * FROM UNIDAD";
	
	@Override
	public List<Unidad> buscarTodas() {
		List<Unidad> lista = new ArrayList<Unidad>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Unidad u = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL_UNIDAD);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new Unidad();
				u.setNombre(rs.getString("NOMBRE"));
				u.setSimbolo(rs.getString("SIMBOLO"));
				lista.add(u);
			}
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
		return lista;
	}
}
