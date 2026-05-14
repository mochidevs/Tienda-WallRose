package logica;

import java.util.Map;
import java.util.TreeMap;

public class Cliente {
	private String id;						//porque no necesita cálculos
	private String nombre;
	private String email;
	private Map<Integer, Orden> ordenes;
	
	
	public Cliente(String id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		ordenes = new TreeMap<Integer, Orden>();
	}
	
	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Map<Integer, Orden> getOrdenes() {
		return ordenes;
	}
	
	public void agregarOrden(Orden orden) {
		ordenes.put(orden.getNumero(), orden);
	}
	
	public void borrarOrden(Orden orden) {
		ordenes.remove(orden.getNumero());
	}
}
