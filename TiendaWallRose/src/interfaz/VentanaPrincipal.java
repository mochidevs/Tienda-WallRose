package interfaz;

import java.awt.*;

import javax.swing.*;


public class VentanaPrincipal extends JFrame {

	private JTabbedPane tabbedPane;
	private PanelClientes panelClientes;
	private PanelProductos panelProductos;
	private PanelOrdenes panelOrdenes;
	
	public VentanaPrincipal() {
		inicializarPartes();
		configurarVentana();
		
	}

	
	private void inicializarPartes() {
		// Titulo
		
		JLabel lblTitulo = new JLabel("Tienda WallRose", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(33, 37, 41));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        
        // Panel de pestañas
        panelClientes  = new PanelClientes();
        panelProductos = new PanelProductos();
        panelOrdenes   = new PanelOrdenes();
        
        // Panel
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabbedPane.addTab("Clientes",  null, panelClientes,  "Gestión de clientes");
        tabbedPane.addTab("Productos", null, panelProductos, "Gestión de productos");
        tabbedPane.addTab("Órdenes",   null, panelOrdenes,   "Gestión de órdenes");
        
        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(lblTitulo,  BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
	}
	
	private void configurarVentana() {
		setTitle("Tienda WallRose");
        setSize(950, 650);
        setLocationRelativeTo(null);          // lo centra en la pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	
	// Entrada
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		SwingUtilities.invokeLater(VentanaPrincipal::new);
	}

}
