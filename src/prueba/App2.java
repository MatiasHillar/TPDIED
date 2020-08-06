package prueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dominio.Mapa;
import dominio.Planta;
import dominio.Ruta;
import dominio.Stock;
import dominio.util.MatrizFloyd;
import servicios.MapaService;

public class App2 {

	public static void main(String[] args) {
/*
		Planta p1 = new Planta("p1", 1, new ArrayList<Stock>());
		Planta p2 = new Planta("p2", 2, new ArrayList<Stock>());
		Planta p3 = new Planta("p3", 3, new ArrayList<Stock>());
		Planta p4 = new Planta("p4", 4, new ArrayList<Stock>());
		Planta p5 = new Planta("p5", 5, new ArrayList<Stock>());
		
		
		MapaService m = new MapaService();
		System.out.println(m.menosTiempo(p1, p5));
		
		Float[][] d = {{0f,1f,Float.MAX_VALUE,5f,7f},
				{10f,0f,Float.MAX_VALUE,4f,6f},
				{3f,2f,0f,6f,4f},
				{6f,7f,Float.MAX_VALUE,0f,2f},
				{9f,10f,Float.MAX_VALUE,3f,0f}};
		
		List<Integer> l= new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		Integer[] a = l.toArray(new Integer[0]);
		for(Integer i : a) {
			System.out.println(i);
		}
		
	*/
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
		
		Float[]d = {0f,1f,Float.MAX_VALUE,5f,7f};
		System.out.println(Arrays.toString(d));
		//System.out.println(mfkm);
	}
}
