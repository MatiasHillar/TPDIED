package dao;

import dao.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dominio.Modelo;

public class ModeloDaoPostgreSQL implements ModeloDao{
	private static final String UPDATE_MODELO = 
			"UPDATE MODELO SET MODELO = ?, MARCA = ?"
			+ "WHERE ID = ?";
	
	private static final String INSERT_MODELO =
			"INSERT INTO MODELO VALUES (?, ?, ?)";

//	@Override
//	public Modelo saveOrUpdate(Modelo m) {
//		Connection conn = DB.getConexion();
//		PreparedStatement pstmt = null;
//		try {
//			if(m.getModelo())
//		}
//		
//	}

	@Override
	public Modelo buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void borrar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}

	
