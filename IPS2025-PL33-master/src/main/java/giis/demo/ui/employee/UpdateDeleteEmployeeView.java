package giis.demo.ui.employee;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class UpdateDeleteEmployeeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnPrincipal;
	private JPanel pnTitulo;
	private JPanel pnContenedor;
	private JPanel pnSalir;
	private JButton btSalir;
	private JPanel pnBlanco1;
	private JPanel pnBlanco2;
	private JScrollPane sPnListaEmpleados;
	private JPanel pnEmpleados;
	private JPanel pnModificarEmpleado;
	private JPanel pnContenidoModificar;
	private JPanel pnTituloModificar;
	private JPanel pnSalirModificar;
	private JLabel lbTituloModificar;
	private JButton btnGuardarCambios;
	private JButton btnCancelarCambios;
	private JPanel pnBlanco4;
	private JPanel pnBlanco3;
	private JPanel pnDatosModificables;
	private JPanel pnContenedorTitulo;
	private JLabel lbTitulo;
	private JPanel pnFiltro;
	private JTextField txFiltro;
	private JButton btnFiltrar;
	private JButton EliminarFiltro;


	/**
	 * Create the frame.
	 */
	public UpdateDeleteEmployeeView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 794, 476);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnPrincipal(), "pnPrincipal");
		contentPane.add(getPnModificarEmpleado(), "pnModificar");

	}
	
	public CardLayout getCardLayout() {
	    return (CardLayout) contentPane.getLayout();
	}
	
	public JPanel getMainPanel() {
	    return contentPane;
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.WHITE);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getPnTitulo(), BorderLayout.NORTH);
			pnPrincipal.add(getPnContenedor(), BorderLayout.CENTER);
			pnPrincipal.add(getPnSalir(), BorderLayout.SOUTH);
			pnPrincipal.add(getPanel_1(), BorderLayout.WEST);
			pnPrincipal.add(getPanel_1_1(), BorderLayout.EAST);
		}
		return pnPrincipal;
	}
	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.setLayout(new GridLayout(2, 1, 0, 0));
			pnTitulo.add(getPnContenedorTitulo());
			pnTitulo.add(getPnFiltro());
		}
		return pnTitulo;
	}
	private JPanel getPnContenedor() {
		if (pnContenedor == null) {
			pnContenedor = new JPanel();
			pnContenedor.setBackground(Color.WHITE);
			pnContenedor.setLayout(new BorderLayout(0, 0));
			pnContenedor.add(getSPnListaEmpleados());
		}
		return pnContenedor;
	}
	private JPanel getPnSalir() {
		if (pnSalir == null) {
			pnSalir = new JPanel();
			pnSalir.setBackground(Color.WHITE);
			pnSalir.add(getBtSalir());
		}
		return pnSalir;
	}
	public JButton getBtSalir() {
		if (btSalir == null) {
			btSalir = new JButton("Volver Atrás");
			btSalir.setBackground(new Color(176, 196, 222));
			btSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btSalir;
	}
	private JPanel getPanel_1() {
		if (pnBlanco1 == null) {
			pnBlanco1 = new JPanel();
			pnBlanco1.setBackground(Color.WHITE);
		}
		return pnBlanco1;
	}
	private JPanel getPanel_1_1() {
		if (pnBlanco2 == null) {
			pnBlanco2 = new JPanel();
			pnBlanco2.setBackground(Color.WHITE);
		}
		return pnBlanco2;
	}
	private JScrollPane getSPnListaEmpleados() {
		if (sPnListaEmpleados == null) {
			sPnListaEmpleados = new JScrollPane();
			sPnListaEmpleados.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sPnListaEmpleados.setViewportView(getPnEmpleados());
		}
		return sPnListaEmpleados;
	}
	public JPanel getPnEmpleados() {
		if (pnEmpleados == null) {
			pnEmpleados = new JPanel();
			pnEmpleados.setBackground(Color.WHITE);
			pnEmpleados.setLayout(new BoxLayout(pnEmpleados, BoxLayout.Y_AXIS));
		}
		return pnEmpleados;
	}
	private JPanel getPnModificarEmpleado() {
		if (pnModificarEmpleado == null) {
			pnModificarEmpleado = new JPanel();
			pnModificarEmpleado.setBackground(Color.WHITE);
			pnModificarEmpleado.setLayout(new BorderLayout(0, 0));
			pnModificarEmpleado.add(getPnContenidoModificar(), BorderLayout.CENTER);
			pnModificarEmpleado.add(getPanel_1_2(), BorderLayout.NORTH);
			pnModificarEmpleado.add(getPnSalirModificar(), BorderLayout.SOUTH);
		}
		return pnModificarEmpleado;
	}
	private JPanel getPnContenidoModificar() {
		if (pnContenidoModificar == null) {
			pnContenidoModificar = new JPanel();
			pnContenidoModificar.setBackground(Color.WHITE);
			pnContenidoModificar.setLayout(new BorderLayout(0, 0));
			pnContenidoModificar.add(getPanel_2(), BorderLayout.WEST);
			pnContenidoModificar.add(getPanel_1_3(), BorderLayout.EAST);
			pnContenidoModificar.add(getPnDatosModificables(), BorderLayout.CENTER);
		}
		return pnContenidoModificar;
	}
	private JPanel getPanel_1_2() {
		if (pnTituloModificar == null) {
			pnTituloModificar = new JPanel();
			pnTituloModificar.setBackground(Color.WHITE);
			pnTituloModificar.add(getLbTituloModificar());
		}
		return pnTituloModificar;
	}
	private JPanel getPnSalirModificar() {
		if (pnSalirModificar == null) {
			pnSalirModificar = new JPanel();
			pnSalirModificar.setBackground(Color.WHITE);
			pnSalirModificar.add(getBtnGuardarCambios());
			pnSalirModificar.add(getBtnCancelarCambios());
		}
		return pnSalirModificar;
	}
	private JLabel getLbTituloModificar() {
		if (lbTituloModificar == null) {
			lbTituloModificar = new JLabel("Modificar Empleado: ");
			lbTituloModificar.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTituloModificar;
	}
	public JButton getBtnGuardarCambios() {
		if (btnGuardarCambios == null) {
			btnGuardarCambios = new JButton("Guardar Cambios");
			btnGuardarCambios.setBackground(new Color(176, 196, 222));
			btnGuardarCambios.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnGuardarCambios;
	}
	public JButton getBtnCancelarCambios() {
		if (btnCancelarCambios == null) {
			btnCancelarCambios = new JButton("Cancelar");
			btnCancelarCambios.setForeground(Color.WHITE);
			btnCancelarCambios.setBackground(new Color(128, 0, 0));
			btnCancelarCambios.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnCancelarCambios;
	}
	private JPanel getPanel_2() {
		if (pnBlanco4 == null) {
			pnBlanco4 = new JPanel();
			pnBlanco4.setBackground(Color.WHITE);
		}
		return pnBlanco4;
	}
	private JPanel getPanel_1_3() {
		if (pnBlanco3 == null) {
			pnBlanco3 = new JPanel();
			pnBlanco3.setBackground(Color.WHITE);
		}
		return pnBlanco3;
	}
	public JPanel getPnDatosModificables() {
		if (pnDatosModificables == null) {
			pnDatosModificables = new JPanel();
			pnDatosModificables.setBackground(Color.WHITE);
		}
		return pnDatosModificables;
	}
	private JPanel getPnContenedorTitulo() {
		if (pnContenedorTitulo == null) {
			pnContenedorTitulo = new JPanel();
			pnContenedorTitulo.setBackground(Color.WHITE);
			pnContenedorTitulo.add(getLbTitulo_1());
		}
		return pnContenedorTitulo;
	}
	private JLabel getLbTitulo_1() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Lista de Empleados");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}
	private JPanel getPnFiltro() {
		if (pnFiltro == null) {
			pnFiltro = new JPanel();
			pnFiltro.setBackground(Color.WHITE);
			pnFiltro.add(getTxFiltro());
			pnFiltro.add(getBtnFiltrar());
			pnFiltro.add(getBtnEliminarFiltro());
		}
		return pnFiltro;
	}
	public JTextField getTxFiltro() {
		if (txFiltro == null) {
			txFiltro = new JTextField();
			txFiltro.setToolTipText("Nombre o DNI a buscar");
			txFiltro.setColumns(10);
		}
		return txFiltro;
	}
	public JButton getBtnFiltrar() {
		if (btnFiltrar == null) {
			btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBackground(new Color(176, 196, 222));
			btnFiltrar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnFiltrar;
	}
	public JButton getBtnEliminarFiltro() {
		if (EliminarFiltro == null) {
			EliminarFiltro = new JButton("Eliminar Filtro");
			EliminarFiltro.setBackground(new Color(176, 196, 222));
			EliminarFiltro.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return EliminarFiltro;
	}
}
