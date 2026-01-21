package giis.demo.ui.jardineria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class JardineriaMainMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnPrincipal;
	private JPanel pnTitulo;
	private JPanel pnContenido;
	private JPanel pnSalir;
	private JLabel lbTitulo;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	private JPanel pnJardineros;


	/**
	 * Create the frame.
	 */
	public JardineriaMainMenuView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 559);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnPrincipal());

	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.WHITE);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getPnTitulo(), BorderLayout.NORTH);
			pnPrincipal.add(getPnContenido(), BorderLayout.CENTER);
			pnPrincipal.add(getPnSalir(), BorderLayout.SOUTH);
		}
		return pnPrincipal;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.add(getLbTitulo());
		}
		return pnTitulo;
	}

	private JPanel getPnContenido() {
		if (pnContenido == null) {
			pnContenido = new JPanel();
			pnContenido.setBorder(
					new TitledBorder(null, "Jardineros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnContenido.setBackground(Color.WHITE);
			pnContenido.setLayout(new BorderLayout(0, 0));
			pnContenido.add(getScrollPane(), BorderLayout.CENTER);
		}
		return pnContenido;
	}

	private JPanel getPnSalir() {
		if (pnSalir == null) {
			pnSalir = new JPanel();
			pnSalir.setBackground(Color.WHITE);
			pnSalir.add(getBtnSalir());
		}
		return pnSalir;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Labores de jardinería");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}

	public JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnSalir.setBackground(new Color(176, 196, 222));
		}
		return btnSalir;
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getPnJardineros());
		}
		return scrollPane;
	}

	public JPanel getPnJardineros() {
		if (pnJardineros == null) {
			pnJardineros = new JPanel();
			pnJardineros.setBackground(Color.WHITE);
			pnJardineros.setLayout(new BoxLayout(pnJardineros, BoxLayout.Y_AXIS));
		}
		return pnJardineros;
	}
}
