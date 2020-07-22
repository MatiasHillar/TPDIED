package dominio.util;

import java.util.*;

import dominio.Planta;

public class MatrizFloyd {
	private Float[][] matriz;
	Map<Integer, Planta> trad;
	public MatrizFloyd(Float[][] matriz, Map<Integer, Planta> trad) {
		super();
		this.matriz = matriz;
		this.trad = trad;
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
	
}
