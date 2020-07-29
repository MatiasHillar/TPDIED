package dominio;

import java.time.LocalDate;

public class Camion {
private String patente;
private Modelo modelo;
private Float costoKm;
private Float costoHora;
private Float kmRecorridos;
private LocalDate fechaCompra;
public String getPatente() {
	return patente;
}
public void setPatente(String patente) {
	this.patente = patente;
}
public Modelo getModelo() {
	return modelo;
}
public void setModelo(Modelo modelo) {
	this.modelo = modelo;
}
public Float getCostoKm() {
	return costoKm;
}
public void setCostoKm(Float costoKm) {
	this.costoKm = costoKm;
}
public Float getCostoHora() {
	return costoHora;
}
public void setCostoHora(Float costoHora) {
	this.costoHora = costoHora;
}
public Float getKmRecorridos() {
	return kmRecorridos;
}
public void setKmRecorridos(Float kmRecorridos) {
	this.kmRecorridos = kmRecorridos;
}
public LocalDate getFechaCompra() {
	return fechaCompra;
}
public void setFechaCompra(LocalDate fechaCompra) {
	this.fechaCompra = fechaCompra;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((patente == null) ? 0 : patente.hashCode());
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
	Camion other = (Camion) obj;
	if (patente == null) {
		if (other.patente != null)
			return false;
	} else if (!patente.equals(other.patente))
		return false;
	return true;
}



/*
 * 
 * PARECE QUE EL PROFE INICIALIZA CON OTRA CLASE PERO NO SE SI VAMOS A USAR ESE SISTEMA
public Camion(String patente, Integer id, Modelo modelo, Float costoKm, Float costoHora, Float kmRecorridos,
		LocalDate fechaCompra) {
	super();
	this.patente = patente;
	this.id = id;
	this.modelo = modelo;
	this.costoKm = costoKm;
	this.costoHora = costoHora;
	this.kmRecorridos = kmRecorridos;
	this.fechaCompra = fechaCompra;
}

public Camion(String patente, Integer id, Modelo modelo, Float costoKm, Float costoHora, LocalDate fechaCompra) {
	this(patente, id, modelo, costoKm, costoHora,0f, fechaCompra);
}
public Camion(String patente, Integer id, Modelo modelo, Float costoKm, Float costoHora, Float kmRecorridos) {
	this(patente, id, modelo, costoKm, costoHora,kmRecorridos, LocalDate.now());
}

*/


}
