package logica;

public class LineaOrden {
	private double cantidad; 
	private Producto producto;
	
	public LineaOrden(double cantidad, Producto producto) {
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public double calcularCosto() {
		return cantidad * producto.getPrecio();
	}
	
}
