package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dominio.*;
import servicios.InsumoService;
import servicios.PedidoService;
import servicios.PlantaService;
import servicios.StockService;

public class PedidoTest {

	@Test
	public void RegistroOrdenTest() {
		
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
		
		PedidoService PS = new PedidoService();
		
		PS.crearPedido(P);
		Estado esperado = Estado.CREADO;
		
		assertEquals(P.getEstado(), esperado);
	}
	
	@Test
	public void EntregarPedidoTest() {
		
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
		
		PedidoService PS = new PedidoService();
		
		PS.crearPedido(P);
		
		Planta Planta2 = new Planta();
		 
		P.setEstado(Estado.PROCESADO);
		PS.entregarPedido(P);
		Estado esperado = Estado.ENTREGADO;
		assertEquals(esperado, P.getEstado());
		
	}
	
//
//		@Test
//		public void RegistroOrdenTest2() {
//			
//			Planta Planta1 = new Planta();
//			Planta1.setId(1);
//			
//			Camion C = new Camion();
//			
//			List<ItemPedido> lista = new ArrayList<ItemPedido>();
//			
//			ItemPedido ip1 = new ItemPedido();
//			ItemPedido ip2 = new ItemPedido();
//			
//			Insumo I1 = new InsumoGral();
//			Insumo I2 = new InsumoLiquido();
//			
//			I2.setNombre("Aceite");
//			I2.setCosto(5f);
//		
//			I1.setNombre("Arena");
//			I1.setCosto(3f);
//			
//			ip1.setInsumo(I1);
//			ip1.setCtidad(10f);
//			ip2.setInsumo(I2);
//			ip2.setCtidad(7f);
//			
//			C.setPatente("AAA 123");
//			
//			lista.add(ip1);
//			lista.add(ip2);
//			
//			Pedido P = new Pedido();
//			P.setNroPedido(1);
//			P.setListaItems(lista);
//			P.setPlantaDestino(Planta1);
//			P.setFechaEntrega(LocalDate.now().plusDays(7));
//			P.setCamion(C);
//			P.setCostoEnvio(5f);
//			
//			PedidoService PS = new PedidoService();
//			
//			PS.crearPedido(P);
//			Estado esperado = Estado.CREADO;
//			
//			assertEquals(P.getEstado(), esperado);
//		}
	
	
	@Test
	public void ProcesarPedidoTest() {
		InsumoService IS = new InsumoService();
		StockService SS = new StockService();
		PlantaService PS = new PlantaService();
		
		
		Planta P1 = new Planta();
	//lanta P2 = new Planta();
		
		Unidad U1 = new Unidad();
		U1.setNombre("Kilogramo");
		U1.setSimbolo("KG");
		Unidad U2 = new Unidad();
		U2.setNombre("Litro");
		U2.setSimbolo("L");
		
		
		InsumoGral I1 = new InsumoGral();
		I1.setNombre("Arena");
		I1.setCosto(5f);
		I1.setDescripcion("Es Arena");
		I1.setPeso(2f);
		I1.setUnidadMedida(U1);
		IS.crearInsumoGral(I1);
		
		InsumoLiquido I2 = new InsumoLiquido();
		I2.setNombre("Aceite");
		I2.setCosto(4f);
		I2.setDescripcion("Es Aceite");
		I2.setDensidad(2.5f);
		I2.setUnidadMedida(U2);
		IS.crearInsumoLiquido(I2);
		
		Stock S1 = new Stock();
		S1.setInsumo(I1);
		S1.setCtidad(50f);
		S1.setP(P1);
		S1.setPuntoRepo(10f);
		
		Stock S2 = new Stock();
		S1.setInsumo(I2);
		S1.setCtidad(100f);
		S1.setP(P1);
		S1.setPuntoRepo(20f);
		
		List<Stock> lista1 = new ArrayList<Stock>();
		lista1.add(S1);
		lista1.add(S2);
		P1.setNombre("Planta 1");
		P1.setListaStock(lista1); 
		PS.crearPlanta(P1);
		SS.crearStock(S1);
		SS.crearStock(S2);
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

