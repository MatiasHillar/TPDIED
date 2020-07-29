package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import dao.PedidoDao;
import dao.utils.DB;

public class PedidoDaoPostgreSQL implements PedidoDao{
	
	private static final String UPDATE_PEDIDO = 
			"UPDATE PEDIDO SET PLANTA_DESTINO = ?, ID_RUTA = ?, FECHA_SOLICITUD = ?,"
			+ "FECHA_ENTREGA = ?, ESTADO = ?, COSTO_ENVIO = ?, PATENTE_CAMION = ?"
			+ "WHERE NRO_PEDIDO = ?";
	
	private static final String INSERT_CAMION = 
			"INSERT INTO PEDIDO (PLANTA_DESTINO, ID_RUTA, FECHA_SOLICITUD, FECHA_ENTREGA, ESTADO,"
			+ "ENVIO, COSTO_ENVIO, PATENTE_CAMION)"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

//	@Override
//	public Pedido saveOrUpdate(Pedido p) {
//		Connection conn = DB.getConexion();
//		PreparedStatement pstmt = null;
//		try {
//			if(p.getNroPedido() != null) {
//				pstmt = conn.prepareStatement(UPDATE_PEDIDO);
//				pstmt.setInt(1, p.getPlantaDestino().getId());
//				pstmt.setInt(2, p.getr);
//			}
//		}
//	}

	@Override
	public Pedido buscar(Integer nroPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> filtrarProcesadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> filtrarCreadas() {
		// TODO Auto-generated method stub
		return null;
	}

}
