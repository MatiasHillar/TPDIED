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

public class CamionDaoPostgreSQL implements CamionDao{

	private static final String SELECT_ALL_CAMION = 
			/*"SELECT C.PATENTE, M.MARCA, M.MODELO, C.KM_RECORRIDOS, C.COSTOXKM,"
			+ "C.COSTOXHS, C.FECHA_COMPRA, C.ID_MODELO, C.ID_PLANTA"
			+ "FROM CAMION C, MODELO M"
			+ "WHERE C.ID_MODELO = M.ID";*/
			"SELECT * FROM CAMION";
	
	private static final String SELECT_CAMION =
			"SELECT * FROM CAMION"
			+ "WHERE PATENTE = ?";
	
	private static final String UPDATE_CAMION =
			"UPDATE CAMION SET PATENTE = ?, KM_RECORRIDOS = ?,COSTOXKM = ?, COSTOXHS = ?,"
			+ " FECHA_COMPRA = ?, ID_MODELO = ?, ID_PLANTA = ? "
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
		//consultar por PATENTE O ID
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
			  	/*	VER COMO FORMATEAR FECHA A LOCALDATE Y CONSISTENCIA TABLAS/MODELO
			  	c.setModelo(rs.getInt("ID_MODELO"));
				c.setPatente(rs.getInt("ID_PLANTA"));
				*/
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
		String sentencia = prepararSentencia(atributos);
		List<Camion> lista = new ArrayList<Camion>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
				/*c.setModelo(rs.getInt("ID_MODELO"));
				c.setPatente(rs.getInt("ID_PLANTA"));
				*/
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
		String p1 = "SELECT ";
		String p2 = " FROM CAMION WHERE ";
		String atribs = "";
		String values = "";
		for (Map.Entry<String,?> entry : atrib.entrySet()) {
			if(atribs.isEmpty()) atribs.concat(entry.getKey());
			else atribs.concat(","+entry.getKey());
			if(values.isEmpty()) values.concat(entry.getKey()+"="+entry.getValue());
			else values.concat(","+entry.getKey()+"="+entry.getValue());
			
		}
		return p1 + atribs + p2 + values; 
	}
	
	private LocalDate formatearFecha(String fecha) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate date = LocalDate.parse(fecha, f);
		return date;
	}
}
