package dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Formatter;
import java.util.stream.Stream;

import dao.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dominio.Camion;
import dominio.Modelo;

public class CamionDaoPostgreSQL implements CamionDao{

	private static final String SELECT_ALL_CAMION = 
			"SELECT * FROM CAMION C, MODELO M"
			+ "WHERE C.ID_MODELO = M.ID";
	
	private static final String SELECT_CAMION =
			"SELECT * FROM CAMION"
			+ "WHERE PATENTE = ?";
	
	private static final String UPDATE_CAMION =
			"UPDATE CAMION SET PATENTE = ?, KM_RECORRIDOS = ?,COSTOXKM = ?, COSTOXHS = ?,"
			+ " FECHA_COMPRA = ?, ID_MODELO = ?"
			+ "WHERE PATENTE = ?";
	
	private static final String DELETE_CAMION =
			"DELETE FROM CAMION"
			+ "WHERE PATENTE = ?";
	
	private static final String INSERT_CAMION =
			"INSERT INTO CAMION(PATENTE, COSTOXKM, COSTOXHS, FECHA_COMPRA"
			+ ", ID_MODELO, ID_PLANTA) VALUES(?, ?, ?, ?, ?, ?)";
	
	@Override
	public Camion saveOrUpdate(Camion c) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try{
			if(c.getPatente()!=null) {
				pstmt = conn.prepareStatement(UPDATE_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setFloat(2, c.getKmRecorridos());
				pstmt.setFloat(3, c.getCostoKm());
				pstmt.setFloat(4, c.getCostoHora());
				pstmt.setString(5, c.getFechaCompra().toString());
				
			}
			else {
				pstmt = conn.prepareStatement(INSERT_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setFloat(2, c.getCostoKm());
				pstmt.setFloat(3, c.getCostoHora());
				pstmt.setString(4, c.getFechaCompra().toString());
				pstmt.setString(5, c.getModelo().getModelo());
			}
			pstmt.executeUpdate();
		}
			catch(SQLException e){
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
		return null;
	}

	@Override
	public void borrar(Camion c) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_CAMION);
			pstmt.setString(1, c.getPatente());
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

	@Override
	public List<Camion> buscarTodos() {
		List<Camion> lista = new ArrayList<Camion>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(SELECT_ALL_CAMION);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();
				c.setPatente(rs.getString("PATENTE"));
				c.setKmRecorridos(rs.getFloat("KM_RECORRIDOS"));
				c.setCostoKm(rs.getFloat("COSTOXKM"));
				c.setCostoHora(rs.getFloat("COSTOXHS"));
			  	c.setFechaCompra(formatearFecha(rs.getString("FECHA_COMPRA")));
			  	c.setModelo(new Modelo(rs.getString("MARCA"),rs.getString("MODELO")));
				lista.add(c);
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

	@Override
	public Camion buscarporPatente(Integer id) {
		return null;
	}

	@Override
	public List<Camion> buscarPorPatributos(Map<String, ?> atributos){
		List<Camion> lista = new ArrayList<Camion>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sentencia = prepararSentencia(atributos);
		try {
			pstmt = conn.prepareStatement(sentencia);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();
				c.setPatente(rs.getString("PATENTE"));
				c.setKmRecorridos(rs.getFloat("KM_RECORRIDOS"));
				c.setCostoKm(rs.getFloat("COSTOXKM"));
				c.setCostoHora(rs.getFloat("COSTOXHS"));
			  	c.setFechaCompra(formatearFecha(rs.getString("FECHA_COMPRA")));
			  	c.setModelo(new Modelo(rs.getString("MARCA"),rs.getString("MODELO")));
				lista.add(c);
			}
		}catch(SQLException e) {
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
	
	private String prepararSentencia(Map<String, ?> atrib) {
		String p1 = "SELECT * FROM CAMION C, MODELO M "
				+ "WHERE C.ID_MODELO = M.NOMBRE";
		String values = "";
		for (Map.Entry<String,?> entry : atrib.entrySet()) {
			values.concat(" AND "+"C."+entry.getKey()+"="+entry.getValue());
			
		}
		return p1.concat(values); 
	}
	
	private LocalDate formatearFecha(String fecha) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate date = LocalDate.parse(fecha, f);
		return date;
	}
}
