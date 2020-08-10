package dao;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.utils.DB;
import dominio.Insumo;
import dominio.Planta;
import dominio.Stock;

public class StockDaoPostgreSQL implements StockDao {

	private static final String SELECT_STOCK_PLANTA = 
			"SELECT *"
			+ " FROM STOCK "
			+ " WHERE ID_PLANTA = ?";
	
	private static final String UPDATE_STOCK = 
			"UPDATE STOCK SET PUNTO_REPOSICION = ?, CANTIDAD = ?"
			+ " WHERE ID_INSUMO = ? "
			+ "AND ID_PLANTA = ?";
	
	private static final String INSERT_STOCK = 
			"INSERT INTO STOCK(ID_INSUMO, ID_PLANTA, PUNTO_REPOSICION, CANTIDAD) VALUES(?,?,?,?)";
	
	private static final String SELECT_STOCK_TOTAL =
			"SELECT SUM(S.CANTIDAD) AS STOCK_TOTAL"
			+ " FROM STOCK S, INSUMO I"
			+ " WHERE I.ID = ?"
			+ " AND S.ID_INSUMO = I.ID"
			+ " GROUP BY(I.ID)";
	
	private static final String DELETE_STOCK =
			"DELETE FROM STOCK WHERE ID = ?";
	
	private static final String SELECT_ALL_STOCK =
			"SELECT * FROM STOCK";
	
	private static final String SELECT_STOCK = 
			"SELECT * FROM STOCK WHERE ID_INSUMO = ? "
			+ "AND ID_PLANTA = ?";
	
	private InsumoDao insumoDao;
	private PlantaDao plantadao;
	
	public StockDaoPostgreSQL() {
		super();
		insumoDao = new InsumoDaoPostgreSQL();
		plantadao = new PlantaDaoPostgreSQL(this);
	}
	
	public StockDaoPostgreSQL(PlantaDao plantadao) {
		super();
		insumoDao = new InsumoDaoPostgreSQL();
		this.plantadao = plantadao;
	}
	
	@Override
	public Stock saveOrUpdate(Stock s) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(checkNull(s.getInsumo().getId(), s.getP().getId(), conn)) {
				pstmt = conn.prepareStatement(UPDATE_STOCK);
				pstmt.setFloat(1, s.getPuntoRepo());
				pstmt.setFloat(2, s.getCtidad());
				pstmt.setInt(3, s.getInsumo().getId());
				pstmt.setInt(4, s.getP().getId());
			}
			else {
				pstmt = conn.prepareStatement(INSERT_STOCK);
				pstmt.setInt(1, s.getInsumo().getId());
				pstmt.setInt(2, s.getP().getId());
				pstmt.setFloat(3, s.getPuntoRepo());
				pstmt.setFloat(4, s.getCtidad());
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
		return s;
	}
	
	@Override
	public void saveOrUpdate(Integer id_planta, List<Stock> lista) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			for(int i=0; i<lista.size(); i++) {
				Integer id_insumo = lista.get(i).getInsumo().getId();
				if(checkNull(id_insumo, id_planta, conn)) {
					pstmt = conn.prepareStatement(UPDATE_STOCK);
					pstmt.setFloat(1, lista.get(i).getPuntoRepo());
					pstmt.setFloat(2, lista.get(i).getCtidad());
					pstmt.setInt(3, lista.get(i).getInsumo().getId());
					pstmt.setInt(4, id_planta);
				}
				else {
					pstmt = conn.prepareStatement(INSERT_STOCK);
					pstmt.setInt(1, id_insumo);
					pstmt.setInt(2, id_planta);
					pstmt.setFloat(3, lista.get(i).getPuntoRepo());
					pstmt.setFloat(4, lista.get(i).getCtidad());
				}
				pstmt.executeUpdate();
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
	}

	@Override
	public Integer stockTotal(Integer idprod) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int stock_total = 0;
		try {
			pstmt = conn.prepareStatement(SELECT_STOCK_TOTAL);
			pstmt.setInt(1, idprod);
			rs = pstmt.executeQuery();
			stock_total = rs.getInt("STOCK_TOTAL");
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
		return stock_total;
	}

	@Override
	public List<Stock> buscarPorPlanta(Planta p, Connection conn) {
		List<Stock> lista = new ArrayList<Stock>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_STOCK_PLANTA);
			pstmt.setInt(1, p.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Stock s = new Stock();
				s.setCtidad(rs.getFloat("CANTIDAD"));
				s.setPuntoRepo(rs.getFloat("PUNTO_REPOSICION"));
				s.setInsumo(insumoDao.buscar(rs.getInt("ID_INSUMO"), conn));
				s.setP(p);
				lista.add(s);
			}
		}
		catch(SQLException e) {
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
		return lista;
	}

	public List<Stock> buscarTodos(){
		List<Stock> lista = new ArrayList<Stock>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Stock s = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ALL_STOCK);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				s = new Stock();
				s.setCtidad(rs.getFloat("CANTIDAD"));
				s.setInsumo(insumoDao.buscar(rs.getInt("ID_INSUMO"), conn));
				s.setP(plantadao.buscar(rs.getInt("ID_PLANTA"), conn));
				s.setPuntoRepo(rs.getFloat("PUNTO_REPOSICION"));
				lista.add(s);
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
	public void borrar(Integer idprod) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_STOCK);
			pstmt.setInt(1, idprod);
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
	
	private boolean checkNull(Integer id_insumo, Integer id_planta, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean ret = false;
		try {
			pstmt = conn.prepareStatement(SELECT_STOCK, ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id_insumo);
			pstmt.setInt(2, id_planta);
			rs = pstmt.executeQuery();
			ret = rs.first();
		}
		catch(SQLException e) {
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
