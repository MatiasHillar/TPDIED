package dominio;

import java.util.*;

public class Planta {
	private String nombre;
	private Integer id;
	private List<Stock> listaStock;
	private PriorityQueue<Camion> listaCamiones;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Stock> getListaStock() {
		return listaStock;
	}
	public void setListaStock(List<Stock> listaStock) {
		this.listaStock = listaStock;
	}
	public PriorityQueue<Camion> getListaCamiones() {
		return listaCamiones;
	}
	public void setListaCamiones(PriorityQueue<Camion> listaCamiones) {
		this.listaCamiones = listaCamiones;
	}

}
