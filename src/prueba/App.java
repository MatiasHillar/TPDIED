package prueba;

import java.util.ArrayList;

import dominio.*;
import servicios.MapaService;

public class App {

	public static void main(String[] args) {
		MapaService ms= new MapaService();
		Planta p1 = new Planta("p1", 1, new ArrayList<Stock>());
		Planta p2 = new Planta("p2", 2, new ArrayList<Stock>());
		Planta p3 = new Planta("p3", 3, new ArrayList<Stock>());
		Planta p4 = new Planta("p4", 4, new ArrayList<Stock>());
		Planta p5 = new Planta("p5", 5, new ArrayList<Stock>());
		System.out.println(ms.maxFlow(p1, p5));

	}

}
