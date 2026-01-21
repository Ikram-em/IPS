package giis.demo.ui.logger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoggerView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnSalir;
	private JLabel lbTitulo;
	private JPanel panel_4;
	private JScrollPane scrollPane;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel;
	private JRadioButton rdBtFecha;
	private JRadioButton rdBtUsuario;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnContenedorLogs;


	/**
	 * Create the frame.
	 */
	public LoggerView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 573, 543);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_1(), BorderLayout.NORTH);
			panel.add(getPanel_2(), BorderLayout.CENTER);
			panel.add(getPanel_3(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.add(getLbTitulo());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getPanel_4(), BorderLayout.NORTH);
			panel_2.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel_2;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.add(getBtnSalir());
		}
		return panel_3;
	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
			btnSalir.setBackground(new Color(176, 196, 222));
			btnSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnSalir;
	}

	private void cerrar() {
		this.dispose();
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Log del sistema");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new GridLayout(0, 2, 0, 0));
			panel_4.add(getPanel_5());
			panel_4.add(getPanel_6());
		}
		return panel_4;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getPnContenedorLogs());
		}
		return scrollPane;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.add(getLblNewLabel());
		}
		return panel_5;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.add(getRdBtFecha());
			panel_6.add(getRdBtUsuario());
		}
		return panel_6;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Ordenar por:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lblNewLabel;
	}

	public JRadioButton getRdBtFecha() {
		if (rdBtFecha == null) {
			rdBtFecha = new JRadioButton("Fecha");
			rdBtFecha.setSelected(true);
			buttonGroup.add(rdBtFecha);
			rdBtFecha.setFont(new Font("Calibri", Font.PLAIN, 15));
			rdBtFecha.setBackground(Color.WHITE);
		}
		return rdBtFecha;
	}

	public JRadioButton getRdBtUsuario() {
		if (rdBtUsuario == null) {
			rdBtUsuario = new JRadioButton("Usuario");
			buttonGroup.add(rdBtUsuario);
			rdBtUsuario.setFont(new Font("Calibri", Font.PLAIN, 15));
			rdBtUsuario.setBackground(Color.WHITE);
		}
		return rdBtUsuario;
	}

	public JPanel getPnContenedorLogs() {
		if (pnContenedorLogs == null) {
			pnContenedorLogs = new JPanel();
			pnContenedorLogs.setLayout(new BoxLayout(pnContenedorLogs, BoxLayout.Y_AXIS));
		}
		return pnContenedorLogs;
	}
}
