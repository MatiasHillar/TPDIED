package dominio;

public class Ruta {
private Integer id;
private Planta plantaOrigen;
private Planta plantaDestino;
private Float distanciaKm;
private Float duracionMin;
private Float pesoMaximoKg;

@Override
public String toString() {
	return plantaOrigen + " -> " + plantaDestino + " (" + distanciaKm + " Km; "
			+ duracionMin + " min; Maximo= " + pesoMaximoKg + " Kg)";
}
public Ruta(Planta plantaOrigen, Planta plantaDestino, Float distanciaKm, Float duracionMin, Float pesoMaximoKg) {
	super();
	this.plantaOrigen = plantaOrigen;
	this.plantaDestino = plantaDestino;
	this.distanciaKm = distanciaKm;
	this.duracionMin = duracionMin;
	this.pesoMaximoKg = pesoMaximoKg;
}
public Ruta() {
	super();
}
public Planta getPlantaOrigen() {
	return plantaOrigen;
}
public void setPlantaOrigen(Planta plantaOrigen) {
	this.plantaOrigen = plantaOrigen;
}
public Planta getPlantaDestino() {
	return plantaDestino;
}
public void setPlantaDestino(Planta plantaDestino) {
	this.plantaDestino = plantaDestino;
}
public Float getDistanciaKm() {
	return distanciaKm;
}
public void setDistanciaKm(Float distanciaKm) {
	this.distanciaKm = distanciaKm;
}
public Float getDuracionMin() {
	return duracionMin;
}
public void setDuracionMin(Float duracionMin) {
	this.duracionMin = duracionMin;
}
public Float getPesoMaximoKg() {
	return pesoMaximoKg;
}
public void setPesoMaximoKg(Float pesoMaximoKg) {
	this.pesoMaximoKg = pesoMaximoKg;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}



}
