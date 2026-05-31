package interfaz;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetalleCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable tablaOrdenesCliente;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalleCliente dialog = new DetalleCliente();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public DetalleCliente() {
		setBounds(100, 100, 536, 413);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 530, 374);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(20, 11, 70, 20);
		panel.add(lblCliente);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDetalleID = new JLabel("ID :");
		lblDetalleID.setBounds(20, 40, 23, 17);
		panel.add(lblDetalleID);
		lblDetalleID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel DatoID = new JLabel("");
		DatoID.setBounds(53, 42, 281, 14);
		panel.add(DatoID);
		
		JLabel lblNombreDetalle = new JLabel("Nombre:");
		lblNombreDetalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDetalle.setBounds(20, 68, 60, 17);
		panel.add(lblNombreDetalle);
		
		JLabel DatoNombre = new JLabel("");
		DatoNombre.setBounds(78, 71, 256, 14);
		panel.add(DatoNombre);
		
		JLabel lblEmailDetalle = new JLabel("Email:");
		lblEmailDetalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailDetalle.setBounds(20, 96, 38, 17);
		panel.add(lblEmailDetalle);
		
		JLabel DatoEmail = new JLabel("");
		DatoEmail.setBounds(63, 99, 271, 14);
		panel.add(DatoEmail);
		
		JLabel lblLista = new JLabel("Lista de órdenes");
		lblLista.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLista.setBounds(20, 124, 126, 20);
		panel.add(lblLista);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 144, 353, 190);
		panel.add(scrollPane);
		
		tablaOrdenesCliente = new JTable();
		tablaOrdenesCliente.setModel(new DefaultTableModel(
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
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaOrdenesCliente.getColumnModel().getColumn(0).setResizable(false);
		tablaOrdenesCliente.getColumnModel().getColumn(1).setResizable(false);
		tablaOrdenesCliente.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(tablaOrdenesCliente);
		
		JLabel lblTotal = new JLabel("Total pendiente : ");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal.setBounds(20, 340, 126, 23);
		panel.add(lblTotal);
		
		JLabel DatoTotalPendiente = new JLabel("");
		DatoTotalPendiente.setBounds(144, 340, 190, 23);
		panel.add(DatoTotalPendiente);
		
		JComboBox<String> filtroEstado = new JComboBox();
		filtroEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = filtroEstado.getSelectedItem().toString();
				
			}
		});
		filtroEstado.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Iniciadas", "Pendientes", "Terminadas"}));
		filtroEstado.setBounds(387, 158, 100, 22);
		panel.add(filtroEstado);

	}
}
