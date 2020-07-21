package dominio;

import java.util.*;

public class Mapa {
	private List<Planta> listaPlantas;
	private List<Ruta> listaRutas;
	public List<Planta> getListaPlantas() {
		return listaPlantas;
	}
	public void setListaPlantas(List<Planta> listaPlantas) {
		this.listaPlantas = listaPlantas;
	}
	public List<Ruta> getListaRutas() {
		return listaRutas;
	}
	public void setListaRutas(List<Ruta> listaRutas) {
		this.listaRutas = listaRutas;
	}

}
