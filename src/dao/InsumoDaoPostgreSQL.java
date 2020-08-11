package dao;

import java.util.ArrayList;		
import java.util.List;

import dao.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dominio.Insumo;
import dominio.InsumoGral;
import dominio.InsumoLiquido;
import dominio.Unidad;
import excepciones.ExcepcionNoExisteElemento;

public class InsumoDaoPostgreSQL implements InsumoDao {

	private static final String UPDATE_INSUMO_GENERAL =
			"UPDATE INSUMO SET NOMBRE_UNIDAD_MEDIDA = ?,DESCRIPCION = ?, COSTO = ?, PESO = ?, "
			+ "NOMBRE = ? "
			+ "WHERE ID = ?";
	
	private static final String INSERT_INSUMO_GENERAL =
			"INSERT INTO INSUMO(NOMBRE_UNIDAD_MEDIDA, DESCRIPCION, COSTO, TIPO, PESO, NOMBRE)"
			+ " VALUES(?, ?, ?, 'general', ?, ?)";

	private static final String UPDATE_INSUMO_LIQUIDO =
			"UPDATE INSUMO SET NOMBRE_UNIDAD_MEDIDA = ?,DESCRIPCION = ?, COSTO = ?, PESO = ?,"
			+ " DENSIDAD = ?, NOMBRE = ?"
			+ " WHERE ID = ?";

	private static final String INSERT_INSUMO_LIQUIDO =
			"INSERT INTO INSUMO(NOMBRE_UNIDAD_MEDIDA, DESCRIPCION, COSTO, TIPO, PESO, DENSIDAD, NOMBRE)"
			+ " VALUES(?, ?, ?, 'liquido', ?, ?, ?)";	
	
	private static final String DELETE_INSUMO =
			"DELETE FROM INSUMO"
			+ "WHERE ID = ?";
	
	private static final String SELECT_ALL_INSUMO =
			"SELECT I.ID, I.DESCRIPCION, I.COSTO, I.TIPO, I.PESO, I.DENSIDAD, I.NOMBRE AS NOMBRE_I, U.NOMBRE,"
			+ " U.SIMBOLO, SUM(S.CANTIDAD) AS CANTIDAD_TOTAL"
			+ " FROM INSUMO I LEFT JOIN STOCK S ON S.ID_INSUMO = I.ID"
			+ " INNER JOIN UNIDAD U ON I.NOMBRE_UNIDAD_MEDIDA = U.NOMBRE"
			+ " GROUP BY(I.ID, U.NOMBRE)";
	
	private static final String SELECT_INSUMO = 
			"SELECT I.ID, I.DESCRIPCION, I.COSTO, I.TIPO, I.PESO, I.DENSIDAD, I.NOMBRE, U.NOMBRE, U.SIMBOLO, "
			+ "SUM(S.CANTIDAD) AS CANTIDAD_TOTAL "
			+"FROM INSUMO I, STOCK S, UNIDAD U"
			+" WHERE S.ID_INSUMO = ?"
			+ " GROUP BY (I.ID, U.NOMBRE)";
	
	@Override
	public Insumo saveOrUpdate(InsumoGral i) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(i.getId()!=null) {				
				pstmt = conn.prepareStatement(UPDATE_INSUMO_GENERAL);
				pstmt.setString(1, i.getUnidadMedida().getNombre());
				pstmt.setString(2, i.getDescripcion());
				pstmt.setFloat(3, i.getCosto());
				pstmt.setFloat(4, i.getPeso());
				pstmt.setString(5, i.getNombre());
				pstmt.setInt(6, i.getId());
			 
		}
			else {
				pstmt = conn.prepareStatement(INSERT_INSUMO_GENERAL);
				pstmt.setString(1, i.getUnidadMedida().getNombre());
				pstmt.setString(2, i.getDescripcion());
				pstmt.setFloat(3, i.getCosto());
				pstmt.setFloat(4, i.getPeso());
				pstmt.setString(5, i.getNombre());
			}
		pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn!= null) conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public Insumo saveOrUpdate(InsumoLiquido i) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(i.getId()!=null) {
				pstmt = conn.prepareStatement(UPDATE_INSUMO_LIQUIDO);
				pstmt.setString(1, i.getUnidadMedida().getNombre());
				pstmt.setString(2, i.getDescripcion());
				pstmt.setFloat(3, i.getCosto());
				pstmt.setFloat(4, i.pesoPorUnidad());
				pstmt.setFloat(5, i.getDensidad());
				pstmt.setString(6, i.getNombre());
				pstmt.setInt(7, i.getId());
			}
			else {
				pstmt = conn.prepareStatement(INSERT_INSUMO_LIQUIDO);
				pstmt.setString(1, i.getUnidadMedida().getNombre());
				pstmt.setString(2, i.getDescripcion());
				pstmt.setFloat(3, i.getCosto());
				pstmt.setFloat(4, i.pesoPorUnidad());
				pstmt.setFloat(5, i.getDensidad());
				pstmt.setString(6, i.getNombre());
			}
		pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn!= null) conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return i;
	}

	
	@Override
	public void borrar(Insumo i) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_INSUMO);
			pstmt.setInt(1, i.getId());
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
	public List<Insumo> buscarTodos() {
		List<Insumo> lista = new ArrayList<Insumo>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL_INSUMO);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("TIPO").equals("general")) {
					InsumoGral i = new InsumoGral();
					i.setId(rs.getInt("ID"));
					i.setDescripcion(rs.getString("DESCRIPCION"));
					i.setUnidadMedida(new Unidad(rs.getString("NOMBRE"), rs.getString("SIMBOLO")));
					i.setCosto(rs.getFloat("COSTO"));
					i.setPeso(rs.getFloat("PESO"));
					i.setCantidadTotal(rs.getFloat("CANTIDAD_TOTAL"));
					i.setNombre(rs.getString("NOMBRE_I"));
					lista.add(i);
				}
				else {
					InsumoLiquido i = new InsumoLiquido();
					i.setId(rs.getInt("ID"));
					i.setDescripcion(rs.getString("DESCRIPCION"));
					i.setUnidadMedida(new Unidad(rs.getString("NOMBRE"), rs.getString("SIMBOLO")));
					i.setCosto(rs.getFloat("COSTO"));
					i.setPeso(rs.getFloat("PESO"));
					i.setDensidad(rs.getFloat("DENSIDAD"));
					i.setCantidadTotal(rs.getFloat("CANTIDAD_TOTAL"));
					i.setNombre(rs.getString("NOMBRE_I"));
					lista.add(i);
				}
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
	public Unidad buscarUnidad(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Insumo buscar(Integer id_insumo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Insumo ret = null;
		try {
			pstmt = conn.prepareStatement(SELECT_INSUMO, ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id_insumo);
			rs = pstmt.executeQuery();
			if(!rs.first()) throw new ExcepcionNoExisteElemento();
			if(rs.getString("TIPO").equals("general")) {
				InsumoGral i = new InsumoGral();
				i.setId(rs.getInt("ID"));
				i.setCosto(rs.getFloat("COSTO"));
				i.setDescripcion(rs.getString("DESCRIPCION"));
				i.setUnidadMedida(new Unidad(rs.getString("NOMBRE"), rs.getString("SIMBOLO")));
				i.setNombre(rs.getString("NOMBRE"));
				i.setPeso(rs.getFloat("PESO"));
				i.setCantidadTotal(rs.getFloat("CANTIDAD_TOTAL"));
				ret = i;
			}
			else {
				InsumoLiquido i = new InsumoLiquido();
				i.setId(rs.getInt("ID"));
				i.setCosto(rs.getFloat("COSTO"));
				i.setDescripcion(rs.getString("DESCRIPCION"));
				i.setUnidadMedida(new Unidad(rs.getString("NOMBRE"), rs.getString("SIMBOLO")));
				i.setNombre(rs.getString("NOMBRE"));
				i.setDensidad(rs.getFloat("DENSIDAD"));
				i.setPeso(rs.getFloat("PESO"));
				i.setCantidadTotal(rs.getFloat("CANTIDAD_TOTAL"));
				ret = i;
			}
		}
		catch(SQLException | ExcepcionNoExisteElemento e) {
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
		return ret;
	}

}
