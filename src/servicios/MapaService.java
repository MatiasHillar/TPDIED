package servicios;

import java.util.*;

import dominio.*;



public class MapaService {
	private PlantaDao plantaDao = new PlantaDaoMysql();
	private RutaDao rutaDao = new RutaDaoMysql();
	
	
	public List<Ruta> buscarTodasRutas() {
		return rutaDao.buscarTodos();
	}
	public List<Planta> buscarTodasPlantas() {
		return plantaDao.buscarTodos();
	}
	
	public Mapa construir() {
		Mapa m = new Mapa();
		m.setListaPlantas(buscarTodasPlantas());
		m.setListaRutas(buscarTodasRutas());
		return m;
	}
	protected List<Planta> getAdyacentes(Planta p, Mapa m){ 
		List<Planta> salida = new ArrayList<Planta>();
		for(Ruta r : m.getListaRutas()){
			if(r.getPlantaOrigen().equals(p)){
				salida.add(r.getPlantaDestino());
			}
		}
		return salida;
	}
	
	public List<Ruta> menosKm(Planta origen, Planta destino){
		Mapa m = construir();
		HashMap<Planta, List<Planta>> caminos = new HashMap<Planta, List<Planta>>();
		HashMap<Planta,Float> minimos = new HashMap<Planta,Float>();
		
		PriorityQueue<Planta> pq = new PriorityQueue(m.getListaPlantas().size(), (p1,p2)->minimos.get(p1).compareTo(minimos.get(p2)));
		
	}
	
	
	
	
	

}
