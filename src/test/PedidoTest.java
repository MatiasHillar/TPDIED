package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dominio.*;
import servicios.PedidoService;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
