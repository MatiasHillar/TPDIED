package servicios;

import java.util.List;

import dao.InsumoDao;
import dao.InsumoDaoPostgreSQL;
import dao.UnidadDao;
import dao.UnidadDaoPostgreSQL;
import dominio.*;
import servicios.*;


public class InsumoService {
	private InsumoDao insumoDao;
	private UnidadDao unidadDao;
	public InsumoService() {
		insumoDao= new InsumoDaoPostgreSQL();
		unidadDao = new UnidadDaoPostgreSQL();
	}
	
	public InsumoGral crearInsumoGral(InsumoGral i) {
		return (InsumoGral) this.insumoDao.saveOrUpdate(i);
	}
	
	public InsumoLiquido crearInsumoLiquido(InsumoLiquido i) {
		return (InsumoLiquido) this.insumoDao.saveOrUpdate(i);
	}
	
	public List<Insumo> buscarTodos(){
		return this.insumoDao.buscarTodos();
	}
	
	public void borrarInsumo(Insumo i) {
		insumoDao.borrar(i);
	}
	
	public List<Unidad> buscarTodasUnidades(){
		return unidadDao.buscarTodas();
	}
	

}
