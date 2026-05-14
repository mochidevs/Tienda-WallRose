package logica;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Orden {
	private static double IV;		// % impuesto de venta
	private int numero;
	private LocalDateTime fecha;
	private Cliente cliente;
	private List<LineaOrden> lineas;
	private EstadoOrden estado;
	
	public Orden(int numero, Cliente cliente) {
		IV = 13.0;
		this.numero = numero;
		this.cliente = cliente;
		this.estado = EstadoOrden.INICIADA;
		fecha = LocalDateTime.now();
		lineas = new ArrayList<LineaOrden>();
	}
	
	public int getNumero() {
		return numero;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

	public List<LineaOrden> getLineas() {
		return lineas;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public double calcularMonto() {
		double monto = 0;
		for (LineaOrden linea : lineas) {
			monto += linea.calcularCosto();
		}
		return monto;
	}
	
	public double calcularMontoImpuesto() {
		return calcularMonto() * IV;
	}
	
	public double calcularMontoTotal() {
		return calcularMonto() + calcularMontoImpuesto();
	}
	
	public void agregarLinea(LineaOrden linea) {
		lineas.add(linea);
	}
	
	public void borrarLinea(int numeroLinea) {
		lineas.remove(numeroLinea);
	}
	
}
