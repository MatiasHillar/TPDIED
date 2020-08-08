package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dao.utils.DB;
import dominio.Ruta;

public class RutaDaoPostgreSQL implements RutaDao {

	private static final String UPDATE_RUTA = 
			"UPDATE RUTA SET ID_PLANTA_ORIGEN = ?, ID_PLANTA_DESTINO = ?, DISTANCIAKM = ?, "
			+ "DISTANCIAMIN = ?,  PESOMAXIMOKG = ?"
			+ "WHERE ID = ?";
	
	private static final String INSERT_RUTA = 
			"INSERT INTO RUTA(ID_PLANTA_ORIGEN, ID_PLANTA_DESTINO, DISTANCIAKM, DISTANCIAMIN,"
			+ " PESOMAXIMOKG)"
			+ "VALUES(?,?,?,?,?)";
	
	private static final String SELECT_ALL_RUTA =
			"SELECT * FROM RUTA";
	
	private static final String DELETE_RUTA = 
			"DELETE FROM RUTA WHERE ID = ?";
	
	private PlantaDao plantadao = new PlantaDaoPostgreSQL();
	
	@Override
	public Ruta saveOrUpdate(Ruta r) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(r.getId()!=null) {
				pstmt = conn.prepareStatement(UPDATE_RUTA);
				pstmt.setInt(1, r.getPlantaOrigen().getId());
				pstmt.setInt(2, r.getPlantaDestino().getId());
				pstmt.setFloat(3, r.getDistanciaKm());
				pstmt.setFloat(4, r.getDuracionMin());
				pstmt.setFloat(5, r.getPesoMaximoKg());
				pstmt.setInt(6, r.getId());
			}
			else {
				pstmt = conn.prepareStatement(INSERT_RUTA);
				pstmt.setInt(1, r.getPlantaOrigen().getId());
				pstmt.setInt(2, r.getPlantaDestino().getId());
				pstmt.setFloat(3, r.getDistanciaKm());
				pstmt.setFloat(4, r.getDuracionMin());
				pstmt.setFloat(5, r.getPesoMaximoKg());
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
	return r;
	}

	@Override
	public Ruta buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrar(Integer id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_RUTA);
			pstmt.setInt(1, id);
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
	public List<Ruta> buscarTodas() {
		List<Ruta> lista = new ArrayList<Ruta>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Ruta r = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL_RUTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				r = new Ruta();
				r.setDistanciaKm(rs.getFloat("DISTANCIAKM"));
				r.setDuracionMin(rs.getFloat("DISTANCIAMIN"));
				r.setId(rs.getInt("ID"));
				r.setPesoMaximoKg(rs.getFloat("PESOMAXIMOKG"));
				r.setPlantaDestino(plantadao.buscar(rs.getInt("ID_PLANTA_DESTINO"), conn));
				r.setPlantaOrigen(plantadao.buscar(rs.getInt("ID_PLANTA_ORIGEN"), conn));
				lista.add(r);
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
