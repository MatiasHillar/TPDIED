package dominio;

import java.time.LocalDate;

public class Camion {
private String patente;
private Integer id;
private Modelo modelo;
private Float costoKm;
private Float costoHora;
private Float kmRecorridos;
private LocalDate fechaCompra;



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
