package Dominio;

public class Ruta {
private Planta plantaOrigen;
private Planta plantaDestino;
private Float distanciaKm;
private Float duracionMin;
private Float pesoMaximoKg;

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



}
