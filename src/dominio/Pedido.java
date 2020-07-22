package dominio;

import java.time.*;
import java.util.*;

public class Pedido {
	private Integer nroPedido;
	private Planta plantaDestino;
	private LocalDate fechaSolicitud;
	private LocalDate fechaEntrega;
	private Estado estado;
	private List<ItemPedido> listaItems;
	private Camion camion;
	private Ruta ruta;
	private Float costoEnvio;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nroPedido == null) ? 0 : nroPedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (nroPedido == null) {
			if (other.nroPedido != null)
				return false;
		} else if (!nroPedido.equals(other.nroPedido))
			return false;
		return true;
	}
	public Integer getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(Integer nroPedido) {
		this.nroPedido = nroPedido;
	}
	public Planta getPlantaDestino() {
		return plantaDestino;
	}
	public void setPlantaDestino(Planta plantaDestino) {
		this.plantaDestino = plantaDestino;
	}
	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<ItemPedido> getListaItems() {
		return listaItems;
	}
	public void setListaItems(List<ItemPedido> listaItems) {
		this.listaItems = listaItems;
	}
	public Camion getCamion() {
		return camion;
	}
	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public Float getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(Float costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	
}
