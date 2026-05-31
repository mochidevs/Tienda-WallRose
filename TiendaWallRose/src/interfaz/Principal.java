package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import control.Controladora;
import logica.Cliente;
import logica.Producto;
import logica.Orden;
import java.time.format.DateTimeFormatter;

public class Principal {

	private JFrame frame;
	private JTable tablaClientes;
	private JTable tablaOrdenes;
	private JTable tablaProductos;
	private Controladora control = Controladora.getInstancia();
	private JLabel DatoTotalPendiente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Métodos Clientes
	private void cargarClientes() {
		DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
		model.setRowCount(0);
		List<Cliente> listaClientes = control.obtenerListadoClientes();
		for (Cliente cliente : listaClientes) {
			Object[] fila = new Object[] {cliente.getId(), cliente.getNombre(), cliente.getEmail()};
			model.addRow(fila);
		}
	}
	
	private void verCliente() {
		int numFila = tablaClientes.getSelectedRow();
		if (numFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
			String idCliente = (String)model.getValueAt(numFila, 0);
			DetalleCliente ventanaDetalleCliente = new DetalleCliente(idCliente);
			ventanaDetalleCliente.setVisible(true);
		}
	}
	
	private void agregarCliente() {
		CreaCliente ventanaCrear = new CreaCliente();
		ventanaCrear.setVisible(true);
		cargarClientes();
	}
	
	private void editarCliente() {
		int numFila = tablaClientes.getSelectedRow();
		if (numFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
			String idCliente = (String)model.getValueAt(numFila, 0);
			CreaCliente ventanaCrear = new CreaCliente(idCliente);
			ventanaCrear.setVisible(true);
			cargarClientes();
		}
	}
	
	private void borrarCliente() {
		int numFila = tablaClientes.getSelectedRow();
		if (numFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
			String idCliente = (String)model.getValueAt(numFila, 0);
			String nombreCliente = (String)model.getValueAt(numFila, 1);
			int respuesta = JOptionPane.showConfirmDialog(frame, "Se eliminará la información del cliente " + nombreCliente + " (id=" + idCliente + ") ", "Confirmar", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					control.borrarCliente(idCliente);
					cargarClientes();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error al borrar cliente: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	
	//Métodos Productos
	
	private void cargarProductos() {
		DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
		model.setRowCount(0);
		List<Producto> listaProductos = control.obtenerListadoProductos();
		for (Producto p : listaProductos) {
			Object[] fila = new Object[] {p.getCodigo(), p.getNombre(), p.getExistencias(), p.getUnidad(), p.getPrecio()};
			model.addRow(fila);
		}
	}
	
	private void agregarProducto() {
		DetalleProducto ventana = new DetalleProducto(frame, true, null);
		ventana.setVisible(true);
		cargarProductos();
	}
	
	private void editarProducto() {
		int numFila = tablaProductos.getSelectedRow();
		if (numFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un producto.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
			int codigo = (Integer) model.getValueAt(numFila, 0);
			Producto p = control.obtenerProducto(codigo);
			DetalleProducto ventana = new DetalleProducto(frame, false, p);
			ventana.setVisible(true);
			cargarProductos();
		}
	}
	
	private void borrarProducto() {
		int numFila = tablaProductos.getSelectedRow();
		if (numFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un producto.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
			Integer codigo = (Integer)model.getValueAt(numFila, 0);
			String nombre = (String)model.getValueAt(numFila, 1);
			try {
				if (control.esProductoUtilizado(codigo)) {
					JOptionPane.showMessageDialog(frame, "El producto está siendo utilizado en una orden, no se puede eliminar", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					int respuesta = JOptionPane.showConfirmDialog(frame, "Se eliminará la información del producto " + nombre + " (codigo=" + codigo + ") ", "Confirmar", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						control.borrarProducto(codigo);
						cargarProductos();
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "Error al borrar producto: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

	//Métodos Ordenes
	
	private void cargarOrdenes() {
		DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
		model.setRowCount(0);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Orden o : control.obtenerListadoOrdenes()) {
			String fecha = o.getFecha().format(format);
			Object[] fila = new Object[] {o.getNumero(), fecha,o.getEstado().toString()};
			model.addRow(fila);
		}
		double totalPendiente = control.obtenerMontoTotalPendiente();
		DatoTotalPendiente.setText(String.format("$%.2f", totalPendiente));
	}
	
	private void nuevaOrden() {
		SeleccionarCliente ventana = new SeleccionarCliente(frame);
		ventana.setVisible(true);
		String clienteId = ventana.getClienteSeleccionadoId(frame);
		
		if (clienteId != null) {
			control.crearOrdenVacia(clienteId);
			cargarOrdenes();
			JOptionPane.showMessageDialog(frame, "Orden creada");			
		}
	}
	
	private void detalleOrden() {
		int numFila = tablaOrdenes.getSelectedRow();
		if (numFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar una orden.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
			int numeroOrden = (Integer) model.getValueAt(numFila, 0);
			DetalleOrden ventana = new DetalleOrden(frame, numeroOrden);
			ventana.setVisible(true);
			cargarOrdenes();
		}
	}
	
	private void borrarOrden() throws Exception {
		int numFila = tablaOrdenes.getSelectedRow();
		if (numFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar una orden.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
			int numeroOrden = (Integer) model.getValueAt(numFila, 0);
			
			int respuesta = JOptionPane.showConfirmDialog(frame, "¿Eliminar la orden #" + numeroOrden + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
			
			if (respuesta == JOptionPane.YES_OPTION) {
				control.borrarOrden(numeroOrden);
				cargarOrdenes();
				JOptionPane.showMessageDialog(frame, "Orden eliminada");
			}
		}
	}
	
	public Principal() {
		initialize();
		cargarClientes();
		cargarProductos();
		cargarOrdenes();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Tienda WallRose");
		frame.setBounds(100, 100, 542, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 93, 94, 0);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// Pestaña Clientes
		
		JPanel panelClientes = new JPanel();
		tabbedPane.addTab("Clientes", null, panelClientes, null);
		panelClientes.setLayout(null);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCliente();
			}
		});
		btnVer.setBounds(440, 38, 71, 23);
		panelClientes.add(btnVer);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCliente();								
			}
		});
		btnAgregar.setBounds(440, 63, 71, 23);
		panelClientes.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setBounds(440, 89, 71, 23);
		panelClientes.add(btnEditar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCliente();
			}
		});
		btnBorrar.setBounds(440, 114, 71, 23);
		panelClientes.add(btnBorrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 420, 215);
		panelClientes.add(scrollPane);
		
		tablaClientes = new JTable();
		scrollPane.setViewportView(tablaClientes);
		tablaClientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Email"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(140);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(175);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(175);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClientes.setBounds(10, 11, 89, 14);
		panelClientes.add(lblClientes);
		
		
		// Pestaña Ordenes
		
		JPanel panelOrdenes = new JPanel();
		tabbedPane.addTab("Órdenes", null, panelOrdenes, null);
		panelOrdenes.setLayout(null);
		
		JLabel lblOrdenes = new JLabel("Órdenes de Compra");
		lblOrdenes.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrdenes.setBounds(10, 11, 171, 14);
		panelOrdenes.add(lblOrdenes);
		
		JButton btnNueva = new JButton("Nueva");
		btnNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaOrden();
			}
		});
		btnNueva.setBounds(428, 71, 71, 23);
		panelOrdenes.add(btnNueva);
		
		JButton btnDetalle = new JButton("Detalle");
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detalleOrden();
			}
		});
		btnDetalle.setBounds(428, 105, 71, 23);
		panelOrdenes.add(btnDetalle);
		
		JButton btnBorrarOrden = new JButton("Borrar");
		btnBorrarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					borrarOrden();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnBorrarOrden.setBounds(428, 139, 71, 23);
		panelOrdenes.add(btnBorrarOrden);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 401, 211);
		panelOrdenes.add(scrollPane_1);
		
		tablaOrdenes = new JTable();
		tablaOrdenes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00FAmero", "Fecha", "Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaOrdenes.getColumnModel().getColumn(0).setResizable(false);
		scrollPane_1.setViewportView(tablaOrdenes);
		
		JLabel lblTotal = new JLabel("Total pendiente : ");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal.setBounds(20, 250, 126, 23);
		panelOrdenes.add(lblTotal);
		
		JLabel DatoTotalPendiente = new JLabel("");
		DatoTotalPendiente.setBounds(144, 256, 159, 14);
		panelOrdenes.add(DatoTotalPendiente);
		
		// Pestaña Productos
		
		JPanel panelProductos = new JPanel();
		tabbedPane.addTab("Productos", null, panelProductos, null);
		panelProductos.setLayout(null);
		
		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductos.setBounds(10, 11, 82, 14);
		panelProductos.add(lblProductos);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 36, 396, 237);
		panelProductos.add(scrollPane_2);
		
		tablaProductos = new JTable();
		tablaProductos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Existencias", "Unidad", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaProductos.getColumnModel().getColumn(0).setResizable(false);
		tablaProductos.getColumnModel().getColumn(1).setResizable(false);
		tablaProductos.getColumnModel().getColumn(2).setResizable(false);
		tablaProductos.getColumnModel().getColumn(3).setResizable(false);
		tablaProductos.getColumnModel().getColumn(4).setResizable(false);
		scrollPane_2.setViewportView(tablaProductos);
		
		JButton btnAgregarProd = new JButton("Agregar");
		btnAgregarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		btnAgregarProd.setBounds(422, 87, 89, 23);
		panelProductos.add(btnAgregarProd);
		
		JButton btnEditarProd = new JButton("Editar");
		btnEditarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarProducto();
			}
		});
		btnEditarProd.setBounds(422, 113, 89, 23);
		panelProductos.add(btnEditarProd);
		
		JButton btnBorrarProd = new JButton("Borrar");
		btnBorrarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarProducto();
			}
		});
		btnBorrarProd.setBounds(422, 140, 89, 23);
		panelProductos.add(btnBorrarProd);
	}
}
