package interfaz;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import control.Controladora;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class CreaCliente extends JDialog {
	
	private Controladora control = Controladora.getInstancia();
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaCliente dialog = new CreaCliente();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CreaCliente() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 381, 244);
		getContentPane().setLayout(null);
		
		JLabel lblCliente = new JLabel("Crear/Editar Cliente");
		lblCliente.setBounds(10, 11, 167, 14);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblCliente);
		
		JLabel lblID = new JLabel("ID :");
		lblID.setBounds(10, 47, 28, 14);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblID);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(10, 82, 63, 14);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(10, 117, 47, 14);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblEmail);
		
		txtId = new JTextField();
		txtId.setBounds(35, 46, 307, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(69, 81, 273, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(52, 116, 290, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarCliente();
			}
		});
		btnGuardar.setBounds(254, 158, 89, 23);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(btnGuardar);

	}

	private void cargarDatosCliente(String idCliente) {
		try {
			var cliente = control.obtenerCliente(idCliente);
			txtId.setText(cliente.getId());
            txtNombre.setText(cliente.getNombre());
            txtEmail.setText(cliente.getEmail());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar cliente: " + e.getMessage());
		}
	}
	
	private void guardarCliente() {
		try {
			String id = txtId.getText().trim();
			String nombre = txtNombre.getText().trim();
			String email = txtEmail.getText().trim();
			
			if (id.isEmpty() || nombre.isEmpty() || email.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (esNuevo) {
                control.crearCliente(id, nombre, email);
            } else {
            	JOptionPane.showMessageDialog(this, "Error: ID ya fue usado.");
            }
			
		} catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
	}
	
}
