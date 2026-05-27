package interfaz;

import control.Controladora;
import logica.Cliente;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelClientes extends JPanel {
	
	// Ref a singleton
	private final Controladora control = Controladora.getInstancia();
	
	// Form del cliente
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtEmail;
	
	// Botones
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JButton btnBorrar;
	private JButton btnLimpiar;
	
	// Tabla
	private JTable tablaClientes;
	private DefaultTableModel modeloTabla;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textId;
	private GridBagConstraints gbc_labelNombre;
	private JTextField textNombre;
	private GridBagConstraints gbc_labelEmail;
	private JTextField textEmail;
	
	//Constructor
	public PanelClientes() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		inicializarPartes();
		configurarEventos();
		cargarTabla();
	}
	
	//			Interfaz
	private void inicializarPartes() {
		add(crearPanelFormulario(), BorderLayout.NORTH);
        add(crearPanelTabla(),      BorderLayout.CENTER);
        add(crearPanelBotones(),    BorderLayout.SOUTH);
        
        
        
        
	}
	
	private JPanel crearPanelFormulario() {
		GridBagLayout gbl_panelForm = new GridBagLayout();
		gbl_panelForm.columnWeights = new double[]{0.0, 1.0};
		JPanel panelForm = new JPanel(gbl_panelForm);
        panelForm.setBorder(new TitledBorder("Datos del Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(5, 5, 5, 5);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.anchor  = GridBagConstraints.WEST;
        
        
        JLabel labelId = new JLabel("ID Cliente:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panelForm.add(labelId, gbc_lblNewLabel);
        
        textId = new JTextField();
        GridBagConstraints gbc_textId = new GridBagConstraints();
        gbc_textId.insets = new Insets(0, 0, 5, 0);
        gbc_textId.fill = GridBagConstraints.HORIZONTAL;
        gbc_textId.gridx = 1;
        gbc_textId.gridy = 0;
        panelForm.add(textId, gbc_textId);
        textId.setColumns(10);
        
        JLabel labelNombre = new JLabel("Nombre:");
        GridBagConstraints gbc_lableNombre;
        gbc_labelNombre = new GridBagConstraints();
        gbc_labelNombre.anchor = GridBagConstraints.EAST;
        gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
        gbc_labelNombre.gridx = 0;
        gbc_labelNombre.gridy = 1;
        panelForm.add(labelNombre, gbc_labelNombre);
        
        textNombre = new JTextField();
        GridBagConstraints gbc_textNombre = new GridBagConstraints();
        gbc_textNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textNombre.gridx = 1;
        gbc_textNombre.gridy = 1;
        panelForm.add(textNombre, gbc_textNombre);
        textNombre.setColumns(10);
        
        JLabel labelEmail = new JLabel("Email:");
        GridBagConstraints gbc_lblNewLabel;
        gbc_labelEmail = new GridBagConstraints();
        gbc_labelEmail.anchor = GridBagConstraints.EAST;
        gbc_labelEmail.insets = new Insets(0, 0, 0, 5);
        gbc_labelEmail.gridx = 0;
        gbc_labelEmail.gridy = 2;
        panelForm.add(labelEmail, gbc_labelEmail);
        
        textEmail = new JTextField();
        GridBagConstraints gbc_textEmail = new GridBagConstraints();
        gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
        gbc_textEmail.gridx = 1;
        gbc_textEmail.gridy = 2;
        panelForm.add(textEmail, gbc_textEmail);
        textEmail.setColumns(10);
        
        return panelForm;
	}
	
	// Panel con los clientes
	
	private JScrollPane crearPanelTabla() {
		
		modeloTabla = new DefaultTableModel (new String[]{"ID", "Nombre", "Email"}, 0) {
			@Override
			public boolean celdaEditable(int row, int col) {	//solo lectura
				return false;
			}
		};
		
		tablaClientes = new JTable(modeloTabla);
		tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.getTableHeader().setReorderingAllowed(false);
        tablaClientes.setRowHeight(24);
        tablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
 
        return new JScrollPane(tablaClientes);
		
	}
	
	private JPanel crearPanelBotones() {
		JPanel panel =  new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		btnAgregar = new JButton("Agregar");
		btnActualizar = new JButton("Actualizar");
		btnBorrar = new JButton("Borrar");
		btnLimpiar = new JButton("Limpiar");
		
		// Color borrar
		btnBorrar.setBackground(new Color(220, 53, 69));
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setOpaque(true);
		btnBorrar.setBorderPainted(false);
		
		panel.add(btnAgregar);
		panel.add(btnActualizar);
		panel.add(btnBorrar);
		panel.add(btnLimpiar);
		
		return panel;
	}
	
	// Eventos
	
	private void configurarEventos() {
		
	}
}
