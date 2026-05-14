package logica;

public class Producto {
	private int codigo;
	private String nombre;
	private double existencias;
	private String unidad;
	private double precio;
	
	public Producto(int codigo, String nombre, double existencias, String unidad, double precio) {
		codigo = 0;
		this.nombre = nombre;
		existencias = 0.0;
		this.unidad = unidad;
		precio = 0.0;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getExistencias() {
		return existencias;
	}

	public void setExistencias(double existencias) {
		this.existencias = existencias;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	
	
}
