package servicios;

import java.util.*;	
import java.util.function.*;
import java.util.stream.Collectors;

import dominio.*;



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
		while(!pq.isEmpty()) {
			aux=pq.poll();
			this.getRutas(aux, m).stream()
			.forEach(r-> {
				HashSet<List<Planta>> caminosAux, c2;
				if(minimos.get(r.getPlantaDestino()) > ( minimos.get(r.getPlantaOrigen()) + obtenerCosto.apply(r) ) ) {
					minimos.put(r.getPlantaDestino(), minimos.get(r.getPlantaOrigen()) + obtenerCosto.apply(r));	
					caminosAux= new HashSet<List<Planta>>();
					c2=caminos.get(r.getPlantaOrigen());
					for(List<Planta> lp : c2) {
						caminosAux.add(lp.stream().collect(Collectors.toList()));
					}

					for(List<Planta> cam : caminosAux) {
						cam.add(r.getPlantaDestino());
					}
					
					
					caminos.put(r.getPlantaDestino(), caminosAux);
					
					if(!pq.contains(r.getPlantaDestino()))
					pq.add(r.getPlantaDestino());
				}
				else if(minimos.get(r.getPlantaDestino()) == ( minimos.get(r.getPlantaOrigen()) + obtenerCosto.apply(r) )) {
					caminosAux= new HashSet<List<Planta>>();
					caminosAux.addAll(caminos.get(r.getPlantaOrigen()).stream().collect(Collectors.toList()));
					for(List<Planta> cam : caminosAux) {
						cam.add(r.getPlantaDestino());
					}
					caminos.get(r.getPlantaDestino()).addAll(caminosAux);
				}
			});
		}
		return caminos.get(destino);
		
	}
	
	
	
	
	

}
