package control;

import logica.Cliente;
import logica.EstadoOrden;
import logica.Orden;
import logica.Producto;
import logica.LineaOrden;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

public class Controladora {
	private int consecutivoOrden;
	private int consecutivoProducto;
	private Map<String, Cliente> clientes;
	private Map<Integer, Orden> ordenes;
	private Map<Integer, Producto> productos;
	
	public Controladora() {
		consecutivoOrden = 100;
		consecutivoProducto = 800;
		clientes = new TreeMap<String, Cliente>();
		ordenes = new TreeMap<Integer, Orden>();
		productos = new TreeMap<Integer, Producto>();
	}
	
	//Clientes
	
	public List<Cliente> obtenerListadoClientes() {
		return new ArrayList<>(clientes.values());
	}
	
	public Cliente obtenerCliente(String idCliente) {
		return clientes.get(idCliente);
	}
	
	public List<Orden> obtenerListadoOrdenesCliente(String idCliente) {
		Cliente cliente = clientes.get(idCliente);
		return new ArrayList<>(cliente.getOrdenes().values());
	}
	
	public List<Orden> obtenerListadoOrdenesIniciadasCliente(String idCliente) {
		List<Orden> resultado = new ArrayList<>();
		
		for (Orden orden : clientes.get(idCliente).getOrdenes().values()) {
			if (orden.getEstado() == EstadoOrden.INICIADA) 
				resultado.add(orden);
		}
		return resultado;
	}
	
	public List<Orden> obtenerListadoOrdenesPendientesCliente(String idCliente) {
		List<Orden> resultado = new ArrayList<>();
		for (Orden orden : clientes.get(idCliente).getOrdenes().values()) {
			if (orden.getEstado() == EstadoOrden.PENDIENTE) 
				resultado.add(orden);
		}
		return resultado;
	}
	
	public List<Orden> obtenerListadoOrdenesTerminadasCliente(String idCliente) {
		List<Orden> resultado = new ArrayList<>();
		for (Orden orden : clientes.get(idCliente).getOrdenes().values()) {
			if (orden.getEstado() == EstadoOrden.TERMINADA) 
				resultado.add(orden);
		}
		return resultado;
	}
	
	public void crearCliente(String idCliente, String nombre, String email) {
		Cliente cliente = new Cliente(idCliente, nombre, email);
		clientes.put(idCliente, cliente);
	}
	
	public void actualizarCliente(String idCliente, String nombre, String email) {
		Cliente cliente = clientes.get(idCliente);
		cliente.setNombre(nombre);
		cliente.setEmail(email);
	}
	
	public void borrarCliente(String idCliente) {
		clientes.remove(idCliente);
	}
	
	//Productos
	
	public List<Producto> obtenerListadoProductos() {
		
	}
	
	public void crearProducto(String nombre, double existencias, String unidad, double precio) {
		
	}
	
	public Producto obtenerProducto(int codigoProducto) {
		
	}
	
	public void actualizarProducto(int codigoProducto, String nombre, double existencias, String unidad, double precio) {
		
	}
	
	public void borrarProducto(int codigoProducto) {
		
	}
	
	//Ordenes
	
	public List<Orden> obtenerListadoOrdenes() {
		
	}
	
	public double obtenerMontoTotalPendiente() {
		
	}
	
	public void crearOrdenVacia(String idCliente) {
		
	}
	
	public Orden obtenerOrden(int numeroOrden) {
		
	}
	
	public List<LineaOrden> obtenerLineasOrden(int numeroOrden) {
		
	}
	
	public void establecerOrdenPendiente(int numeroOrden) {
		
	}
	
	public void establecerOrdenTerminada(int numeroOrden) {
		
	}
	
	//Lineas 
	
	public void agregarLineaOrden(int numeroOrden, int codigoProducto, double cantidad) {
		
	}
	
	public void actualizarLineaOrden(int numeroOrden, int numeroLinea, int codigoProducto, double cantidad) {
		
	}
	
	public void borrarLineaOrden(int numeroOrden, int numeroLinea) {
		
	}
	
	public void borrarOrden(int numeroOrden) {
		
	}
	
}
