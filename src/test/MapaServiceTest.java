package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

import dominio.*;
import dominio.util.MatrizFloyd;
import servicios.MapaService;

public class MapaServiceTest {
	

	@Test
	public void maxFlowTest() {
		
		//Ejemplo de clase de grafos de flujo maximo
		
		Mapa m = new Mapa();
		List<Planta> lp= new ArrayList<Planta>();
		Planta p1 = new Planta("p1", 1, new ArrayList<Stock>());
		Planta p2 = new Planta("p2", 2, new ArrayList<Stock>());
		Planta p3 = new Planta("p3", 3, new ArrayList<Stock>());
		Planta p4 = new Planta("p4", 4, new ArrayList<Stock>());
		Planta p5 = new Planta("p5", 5, new ArrayList<Stock>());
		Planta p6 = new Planta("p6", 6, new ArrayList<Stock>());
		Planta p7 = new Planta("p7", 7, new ArrayList<Stock>());
		lp.add(p1);
		lp.add(p2);
		lp.add(p3);
		lp.add(p4);
		lp.add(p5);
		lp.add(p6);
		lp.add(p7);
		
		List<Ruta> lr= new ArrayList<Ruta>();
		Ruta r1 = new Ruta(p1,p2, 10f, 10f, 5f);
		Ruta r2 = new Ruta(p1,p3, 10f, 60f, 6f);
		Ruta r3 = new Ruta(p1,p4, 30f, 50f, 5f);
		Ruta r4 = new Ruta(p2,p5, 50f, 20f, 3f);
		Ruta r5 = new Ruta(p2,p3, 60f, 10f, 2f);
		Ruta r6 = new Ruta(p3,p2, 80f, 90f, 2f);
		Ruta r7 = new Ruta(p3,p5, 20f, 80f, 3f);
		Ruta r8 = new Ruta(p3,p6, 30f, 20f, 7f);
		Ruta r9 = new Ruta(p3,p4, 10f, 50f, 3f);
		Ruta r10 = new Ruta(p4,p6, 50f, 20f, 5f);
		Ruta r11 = new Ruta(p5,p6, 60f, 10f, 1f);
		Ruta r12 = new Ruta(p5,p7, 80f, 90f, 8f);
		Ruta r13 = new Ruta(p6,p5, 20f, 80f, 1f);
		Ruta r14 = new Ruta(p6,p7, 30f, 20f, 7f);
		lr.add(r1);
		lr.add(r2);
		lr.add(r3);
		lr.add(r4);
		lr.add(r5);
		lr.add(r6);
		lr.add(r7);
		lr.add(r8);
		lr.add(r9);
		lr.add(r10);
		lr.add(r11);
		lr.add(r12);
		lr.add(r13);
		lr.add(r14);
		
		m.setListaPlantas(lp);
		m.setListaRutas(lr);
		
		MapaService ms = new MapaService();
		
		assertTrue(ms.maxFlow(p1, p7, m).equals(13f));
	}
	
	@Test 
	public void pageRank() {
		
		//Ejemplo de clase de grafos de page rank
		
		Mapa m = new Mapa();
		List<Planta> lp= new ArrayList<Planta>();
		Planta a = new Planta("p1", 1, new ArrayList<Stock>());
		Planta b = new Planta("p2", 2, new ArrayList<Stock>());
		Planta c = new Planta("p3", 3, new ArrayList<Stock>());
		lp.add(a);
		lp.add(b);
		lp.add(c);
		List<Ruta> lr= new ArrayList<Ruta>();
		Ruta r1 = new Ruta(a,b, 10f, 10f, 5f);
		Ruta r2 = new Ruta(a,c, 10f, 60f, 6f);
		Ruta r3 = new Ruta(c,a, 30f, 50f, 5f);
		Ruta r4 = new Ruta(b,c, 50f, 20f, 3f);
		lr.add(r1);
		lr.add(r2);
		lr.add(r3);
		lr.add(r4);
		m.setListaPlantas(lp);
		m.setListaRutas(lr);
		
		MapaService ms = new MapaService();
		
		HashMap<Planta,Double> res = ms.pageRank(m);
		Double aux = res.get(a);
		assertTrue(aux<1.08d && aux>1.07d);
		aux = res.get(b);
		assertTrue(aux<0.8d && aux>0.7d);
		aux = res.get(c);
		assertTrue(aux<1.16d && aux>1.15d);
		
	}
	
	@Test
	public void Floyd() {
		//Ejemplo de clase de grafos de Floyd
		Mapa m = new Mapa();
		List<Planta> lp= new ArrayList<Planta>();
		Planta p1 = new Planta("p1", 1, new ArrayList<Stock>());
		Planta p2 = new Planta("p2", 2, new ArrayList<Stock>());
		Planta p3 = new Planta("p3", 3, new ArrayList<Stock>());
		Planta p4 = new Planta("p4", 4, new ArrayList<Stock>());
		Planta p5 = new Planta("p5", 5, new ArrayList<Stock>());
		lp.add(p1);
		lp.add(p2);
		lp.add(p3);
		lp.add(p4);
		lp.add(p5);
		
		List<Ruta> lr= new ArrayList<Ruta>();
		Ruta r1 = new Ruta(p1,p2, 1f, 1f, 1f);
		Ruta r2 = new Ruta(p2,p4, 4f, 4f, 4f);
		Ruta r3 = new Ruta(p2,p5, 7f, 7f, 7f);
		Ruta r4 = new Ruta(p3,p1, 3f, 3f, 3f);
		Ruta r5 = new Ruta(p3,p2, 2f, 2f, 2f);
		Ruta r6 = new Ruta(p4,p1, 6f, 6f, 6f);
		Ruta r7 = new Ruta(p4,p5, 2f, 2f, 2f);
		Ruta r8 = new Ruta(p5,p4, 3f, 3f, 3f);
		Ruta r9 = new Ruta(p3,p5, 4f, 4f, 4f);
		lr.add(r1);
		lr.add(r2);
		lr.add(r3);
		lr.add(r4);
		lr.add(r5);
		lr.add(r6);
		lr.add(r7);
		lr.add(r8);
		lr.add(r9);
		
		m.setListaPlantas(lp);
		m.setListaRutas(lr);
		
		MapaService ms = new MapaService();
		
		MatrizFloyd mfkm = ms.caminosMenosKm(m);
		MatrizFloyd mfT = ms.caminosMenosTiempo(m);
		
		Float[][] d = {{0f,1f,Float.MAX_VALUE,5f,7f},
						{10f,0f,Float.MAX_VALUE,4f,6f},
						{3f,2f,0f,6f,4f},
						{6f,7f,Float.MAX_VALUE,0f,2f},
						{9f,10f,Float.MAX_VALUE,3f,0f}};
		
		for(int i = 0 ; i<5 ; i++) {
			Planta act = lp.get(i);
			for(int j = 0; j<5 ; j++) {
				Planta act2 = lp.get(j);
				int index1= mfkm.getTradR().get(act.getId());
				int index2= mfkm.getTradR().get(act2.getId());
				assertTrue(mfkm.getMatriz()[i][j].equals(d[i][j]));
				
				index1= mfT.getTradR().get(act.getId());
				index2= mfT.getTradR().get(act2.getId());
				assertTrue(mfT.getMatriz()[i][j].equals(d[i][j]));
				
			}
		}
	}

}
