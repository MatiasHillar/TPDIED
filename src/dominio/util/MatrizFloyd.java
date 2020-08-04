package dominio.util;

import java.util.*;

import dominio.Planta;

public class MatrizFloyd {
	private Float[][] matriz;
	Map<Integer, Planta> trad;
	Map<Integer, Integer> tradR;
	public MatrizFloyd(Float[][] matriz, Map<Integer, Planta> trad, Map<Integer, Integer> tradR) {
		super();
		this.matriz = matriz;
		this.trad = trad;
		this.tradR = tradR;
	}
	public Float[][] getMatriz() {
		return matriz;
	}
	public void setMatriz(Float[][] matriz) {
		this.matriz = matriz;
	}
	public Map<Integer, Planta> getTrad() {
		return trad;
	}
	public void setTrad(Map<Integer, Planta> trad) {
		this.trad = trad;
	}
	public Map<Integer, Integer> getTradR() {
		return tradR;
	}
	public void setTradR(Map<Integer, Integer> tradR) {
		this.tradR = tradR;
	}
	
}
