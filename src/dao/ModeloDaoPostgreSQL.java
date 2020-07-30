package dao;

import dao.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dominio.Modelo;

public class ModeloDaoPostgreSQL implements ModeloDao{
	private static final String UPDATE_MODELO = 
			"UPDATE MODELO SET MARCA = ?"
			+ "WHERE MODELO = ?";
	
	private static final String INSERT_MODELO =
			"INSERT INTO MODELO VALUES (?, ?)";
	
	private static final String SELECT_MODELO = 
			"SELECT * FROM MODELO"
			+ "WHERE MODELO = ?";
	
	private static final String BORRAR_MODELO =
			"DELETE FROM MODELO"
			+ "WHERE MODELO = ?";

	@Override
	public Modelo saveOrUpdate(Modelo m) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(checkNull(m.getModelo(), conn)) {
				pstmt = conn.prepareStatement(UPDATE_MODELO);
				pstmt.setString(1, m.getMarca());
			}
			else {
				pstmt = conn.prepareStatement(INSERT_MODELO);
				pstmt.setString(1, m.getMarca());
				pstmt.setString(2, m.getModelo());
			}
			pstmt.executeUpdate();
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
		return m;
	}

	@Override
	public Modelo buscar(String modelo) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Modelo m = new Modelo();
		try {
			pstmt = conn.prepareStatement(SELECT_MODELO);
			rs = pstmt.executeQuery();
			m.setMarca(rs.getString("MARCA"));
			m.setModelo(rs.getString("MODELO"));
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
		return m;
	}

	@Override
	public void borrar(String modelo) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(BORRAR_MODELO);
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
	
	private boolean checkNull(String modelo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean ret = false;
		try {
			pstmt = conn.prepareStatement(SELECT_MODELO);
			pstmt.setString(1, modelo);
			rs = pstmt.executeQuery();
			ret = rs.first();
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
		return ret;
	}
}

	
