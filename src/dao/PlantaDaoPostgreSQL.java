package dao;

import java.util.ArrayList;
import java.util.List;

import dao.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dominio.Planta;
import dominio.Stock;
import dao.StockDao;

public class PlantaDaoPostgreSQL implements PlantaDao{

	private static final String UPDATE_PLANTA = 
			"UPDATE PLANTA SET NOMBRE = ?"
			+ "WHERE ID = ?";

	private static final String INSERT_PLANTA = 
			"INSERT INTO PLANTA VALUES(NOMBRE = ?)";
	
	private static final String DELETE_PLANTA = 
			"DELETE FROM PLANTA WHERE ID = ?";
	
	private static final String SELECT_ALL_PLANTA =
			"SELECT * FROM PLANTA";

	StockDao stockDao = new StockDaoPostgreSQL();
	
	@Override
	public Planta saveOrUpdate(Planta p) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(p.getId()!=null){
				pstmt = conn.prepareStatement(UPDATE_PLANTA);
				pstmt.setString(1, p.getNombre());
				pstmt.setInt(2, p.getId());
			}
			else {
				pstmt = conn.prepareStatement(INSERT_PLANTA);
				pstmt.setString(1, p.getNombre());
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
		return p;
	}

	@Override
	public Planta buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Planta> buscarTodas() {
		List<Planta> lista = new ArrayList<Planta>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Planta p = new Planta();
		try {
			pstmt = conn.prepareStatement(SELECT_ALL_PLANTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				p.setId(rs.getInt("ID"));
				p.setNombre(rs.getString("NOMBRE"));
				//podria pasar la conexion como argumento
				p.setListaStock(stockDao.buscarPorPlanta(rs.getInt("ID"),conn));
				lista.add(p);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
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

	@Override
	public void borrar(Integer id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_PLANTA);
			pstmt.setInt(1, id);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
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