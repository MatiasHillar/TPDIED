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
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import dominio.Camion;
import dominio.Modelo;

public class CamionDaoPostgreSQL implements CamionDao{

	private static final String SELECT_ALL_CAMION = 
			"SELECT * FROM CAMION C, MODELO M"
				      + " WHERE C.ID_MODELO = M.MODELO";
	
	private static final String SELECT_CAMION =
			"SELECT * FROM CAMION C, MODELO M "
			+ "WHERE PATENTE = ?";
	
	private static final String UPDATE_CAMION =
			"UPDATE CAMION SET PATENTE = ?, KM_RECORRIDOS = ?,COSTOXKM = ?, COSTOXHS = ?,"
			+ " FECHA_COMPRA = ?, ID_MODELO = ?"
			+ "WHERE PATENTE = ?";
	
	private static final String DELETE_CAMION =
			"DELETE FROM CAMION"
			+ "WHERE PATENTE = ?";
	
	private static final String INSERT_CAMION =
			"INSERT INTO CAMION(PATENTE, COSTOXKM, COSTOXHS"
			+ ", ID_MODELO) VALUES(?, ?, ?, ?)";
	
	@Override
	public Camion saveOrUpdate(Camion c) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try{
			if(checkNull(c.getPatente(), conn)) {
				pstmt = conn.prepareStatement(UPDATE_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setFloat(2, c.getKmRecorridos());
				pstmt.setFloat(3, c.getCostoKm());
				pstmt.setFloat(4, c.getCostoHora());
				pstmt.setDate(5, Date.valueOf(c.getFechaCompra()));
				pstmt.setString(6, c.getModelo().getModelo());
				pstmt.setString(7, c.getPatente());
				
			}
			else {
				pstmt = conn.prepareStatement(INSERT_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setFloat(2, c.getCostoKm());
				pstmt.setFloat(3, c.getCostoHora());
				pstmt.setDate(4, Date.valueOf(c.getFechaCompra()));
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
			  	c.setFechaCompra((rs.getDate("FECHA_COMPRA")).toLocalDate());
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

	private boolean checkNull(String patente, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean ret = false;
		try {
			pstmt = conn.prepareStatement(SELECT_CAMION, ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, patente);
			rs = pstmt.executeQuery();
			ret = rs.first();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Camion buscarPorPatente(String patente, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Camion c = null;
		try {
			pstmt = conn.prepareStatement(SELECT_CAMION);
			pstmt.setString(1, patente);
			rs = pstmt.executeQuery();
			c = new Camion();
			c.setCostoHora(rs.getFloat("COSTOXHS"));
			c.setCostoKm(rs.getFloat("COSTOXKM"));
			c.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
			c.setKmRecorridos(rs.getFloat("KM_RECORRIDOS"));
			c.setModelo(new Modelo(rs.getString("MARCA"), rs.getString("MODELO")));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
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
			  	c.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
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
		System.out.println(lista);
	return lista;
	}
	
	private String prepararSentencia(Map<String, ?> atrib) {
		String p1 = "SELECT * FROM CAMION C, MODELO M "
				+ "WHERE C.ID_MODELO = M.MODELO";
		String values = "";
		for (Map.Entry<String,?> entry : atrib.entrySet()) {
			
			switch(entry.getKey()) {
				case "KMMIN":
					/*
					values = values.concat(" AND C.KM_RECORRIDOS BETWEEN "+entry.getValue()+" AND "
							+atrib.get("KMMAX"));
					*/
					values = values.concat(" AND C.KM_RECORRIDOS >= "+entry.getValue());
					break;
					
				case "KMMAX":
					/*
					values = values.concat(" AND C.KM_RECORRIDOS BETWEEN "+atrib.get("KMMIN")
					+" AND "+entry.getValue());
					*/
					values = values.concat(" AND C.KM_RECORRIDOS <= "+entry.getValue());
					break;
					
				case "COSTOHSMIN":
					/*
					values = values.concat(" AND C.COSTOXHS BETWEEN "+entry.getValue()+" AND "
							+atrib.get("COSTOHSMAX"));
							*/
					values = values.concat(" AND C.COSTOXHS >= "+entry.getValue());
					break;
					
				case "COSTOHSMAX":
					/*
					values = values.concat(" AND C.COSTOXHS BETWEEN "+atrib.get("COSTOHSMIN")
					+" AND "+entry.getValue());
					*/
					values = values.concat(" AND C.COSTOXHS <= "+entry.getValue());
					break;
				
				case "COSTOKMMIN":
					/*values = values.concat(" AND C.COSTOXKM BETWEEN "+entry.getValue()+" AND "
							+atrib.get("COSTOKMMAX"));
					*/
					values = values.concat(" AND C.COSTOXKM >= "+entry.getValue());
					break;
					
				case "COSTOKMMAX":
					/*
					values = values.concat(" AND C.COSTOXKM BETWEEN "+atrib.get("COSTOKMMIN")
					+" AND "+entry.getValue());
					*/
					values = values.concat(" AND C.COSTOXKM <= "+entry.getValue());
					break;
			
				case "MARCA":
					//values = values.concat(" AND "+"M."+entry.getKey()+"='"+entry.getValue() + "'");
					values = values.concat(" AND "+"M."+entry.getKey()+" LIKE '%"+entry.getValue() + "%'");
					break;
					
				case "MODELO":
					//values = values.concat(" AND "+"M."+entry.getKey()+"='"+entry.getValue() + "'");
					values = values.concat(" AND "+"M."+entry.getKey()+" LIKE '%"+entry.getValue() + "%'");
					break;
				case "PATENTE":
					//values = values.concat(" AND "+"M."+entry.getKey()+"='"+entry.getValue() + "'");
					values = values.concat(" AND "+"C."+entry.getKey()+" LIKE '%"+entry.getValue() + "%'");
					break;
					
				default:
					if(entry!=null)
					values = values.concat(" AND "+"C."+entry.getKey()+"='"+entry.getValue() + "'");
					break;
					
			}
		}
		System.out.println(p1 + values);
		return p1.concat(values); 
	}
	
//	private Date formatearFecha(Camion c) {
//		ZoneId defaultZoneId = ZoneId.systemDefault();
//	      LocalDate localDate = c.getFechaCompra();
//	      Date date = Date.valueOf(localDate);
//	  return date;
//	}
}
