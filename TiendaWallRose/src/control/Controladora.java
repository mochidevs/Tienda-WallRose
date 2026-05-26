package control;

import logica.Cliente;
import logica.EstadoOrden;
import logica.Orden;
import logica.Producto;
import logica.LineaOrden;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Controladora {
	
	public static Controladora instancia;
	
	private int consecutivoOrden;
	private int consecutivoProducto;
	private Map<String, Cliente> clientes;
	private Map<Integer, Orden> ordenes;
	private Map<Integer, Producto> productos;
	
	//Esconder constructor
	private Controladora() {
		this. consecutivoOrden = 100;
		this.consecutivoProducto = 600;
		this.clientes = new TreeMap<String, Cliente>();
		this.ordenes = new TreeMap<Integer, Orden>();
		this.productos = new TreeMap<Integer, Producto>();
	}
	
	//Acceso global
	public static Controladora getInstancia() {
		if (instancia == null) {
			instancia = new Controladora();
		}
		return instancia;
	}
	
	//Clientes
	
	public List<Cliente> obtenerListadoClientes() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		for (Map.Entry<String, Cliente> e : clientes.entrySet()) {
			listaClientes.add(e.getValue());
		}
		return listaClientes;
	}
	
	private void verificarClienteExistente(String idCliente) throws Exception {
		if (clientes.containsKey(idCliente))
			throw new Exception("Cliente no encontrado");
	}
	
	private boolean esEmailValido(String email) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9_!#\\$%&'*+/=?^{|}~.-]+@[a-zA-Z0-9.-]+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	private void verificarEmail(String email) throws Exception {
		if (!esEmailValido(email))
			throw new Exception("Email no válido.");
	}
	
	private void verificarProductoExistente(Integer codigoProducto) throws Exception {
		if (!productos.containsKey(codigoProducto))
			throw new Exception("Producto no encontrado.");
	}
	
	private void verificarOrdenExistente(Integer numeroOrden) throws Exception {
		if (!ordenes.containsKey(numeroOrden))
			throw new Exception("Orden no encontrada.");
	}
	
	
	public Cliente obtenerCliente(String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		return clientes.get(idCliente);
	}
	
	public List<Orden> obtenerListadoOrdenesCliente(String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		Cliente cliente = clientes.get(idCliente);
		List<Orden> listaOrdenes = new ArrayList<Orden>();
		Map<Integer, Orden> ordenes = cliente.getOrdenes();
		for (Orden o : ordenes.values()) {
			listaOrdenes.add(o);
		}
		return listaOrdenes;
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
	
	public void crearCliente(String idCliente, String nombre, String email) throws Exception {
		verificarEmail(email);
		Cliente cliente = new Cliente(idCliente, nombre, email);
		clientes.put(idCliente, cliente);
	}
	
	public void actualizarCliente(String idCliente, String nombre, String email) throws Exception {
		verificarEmail(email);
		Cliente cliente = clientes.get(idCliente);
		cliente.setNombre(nombre);
		cliente.setEmail(email);
	}
	
	public void borrarCliente(String idCliente) throws Exception {
		verificarClienteExistente(idCliente);
		Cliente cliente = clientes.get(idCliente);
		Map<Integer, Orden> ordenes = cliente.getOrdenes();
		for (Integer numeroOrden : ordenes.keySet()) {
			ordenes.remove(numeroOrden);
		}
		clientes.remove(idCliente);
	}
	
	//Productos
	
	public List<Producto> obtenerListadoProductos() {
		
		return new ArrayList<>(productos.values());
	}
	
	public void crearProducto(String nombre, double existencias, String unidad, double precio) {
		int codigo = consecutivoProducto++;
		Producto producto = new Producto(codigo, nombre, existencias, unidad, precio);
		productos.put(codigo, producto);
	}
	
	public Producto obtenerProducto(int codigoProducto) {
		return productos.get(codigoProducto);
	}
	
	public void actualizarProducto(int codigoProducto, String nombre, double existencias, String unidad, double precio) {
		Producto producto = productos.get(codigoProducto);
		producto.setNombre(nombre);
		producto.setExistencias(existencias);
		producto.setUnidad(unidad);
		producto.setPrecio(precio);
	}
	
	public void borrarProducto(int codigoProducto) {
		productos.remove(codigoProducto);
	}
	
	//Ordenes
	
	public List<Orden> obtenerListadoOrdenes() {
		return new ArrayList<>(ordenes.values());
	}
	
	public double obtenerMontoTotalPendiente() {
		double total = 0;
		for (Orden orden : ordenes.values()) {
			if (orden.getEstado() == EstadoOrden.PENDIENTE)
				total += orden.calcularMontoTotal();
		}
		return total;
	}
	
	public void crearOrdenVacia(String idCliente) {
		Cliente cliente = clientes.get(idCliente);
		int numero = consecutivoOrden++;
		Orden orden = new Orden(numero, cliente);
		ordenes.put(numero, orden);
		cliente.agregarOrden(orden);
	}
	
	public Orden obtenerOrden(int numeroOrden) {
		return ordenes.get(numeroOrden);
	}
	
	public List<LineaOrden> obtenerLineasOrden(int numeroOrden) {
		return ordenes.get(numeroOrden).getLineas();
	}
	
	public void establecerOrdenPendiente(int numeroOrden) {
		ordenes.get(numeroOrden).setEstado(EstadoOrden.PENDIENTE);
	}
	
	public void establecerOrdenTerminada(int numeroOrden) {
		ordenes.get(numeroOrden).setEstado(EstadoOrden.TERMINADA);
	}
	
	//Lineas de orden
	
	public void agregarLineaOrden(int numeroOrden, int codigoProducto, double cantidad) {
		Orden orden = ordenes.get(numeroOrden);
		Producto producto = productos.get(codigoProducto);
		LineaOrden linea = new LineaOrden(cantidad, producto);
		orden.agregarLinea(linea);
	}
	
	public void actualizarLineaOrden(int numeroOrden, int numeroLinea, int codigoProducto, double cantidad) {
		Orden orden = ordenes.get(numeroOrden);
		LineaOrden linea = orden.getLineas().get(numeroLinea);
		linea.setProducto(productos.get(codigoProducto));
		linea.setCantidad(cantidad);
	}
	
	public void borrarLineaOrden(int numeroOrden, int numeroLinea) {
		ordenes.get(numeroOrden).borrarLinea(numeroLinea);
	}
	
	public void borrarOrden(int numeroOrden) {
		Orden orden = ordenes.get(numeroOrden);
		orden.getCliente().borrarOrden(orden);
		ordenes.remove(numeroOrden);
	}
	
}
