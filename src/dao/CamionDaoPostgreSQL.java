package dao;


import java.util.List;	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		return null;
	}

	@Override
	public Void borrar(Camion c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Camion> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Camion buscarporPatente(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
