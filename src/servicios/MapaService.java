package servicios;

import java.util.*;		
import java.util.function.*;
import java.util.stream.Collectors;

import dominio.*;
import dominio.util.MatrizFloyd;



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
	
	
	public MatrizFloyd caminosMenosKm(){
		return this.todosMenoresCaminos(r-> r.getDistanciaKm());
	}
	public MatrizFloyd caminosMenosTiempo(){
		return this.todosMenoresCaminos(r-> r.getDuracionMin());
	}
	
	private MatrizFloyd todosMenoresCaminos(Function<Ruta,Float> obtenerCosto){
		Mapa m = construir();
		List<Planta> lp = m.getListaPlantas();
		Integer ctidad = lp.size();
		HashMap<Integer,Integer> idAIndex = new HashMap<Integer, Integer>();
		HashMap<Integer,Planta> indexAPlanta = new HashMap<Integer,Planta>();
		Float[][] d = new Float[ctidad][ctidad];
		for(int i = 0 ; i<ctidad ; i++) {
			for(int j=0 ; j<ctidad ; j++) {
				d[i][j]=Float.MAX_VALUE;
			}
		}
		for(int i = 0 ; i<ctidad ; i++) {
			d[i][i]=0f;
			idAIndex.put(lp.get(i).getId(), i);
			indexAPlanta.put(i, lp.get(i));
		}
		m.getListaRutas().stream()
		.forEach(r-> {
			Integer ipo = idAIndex.get(r.getPlantaOrigen().getId()),
					ipd = idAIndex.get(r.getPlantaDestino().getId());
			if(d[ipo][ipd] > obtenerCosto.apply(r) ) {
				d[ipo][ipd] = obtenerCosto.apply(r);
			}
		});
		
		for(int k = 0 ; k<ctidad ; k++) {
			for(int i = 0 ; i<ctidad ; i++) {
				for(int j = 0 ; j<ctidad ; j++) {
					if(d[i][j]>d[i][k]+d[k][j]) 
						d[i][j]=d[i][k]+d[k][j];
				}
			}
		}
		
		return new MatrizFloyd(d,indexAPlanta);
	}
	
	public TreeMap<Double, Planta> pageRank() {
		Mapa m = construir();
		List<Planta> lp = m.getListaPlantas();
		Integer ctidad = lp.size();
		HashMap<Integer,Integer> idAIndex = new HashMap<Integer, Integer>();
		HashMap<Integer,Planta> indexAPlanta = new HashMap<Integer,Planta>();
		Double[] pr = new Double[ctidad];
		Double var = 0.0002d,
				d=0.5d,
				maxvar=1d,
				aux,
				anterior;
		
		for(int i = 0 ; i<ctidad ; i++) {
			pr[i]=1d;
			idAIndex.put(lp.get(i).getId(), i);
			indexAPlanta.put(i, lp.get(i));
		}
		
		while(maxvar>var) {
			for(int i = 0 ; i<ctidad ; i++) {
				anterior= pr[i];
				pr[i]=(1-d);
				aux=0d;
				for(Planta p : lp) {
					if(this.getAdyacentes(p, m).contains(indexAPlanta.get(i))) {
						aux+= (pr[idAIndex.get(p.getId())] / ((double) (this.getAdyacentes(p, m).size() )) );
					}
				}
				pr[i]+=(d*aux);
				maxvar = Math.max(maxvar, Math.abs(anterior-pr[i]));
			}
		}
		TreeMap<Double, Planta> resultado = new TreeMap<Double, Planta>((d1,d2)->d2.compareTo(d1));
		for(int i = 0 ; i<ctidad ; i++) {
			resultado.put(pr[i], indexAPlanta.get(i));
		}
		return resultado;
		
	}
	
	
	

}
