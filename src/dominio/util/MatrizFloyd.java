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
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		String endl = System.getProperty("line.separator"); 
		for(int i=0; i<trad.keySet().size(); i++) {
			sb.append(trad.get(i).getNombre());
		}
		sb.append(endl);
		for(int i=0; i<trad.keySet().size(); i++) {
			sb.append(trad.get(i).getNombre());
			for(int j=0; j<trad.keySet().size(); j++) {
				sb.append(matriz[i][j]);
			}
			sb.append(endl);
		}
		return sb.toString();
//		return "MatrizFloyd [matriz=" + Arrays.toString(matriz) + ", trad=" + trad + ", tradR=" + tradR + "]";
	}
	
	
	
}
