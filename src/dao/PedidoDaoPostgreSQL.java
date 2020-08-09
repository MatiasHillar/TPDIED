package dao;
	
import java.sql.Connection;	
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.utils.DB;
import dominio.Estado;
import dominio.Pedido;
import dominio.Planta;
import dominio.Ruta;
import excepciones.ExcepcionNoExisteElemento;

public class PedidoDaoPostgreSQL implements PedidoDao{
	
	private static final String UPDATE_PEDIDO = 
			"UPDATE PEDIDO SET PLANTA_DESTINO = ?, FECHA_SOLICITUD = ?,"
			+ "FECHA_ENTREGA = ?, ESTADO = ?, COSTO_ENVIO = ?, PATENTE_CAMION = ?"
			+ "WHERE NRO_PEDIDO = ?";
	
	private static final String INSERT_PEDIDO = 
			"INSERT INTO PEDIDO (PLANTA_DESTINO, FECHA_SOLICITUD, FECHA_ENTREGA, ESTADO) "
			+ "VALUES(?, ?, ?, ?)";

	private static final String INSERT_RUTAS = 
			"INSERT INTO RUTA_PEDIDO VALUES(?, ?, ?)";
	
	private static final String UPDATE_RUTAS = 
			"UPDATE RUTA_PEDIO SET NRO_INDICE = ?"
			+ "WHERE NRO_PEDIDO = ?"
			+ "AND ID_RUTA = ?";
	
	private static final String UPDATE_ESTADO = 
			"UPDATE PEDIDO SET ESTADO = 'CANCELADO'"
			+ " WHERE NRO_PEDIDO = ?";
	
	private static final String SELECT_PROCESADAS = 
			"SELECT * FROM PEDIDO"
			+ "WHERE ESTADO LIKE 'PROCESADO'";
	
	private static final String SELECT_PEDIDO = 
			"SELECT * FROM PEDIDO"
			+ "WHERE NRO_PEDIDO = ?";
	
	private static final String SELECT_PEDIDO_ITEMS =
			"SELECT NRO_PEDIDO FROM PEDIDO "
			+ "WHERE NOT EXIST (SELECT * FROM ITEM_PEDIDO"
			+ " WHERE ITEM_PEDIDO.NRO_PEDIDO = PEDIDO.NRO_PEDIDO)";
	
	private static final String SELECT_CREADAS = 
			"SELECT * FROM PEDIDO "
			+ "WHERE ESTADO LIKE 'CREADO'";
	
	private static final String SELECT_RUTAS = 
			"SELECT * FROM RUTA_PEDIDO, RUTA "
			+ "WHERE NRO_PEDIDO = ? "
			+ "ORDER BY (NRO_INDICE) ASC";
		
	private CamionDao camiondao = new CamionDaoPostgreSQL();
	private PlantaDao plantadao = new PlantaDaoPostgreSQL();
	private ItemPedidoDao itemdao = new ItemPedidoDaoPostgreSQL();
	
	@Override
	public Pedido saveOrUpdate(Pedido p) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(p.getNroPedido() != null) {
				if(p.getEstado().equals(Estado.CANCELADO)) {
					pstmt = conn.prepareStatement(UPDATE_ESTADO);
				}
				else {
				pstmt = conn.prepareStatement(UPDATE_PEDIDO);
				pstmt.setInt(1, p.getPlantaDestino().getId());
				pstmt.setDate(2, Date.valueOf(p.getFechaSolicitud()));
				pstmt.setDate(3, Date.valueOf(p.getFechaEntrega()));
				pstmt.setString(4, p.getEstado().name());
				pstmt.setFloat(5, p.getCostoEnvio());
				pstmt.setString(6, p.getCamion().getPatente());
				pstmt.setInt(7, p.getNroPedido());
				saveOrUpdateRutas(p, p.getRuta(), conn);
				}
			}
			else {
				pstmt = conn.prepareStatement(INSERT_PEDIDO);
				pstmt.setInt(1, p.getPlantaDestino().getId());
				pstmt.setDate(2, Date.valueOf(p.getFechaSolicitud()));
				pstmt.setDate(3, Date.valueOf(p.getFechaEntrega()));
				pstmt.setString(4, p.getEstado().name());
//				pstmt.setFloat(5, p.getCostoEnvio());
//				pstmt.setString(6, p.getCamion().getPatente());
			}
			pstmt.executeUpdate();
		
			itemdao.saveOrUpdate(buscarNroPedido(conn, pstmt), p.getListaItems(), conn);
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

	private void saveOrUpdateRutas(Pedido p, List<Ruta> lista, Connection conn) {
		PreparedStatement pstmt = null;
		try {
			if(p.getNroPedido() != null) {
				pstmt = conn.prepareStatement(UPDATE_RUTAS);
				pstmt.setInt(2, p.getNroPedido());
				for(int i = 1; i<=lista.size(); i++) {
					pstmt.setInt(1, i);
					pstmt.setInt(3, lista.get(i).getId());
					pstmt.executeUpdate();
				}
			}
			else {
				pstmt = conn.prepareStatement(INSERT_RUTAS);
				pstmt.setInt(1, p.getNroPedido());
				for(int i = 1; i<=lista.size(); i++) {
					pstmt.setInt(2, lista.get(i).getId());
					pstmt.setInt(3, i);
					pstmt.executeUpdate();
				}
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
	}
	
	private List<Ruta> selectRutas(Integer nro_pedido, Connection conn){
		List<Ruta> lista = new ArrayList<Ruta>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Ruta r = null;
		try {
			pstmt = conn.prepareStatement(SELECT_RUTAS);
			pstmt.setInt(1, nro_pedido);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				r = new Ruta();
				r.setDistanciaKm(rs.getFloat("DISTANCIAKM"));
				r.setDuracionMin(rs.getFloat("DISTANCIAMIN"));
				r.setId(rs.getInt("ID"));
				r.setPesoMaximoKg(rs.getFloat("PESMAXIMOKG"));
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
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	@Override
	public Pedido buscar(Integer nroPedido, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pedido p = null;
		try {
			pstmt = conn.prepareStatement(SELECT_PEDIDO,ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, nroPedido);
			rs = pstmt.executeQuery();
			if(!rs.next()) throw new ExcepcionNoExisteElemento();
			p = new Pedido();
			p.setCamion(camiondao.buscarPorPatente(rs.getString("PATENTE_CAMION"), conn));
			p.setCostoEnvio(rs.getFloat("COSTO_ENVIO"));
			p.setEstado(Estado.valueOf(rs.getString("ESTADO")));
			p.setFechaEntrega(rs.getDate("FECHA_ENTREGA").toLocalDate());
			p.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD").toLocalDate());
			p.setNroPedido(rs.getInt("NRO_PEDIDO"));
			p.setPlantaDestino(plantadao.buscar(rs.getInt("PLANTA_DESTINO"), conn));
			p.setRuta(selectRutas(rs.getInt("NRO_PEDIDO"), conn));
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
		return p;
	}

	@Override
	public List<Pedido> filtrarProcesadas() {
		List<Pedido> lista = new ArrayList<Pedido>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pedido p = null;
		try {
			pstmt = conn.prepareStatement(SELECT_PROCESADAS);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				p = new Pedido();
				p.setCamion(camiondao.buscarPorPatente(rs.getString("PATENTE_CAMION"), conn));
				p.setCostoEnvio(rs.getFloat("COSTO_ENVIO"));
				p.setEstado(Estado.valueOf(rs.getString("ESTADO")));
				p.setFechaEntrega(rs.getDate("FECHA_ENTREGA").toLocalDate());
				p.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD").toLocalDate());
				p.setNroPedido(rs.getInt("NRO_PEDIDO"));
				p.setPlantaDestino(plantadao.buscar(rs.getInt("PLANTA_DESTINO"), conn));
				p.setRuta(selectRutas(rs.getInt("NRO_PEDIDO"), conn));
				p.setListaItems(itemdao.selectItems(rs.getInt("NRO_PEDIDO"), conn));
				lista.add(p);
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
	public List<Pedido> filtrarCreadas() {
		List<Pedido> lista = new ArrayList<Pedido>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pedido p = null;
		try {
			pstmt = conn.prepareStatement(SELECT_CREADAS);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				p = new Pedido();
//				p.setCamion(camiondao.buscarPorPatente(rs.getString("PATENTE_CAMION"), conn));
//				p.setCostoEnvio(rs.getFloat("COSTO_ENVIO"));
				p.setEstado(Estado.valueOf(rs.getString("ESTADO")));
				p.setFechaEntrega(rs.getDate("FECHA_ENTREGA").toLocalDate());
				p.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD").toLocalDate());
				p.setNroPedido(rs.getInt("NRO_PEDIDO"));
				p.setPlantaDestino(plantadao.buscar(rs.getInt("PLANTA_DESTINO"), conn));
//				p.setRuta(selectRutas(rs.getInt("NRO_PEDIDO"), conn));
				p.setListaItems(itemdao.selectItems(rs.getInt("NRO_PEDIDO"), conn));
				lista.add(p);
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
	public List<Planta> checkPlantas(Integer nro_pedido){
		List<Planta> lista;
		Connection conn = DB.getConexion();
			lista = plantadao.checkInsumos(nro_pedido, conn);
			try {
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		return lista;
	}
	
	private Integer buscarNroPedido(Connection conn, PreparedStatement pstmt) {
		ResultSet rs = null;
		Integer nroPedido = null;
		try {
			pstmt = conn.prepareStatement(SELECT_PEDIDO_ITEMS);
			rs = pstmt.executeQuery();
			if(!rs.first()) throw new ExcepcionNoExisteElemento();
			nroPedido = rs.getInt("NRO_PEDIDO");
		}
		catch(SQLException | ExcepcionNoExisteElemento e) {
			e.printStackTrace();
		}
		return nroPedido;
		}
}
