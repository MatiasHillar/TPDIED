package prueba;
	
import Dominio.Planta;	
import servicios.MapaService;

public class App {

	public static void main(String[] args) {
		MapaService m= new MapaService();
		Planta p1= new Planta(); p1.setId(1);
		Planta p3= new Planta(); p1.setId(3);
		System.out.println(m.menosKm(p1, p3));

	}

}
