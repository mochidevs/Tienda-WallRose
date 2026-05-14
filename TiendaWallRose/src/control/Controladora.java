package control;

import logica.Cliente;
import logica.Orden;
import logica.Producto;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

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
		
	}
	
	public Cliente obtenerCliente(String idCliente) {
		
	}
	
	public List<Orden> obtenerListadoOrdenesCliente(String idCliente) {
		
	}
	
	public List<Orden> obtenerListadoOrdenesIniciadasCliente(String idCliente) {
		
	}
	
	public List<Orden> obtenerListadoOrdenesPendientesCliente(String idCliente) {
		
	}
	
	public List<Orden> obtenerListadoOrdenesTerminadasCliente(String idCliente) {
		
	}
	
	public void crearCliente(String idCliente, String nombre, String email) {
		
	}
	
	public void actualizarCliente(String idCliente, String nombre, String email) {
		
	}
	
	public void borrarCliente(String idCliente) {
		
	}
	
	//Productos
	
	public List<Producto> obtenerListadoProductos() {
		
	}
	
	public void crearProducto(String nombre, double existencias, String unidad, double precio) {
		
	}
	
	public Producto obtenerProducto(int codigoProducto) {
		
	}
	
	
	
}
