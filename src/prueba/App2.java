package prueba;

import java.util.ArrayList;

import dominio.Planta;
import dominio.Stock;
import servicios.MapaService;

public class App2 {

	public static void main(String[] args) {

		Planta p1 = new Planta("p1", 1, new ArrayList<Stock>());
		Planta p2 = new Planta("p2", 2, new ArrayList<Stock>());
		Planta p3 = new Planta("p3", 3, new ArrayList<Stock>());
		Planta p4 = new Planta("p4", 4, new ArrayList<Stock>());
		Planta p5 = new Planta("p5", 5, new ArrayList<Stock>());
		
		
		MapaService m = new MapaService();
		System.out.println(m.menosTiempo(p1, p5));
		
	}

}
