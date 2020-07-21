package servicios;

import java.util.*;	
import java.util.function.*;
import java.util.stream.Collectors;

import Dominio.*;



public class MapaService {
	/*
	private PlantaDao plantaDao = new PlantaDaoMysql();
	private RutaDao rutaDao = new RutaDaoMysql();
	
	
	public List<Ruta> buscarTodasRutas() {
		return rutaDao.buscarTodos();
	}
	public List<Planta> buscarTodasPlantas() {
		return plantaDao.buscarTodos();
	}
	*/
	
	public Mapa construir() {
		
		Mapa m = new Mapa();
		/*
		m.setListaPlantas(buscarTodasPlantas());
		m.setListaRutas(buscarTodasRutas());
		*/
		Planta p1= new Planta(); p1.setId(1);
		Planta p2= new Planta(); p1.setId(2);
		Planta p3= new Planta(); p1.setId(3);
		Planta p4= new Planta(); p1.setId(4);
		List<Planta> p = new ArrayList<Planta>();
		p.add(p1); p.add(p2); p.add(p3); p.add(p4);
		m.setListaPlantas(p);
		Ruta r1 = new Ruta(); r1.setPlantaOrigen(p1); r1.setPlantaDestino(p2); r1.setDistanciaKm(10f); 
		Ruta r2 = new Ruta(); r2.setPlantaOrigen(p2); r2.setPlantaDestino(p3); r2.setDistanciaKm(10f); 
		Ruta r3 = new Ruta(); r3.setPlantaOrigen(p1); r3.setPlantaDestino(p3); r3.setDistanciaKm(20f); 

		List<Ruta> r = new ArrayList<Ruta>();
		r.add(r1); r.add(r2); r.add(r3);
		m.setListaRutas(r);
		
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
	
	protected List<Ruta> getRutas(Planta p, Mapa m){ 
		List<Ruta> salida = new ArrayList<Ruta>();
		for(Ruta r : m.getListaRutas()){
			if(r.getPlantaOrigen().equals(p)){
				salida.add(r);
			}
		}
		return salida;
	}
	
	public Set<List<Planta>> menosKm(Planta origen, Planta destino){
		return this.menosCosto(origen, destino, r-> r.getDistanciaKm());
	}
	public Set<List<Planta>> menosTiempo(Planta origen, Planta destino){
		return this.menosCosto(origen, destino, r-> r.getDuracionMin());
	}
	
	private Set<List<Planta>> menosCosto(Planta origen, Planta destino, Function<Ruta,Float> obtenerCosto){
		Mapa m = construir();
		HashMap<Planta, HashSet<List<Planta>>> caminos = new HashMap<Planta, HashSet<List<Planta>>>();
		HashMap<Planta,Float> minimos = new HashMap<Planta,Float>();		
		PriorityQueue<Planta> pq = new PriorityQueue<Planta>(m.getListaPlantas().size(), (p1,p2)->minimos.get(p1).compareTo(minimos.get(p2)));
		m.getListaPlantas().stream()
		.forEach(pl -> {
			 minimos.put(pl, Float.MAX_VALUE);
			 caminos.put(pl, new HashSet<List<Planta>>());
		});
		minimos.put(origen, 0f);
		List<Planta> paux=  new ArrayList<Planta>();
		paux.add(origen);
		caminos.get(origen).add(paux);
		pq.add(origen);
		Planta aux;
		HashSet<List<Planta>> caminosAux;
		while(!pq.isEmpty()) {
			aux=pq.poll();
			this.getRutas(aux, m).stream()
			.forEach(r-> {
				if(minimos.get(r.getPlantaDestino()) > ( minimos.get(r.getPlantaOrigen()) + obtenerCosto.apply(r) ) ) {
					minimos.put(r.getPlantaDestino(), minimos.get(r.getPlantaOrigen()) + obtenerCosto.apply(r));	
					caminosAux= new HashSet<List<Planta>>();
					caminosAux.addAll(caminos.get(r.getPlantaOrigen()).stream().collect(Collectors.toList()));
					for(List<Planta> cam : caminosAux) {
						cam.add(r.getPlantaDestino());
					}
					caminos.put(r.getPlantaDestino(), caminosAux);
					pq.add(r.getPlantaDestino());
				}
				else if(minimos.get(r.getPlantaDestino()) == ( minimos.get(r.getPlantaOrigen()) + obtenerCosto.apply(r) )) {
					caminosAux= new HashSet<List<Planta>>();
					caminosAux.addAll(caminos.get(r.getPlantaOrigen()).stream().collect(Collectors.toList()));
					for(List<Planta> cam : caminosAux) {
						cam.add(r.getPlantaDestino());
					}
					caminos.get(r.getPlantaDestino()).addAll(caminosAux);
					pq.add(r.getPlantaDestino());
				}
			});
		}
		return caminos.get(destino);
		
	}
	
	
	
	
	

}
