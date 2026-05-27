package interfaz;

import control.Controladora;
import logica.Producto;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import control.Controladora;

public class PanelProductos extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Controladora ctrl = Controladora.getInstancia();
	
	private JTextField txtNombre;
    private JTextField txtExistencias;
    private JTextField txtUnidad;
    private JTextField txtPrecio;
    
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnBorrar;
    private JButton btnLimpiar;
    
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    
    private int codigoSeleccionado = -1;
	
	/**
	 * Create the panel.
	 */
	public PanelProductos() {
		setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inicializarComponentes();
        configurarEventos();
        cargarTabla();
	}

}
