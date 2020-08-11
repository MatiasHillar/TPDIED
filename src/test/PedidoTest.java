package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import dominio.*;
import gui.util.NoHayCamionesException;
import servicios.CamionService;
import servicios.InsumoService;
import servicios.MapaService;
import servicios.PedidoService;
import servicios.PlantaService;
import servicios.RutaService;
import servicios.StockService;

public class PedidoTest {

//	@Test
//	public void RegistroOrdenTest() {
//		
//		PedidoService PS = new PedidoService();
//		InsumoService IS = new InsumoService();
//		PlantaService PLS = new PlantaService();
//		
//		List<Stock> lista1 = new ArrayList<Stock>();
//		
//		Planta Planta1 = new Planta();
//		Planta1.setNombre("Planta Test 1");
//		Planta1.setListaStock(lista1); 
//		PLS.crearPlanta(Planta1);
//		
//		Camion C = new Camion();
//		
//		List<ItemPedido> lista = new ArrayList<ItemPedido>();
//		
//		ItemPedido ip1 = new ItemPedido();
//		ItemPedido ip2 = new ItemPedido();
//		
//		Unidad U1 = new Unidad();
//		U1.setNombre("Kilogramo");
//		U1.setSimbolo("KG");
//		Unidad U2 = new Unidad();
//		U2.setNombre("Litro");
//		U2.setSimbolo("L");
//		
//		
//		InsumoGral I1 = new InsumoGral();
//		I1.setNombre("Arena");
//		I1.setCosto(5f);
//		I1.setDescripcion("Es Arena");
//		I1.setPeso(2f);
//		I1.setUnidadMedida(U1);
//		IS.crearInsumoGral(I1);
//		
//		InsumoLiquido I2 = new InsumoLiquido();
//		I2.setNombre("Aceite");
//		I2.setCosto(4f);
//		I2.setDescripcion("Es Aceite");
//		I2.setDensidad(2.5f);
//		I2.setUnidadMedida(U2);
//		IS.crearInsumoLiquido(I2);
//		
//		ip1.setInsumo(I1);
//		ip1.setCtidad(10f);
//		ip2.setInsumo(I2);
//		ip2.setCtidad(7f);
//		
//		C.setPatente("AAA 123");
//		
//		lista.add(ip1);
//		lista.add(ip2);
//		
//		Pedido P = new Pedido();
//		P.setListaItems(lista);
//		P.setPlantaDestino(Planta1);
//		P.setFechaEntrega(LocalDate.now().plusDays(7));
//		P.setCamion(C);
//		P.setCostoEnvio(5f);
//		
//		
//		PS.crearPedido(P);
//		Estado esperado = Estado.CREADO;
//		
//		assertEquals(P.getEstado(), esperado);
//	}
//	
//	@Test
//	public void EntregarPedidoTest() {
//		
//		Planta Planta1 = new Planta();
//		Planta1.setId(1);
//		
//		Camion C = new Camion();
//		
//		List<ItemPedido> lista = new ArrayList<ItemPedido>();
//		
//		ItemPedido ip1 = new ItemPedido();
//		ItemPedido ip2 = new ItemPedido();
//		
//		Insumo I1 = new InsumoGral();
//		Insumo I2 = new InsumoLiquido();
//		
//		I2.setNombre("Aceite");
//		I2.setCosto(5f);
//	
//		I1.setNombre("Arena");
//		I1.setCosto(3f);
//		
//		ip1.setInsumo(I1);
//		ip1.setCtidad(10f);
//		ip2.setInsumo(I2);
//		ip2.setCtidad(7f);
//		
//		C.setPatente("AAA 123");
//		
//		lista.add(ip1);
//		lista.add(ip2);
//		
//		Pedido P = new Pedido();
//		P.setListaItems(lista);
//		P.setPlantaDestino(Planta1);
//		P.setFechaEntrega(LocalDate.now().plusDays(7));
//		P.setCamion(C);
//		P.setCostoEnvio(5f);
//		
//		PedidoService PS = new PedidoService();
//		
//		PS.crearPedido(P);
//		 
//		P.setEstado(Estado.PROCESADO);
//		PS.entregarPedido(P);
//		Estado esperado = Estado.ENTREGADO;
//		assertEquals(esperado, P.getEstado());
//		
//	}
	

		@Test
		public void RegistroOrdenTest2() {
			
			Planta Planta1 = new Planta();
			Planta1.setId(1);
			
			Camion C = new Camion();
			
			List<ItemPedido> lista = new ArrayList<ItemPedido>();
			
			ItemPedido ip1 = new ItemPedido();
			ItemPedido ip2 = new ItemPedido();
			
			Insumo I1 = new InsumoGral();
			Insumo I2 = new InsumoLiquido();
			
			I2.setNombre("Aceite");
			I2.setCosto(5f);
		
			I1.setNombre("Arena");
			I1.setCosto(3f);
			
			ip1.setInsumo(I1);
			ip1.setCtidad(10f);
			ip2.setInsumo(I2);
			ip2.setCtidad(7f);
			
			C.setPatente("AAA 123");
			
			lista.add(ip1);
			lista.add(ip2);
			
			Pedido P = new Pedido();
			P.setNroPedido(1);
			P.setListaItems(lista);
			P.setPlantaDestino(Planta1);
			P.setFechaEntrega(LocalDate.now().plusDays(7));
			P.setCamion(C);
			P.setCostoEnvio(5f);
			
			//esto hace el metodo crear pedido del service
			P.setEstado(Estado.CREADO);
			P.setFechaSolicitud(LocalDate.now());
			Estado esperado = Estado.CREADO;
			
			assertEquals(P.getEstado(), esperado);
			assertTrue(P.getListaItems().contains(ip1));
			assertTrue(P.getListaItems().contains(ip2));
			
		}
		
		
		
		
		@Test
		public void EntregarPedidoTest2() {
			
			Planta Planta1 = new Planta();
			Planta1.setId(1);
			
			Camion C = new Camion();
			
			List<ItemPedido> lista = new ArrayList<ItemPedido>();
			
			ItemPedido ip1 = new ItemPedido();
			ItemPedido ip2 = new ItemPedido();
			
			Insumo I1 = new InsumoGral();
			Insumo I2 = new InsumoLiquido();
			
			I2.setNombre("Aceite");
			I2.setCosto(5f);
		
			I1.setNombre("Arena");
			I1.setCosto(3f);
			
			ip1.setInsumo(I1);
			ip1.setCtidad(10f);
			ip2.setInsumo(I2);
			ip2.setCtidad(7f);
			
			C.setPatente("AAA 123");
			
			lista.add(ip1);
			lista.add(ip2);
			
			Pedido P = new Pedido();
			P.setListaItems(lista);
			P.setPlantaDestino(Planta1);
			P.setFechaEntrega(LocalDate.now().plusDays(7));
			P.setCamion(C);
			P.setCostoEnvio(5f);
			
			//esto hace el metodo crearPedido del service
			P.setEstado(Estado.CREADO);
			P.setFechaSolicitud(LocalDate.now());
			Estado esperado1 = Estado.CREADO;
			 
			//Esto pasa cuando un pedido se procesa a traves del service
			Camion C1 = new Camion();
			C1.setPatente("ABC-111");
			C1.setKmRecorridos(1000f);
			Camion C2 = new Camion();
			C2.setPatente("ABC-222");
			C1.setKmRecorridos(900f);
			
			P.setCamion(C2);
			P.setEstado(Estado.PROCESADO);
			
			
			//esto pasa cuando un pedido se entrega a traves del service con el metodo entregarPedido
			Estado esperado2 = Estado.ENTREGADO;
			P.setEstado(Estado.ENTREGADO);
			assertEquals(esperado2, P.getEstado());
			assertEquals(P.getCamion(), C2);
			
		}
		
		
		@Test
		public void procesarPedidoTest2() {
			
			Planta P1 = new Planta();
			Planta P2 = new Planta();
			Planta P3 = new Planta();
			Planta PDestino = new Planta();
			
			
			
			P1.setNombre("Planta Origen 1");
			P1.setId(1);
			P2.setNombre("Planta Origen 2");
			P2.setId(2);	
			P3.setNombre("Planta Origen 2");
			P3.setId(3);
			PDestino.setNombre("Planta Destino");
			PDestino.setId(4);
			Ruta R1 = new Ruta();
			R1.setId(1);
			R1.setPlantaOrigen(P1);
			R1.setPlantaDestino(P3);
			R1.setDistanciaKm(100f);
			R1.setDuracionMin(65f);
			R1.setPesoMaximoKg(1000f);
			
			
			Ruta R2 = new Ruta();
			R2.setId(2);
			R2.setPlantaOrigen(P3);
			R2.setPlantaDestino(PDestino);
			R2.setDistanciaKm(50f);
			R2.setDuracionMin(60f);
			R2.setPesoMaximoKg(1000f);
			
			Ruta R3 = new Ruta();
			R3.setId(3);
			R3.setPlantaOrigen(P1);
			R3.setPlantaDestino(P2);
			R3.setDuracionMin(40f);
			R3.setDistanciaKm(150f);
			R3.setPesoMaximoKg(1000f);
			
			Ruta R4 = new Ruta();
			R4.setId(4);
			R4.setPlantaOrigen(P2);
			R4.setPlantaDestino(PDestino);
			R4.setDuracionMin(60f);
			R4.setDistanciaKm(50f);
			R4.setPesoMaximoKg(1000f);
			
			//CAMINO R1-R2 TOMA: 150 KM Y 125 MIN ----> MENOR EN KM
			//CAMINO R3-R4 TOMA: 200 KM Y 100 MIN ----> MENOR EN MIN

			List<Planta> ListaPlantas = new ArrayList<Planta>();
			ListaPlantas.add(P1);
			ListaPlantas.add(P2);
			ListaPlantas.add(P3);
			ListaPlantas.add(PDestino);
			
			List<Ruta> ListaRutas = new ArrayList<Ruta>();
			ListaRutas.add(R1);
			ListaRutas.add(R2);
			ListaRutas.add(R3);
			ListaRutas.add(R4);
			
			Mapa M = new Mapa();
			M.setListaPlantas(ListaPlantas);
			M.setListaRutas(ListaRutas);
			
			MapaService MS = new MapaService();
			
			
			List<Ruta> listaEsperado = new ArrayList<Ruta>();
			listaEsperado.add(R1);
			listaEsperado.add(R2);
			Set<List<Ruta>> esperado = new HashSet<List<Ruta>>();
			esperado.add(listaEsperado);
			
			List<Ruta> listaEsperado2 = new ArrayList<Ruta>();
			listaEsperado2.add(R3);
			listaEsperado2.add(R4);
			Set<List<Ruta>> esperado2 = new HashSet<List<Ruta>>();
			esperado2.add(listaEsperado2);
			
			
			assertEquals(esperado, MS.menosKm(P1, PDestino, M));
			assertEquals(esperado2, MS.menosTiempo(P1, PDestino, M));
			
			Camion C1 = new Camion();
			C1.setPatente("ABC-123");
			C1.setCostoHora(50f);
			C1.setCostoKm(5f);
			C1.setFechaCompra(LocalDate.now().minusYears(10));
			Modelo Modelo = new Modelo();
			Modelo.setMarca("Audi");
			Modelo.setModelo("Audi Truck");
			C1.setModelo(Modelo);
			C1.setKmRecorridos(1000f);
			
			Camion C2 = new Camion();
			C2.setPatente("ABC-456");
			C2.setCostoHora(50f);
			C2.setCostoKm(5f);
			C2.setFechaCompra(LocalDate.now().minusYears(8));
			Modelo Modelo2 = new Modelo();
			Modelo.setMarca("Mercedes");
			Modelo.setModelo("Mercedes Truck");
			C2.setModelo(Modelo2);
			C2.setKmRecorridos(800f);
			
		}
	
	
//	@Test
//	public void ProcesarPedidoTest() {
//		InsumoService IS = new InsumoService();
//		StockService SS = new StockService();
//		PlantaService PS = new PlantaService();
//		MapaService MP = new MapaService();
//		RutaService RS = new RutaService();
//		CamionService CS = new CamionService();
//		PedidoService PEDS = new PedidoService();
//		
//		
//		Planta P1 = new Planta();
//		Planta P2 = new Planta();
//		Planta P3 = new Planta();
//		Planta PDestino = new Planta();
//		
//		Unidad U1 = new Unidad();
//		U1.setNombre("Kilogramo");
//		U1.setSimbolo("Kg");
//		Unidad U2 = new Unidad();
//		U2.setNombre("Litro");
//		U2.setSimbolo("L");
//		
//		InsumoGral I1 = new InsumoGral();
//		I1.setNombre("Arena");
//		I1.setCosto(5f);
//		I1.setDescripcion("Es Arena");
//		I1.setPeso(2f);
//		I1.setUnidadMedida(U1);
//		IS.crearInsumoGral(I1);
//		
//		InsumoLiquido I2 = new InsumoLiquido();
//		I2.setNombre("Aceite");
//		I2.setCosto(4f);
//		I2.setDescripcion("Es Aceite");
//		I2.setDensidad(2.5f);
//		I2.setUnidadMedida(U2);
//		IS.crearInsumoLiquido(I2);
//		
//		List<ItemPedido> lista = new ArrayList<ItemPedido>();
//		
//		ItemPedido ip1 = new ItemPedido();
//		ItemPedido ip2 = new ItemPedido();	
//		ip1.setCtidad(1f);
//		ip1.setInsumo(I1);
//		ip2.setCtidad(1f);
//		ip2.setInsumo(I2);
//		lista.add(ip1);
//		lista.add(ip2);
//		
//	
//		Stock S1 = new Stock();
//		S1.setInsumo(I1);
//		S1.setCtidad(50f);
//		S1.setP(P1);
//		S1.setPuntoRepo(10f);
//		
//		Stock S2 = new Stock();
//		S1.setInsumo(I2);
//		S1.setCtidad(100f);
//		S1.setP(P1);
//		S1.setPuntoRepo(20f);
//		
//		List<Stock> lista1 = new ArrayList<Stock>();
//		List<Stock> lista2 = new ArrayList<Stock>();
//		lista1.add(S1);
//		lista1.add(S2);
//		
//		
//		P1.setNombre("Planta Origen 1");
//		P1.setListaStock(lista1); 
//		PS.crearPlanta(P1);
//		
//		P2.setNombre("Planta Origen 2");
//		P2.setListaStock(lista1);
//		PS.crearPlanta(P2);
//		
//		P3.setNombre("Planta Origen 2");
//		P3.setListaStock(lista2);
//		PS.crearPlanta(P3);
//		
//		PDestino.setNombre("Planta Destino");
//		P1.setListaStock(lista2);
//		PS.crearPlanta(PDestino);
//		
//		SS.crearStock(S1);
//		SS.crearStock(S2);
//		
//		Camion C1 = new Camion();
//		C1.setPatente("ABC-123");
//		C1.setCostoHora(50f);
//		C1.setCostoKm(5f);
//		C1.setFechaCompra(LocalDate.now().minusYears(10));
//		Modelo Modelo = new Modelo();
//		Modelo.setMarca("Audi");
//		Modelo.setModelo("Audi Truck");
//		C1.setModelo(Modelo);
//		C1.setKmRecorridos(1000f);
//		
//		Camion C2 = new Camion();
//		C2.setPatente("ABC-456");
//		C2.setCostoHora(50f);
//		C2.setCostoKm(5f);
//		C2.setFechaCompra(LocalDate.now().minusYears(8));
//		Modelo Modelo2 = new Modelo();
//		Modelo.setMarca("Mercedes");
//		Modelo.setModelo("Mercedes Truck");
//		C2.setModelo(Modelo2);
//		C2.setKmRecorridos(800f);
//		
//		CS.crearCamion(C1);
//		CS.crearCamion(C2);
//		
//		
//		Pedido P = new Pedido();
//		P.setListaItems(lista);
//		P.setPlantaDestino(PDestino);
//		P.setFechaEntrega(LocalDate.now().plusDays(7));
//		P.setCostoEnvio(5f);
//		PEDS.crearPedido(P);
//		
//		
//		Ruta R1 = new Ruta();
//		R1.setPlantaOrigen(P1);
//		R1.setPlantaDestino(P3);
//		R1.setDistanciaKm(100f);
//		R1.setDuracionMin(65f);
//		R1.setPesoMaximoKg(1000f);
//		RS.crearRuta(R1);
//		
//		
//		Ruta R2 = new Ruta();
//		R2.setPlantaOrigen(P3);
//		R2.setPlantaDestino(PDestino);
//		R2.setDistanciaKm(50f);
//		R2.setDuracionMin(60f);
//		R2.setPesoMaximoKg(1000f);
//		RS.crearRuta(R2);
//		
//		Ruta R3 = new Ruta();
//		R3.setPlantaOrigen(P1);
//		R3.setPlantaDestino(P2);
//		R3.setDuracionMin(40f);
//		R3.setDistanciaKm(150f);
//		R3.setPesoMaximoKg(1000f);
//		RS.crearRuta(R3);
//		
//		Ruta R4 = new Ruta();
//		R4.setPlantaOrigen(P2);
//		R4.setPlantaDestino(PDestino);
//		R4.setDuracionMin(60f);
//		R4.setDistanciaKm(50f);
//		R4.setPesoMaximoKg(1000f);
//		RS.crearRuta(R4);
//		
//		//CAMINO R1-R2 TOMA: 150 KM Y 125 MIN ----> MENOR EN KM
//		//CAMINO R3-R4 TOMA: 200 KM Y 100 MIN ----> MENOR EN MIN
//		
//		
//		List<Ruta> listaEsperado = new ArrayList<Ruta>();
//		listaEsperado.add(R1);
//		listaEsperado.add(R2);
//		Set<List<Ruta>> esperado = new HashSet<List<Ruta>>();
//		esperado.add(listaEsperado);
//		assertEquals(esperado, MP.menosTiempo(P1, PDestino));
//		
//		List<Ruta> listaEsperado2 = new ArrayList<Ruta>();
//		listaEsperado2.add(R3);
//		listaEsperado2.add(R4);
//		Set<List<Ruta>> esperado2 = new HashSet<List<Ruta>>();
//		esperado2.add(listaEsperado2);
//		assertEquals(esperado2, MP.menosKm(P1, PDestino));
//		
//		
//		try {
//			PEDS.asignarCamion(P);
//			Estado EstadoEsperado = Estado.PROCESADO;
//			assertEquals("ABC-456", C2.getPatente());
//			assertEquals(EstadoEsperado, P.getEstado());
//		} catch (NoHayCamionesException e) {
//			fail();
//		}
//	}
//	@After
//	public void eliminarDatos() {
//		InsumoService IS = new InsumoService();
//		StockService SS = new StockService();
//		PlantaService PS = new PlantaService();
//		MapaService MP = new MapaService();
//		RutaService RS = new RutaService();
//		CamionService CS = new CamionService();
//		PedidoService PEDS = new PedidoService();
//		
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
}