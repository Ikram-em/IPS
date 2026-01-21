package giis.demo.ui.jardineria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import giis.demo.ui.employee.schedule.PnSchedule;

public class PlanificarJardineroView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel pnSuperior;
	private JPanel pnAsignarTarea;
	private JPanel panel_3;
	private JPanel pnTitulo;
	private PnSchedule pnHorario;
	private JLabel lbTitulo;
	private JPanel pnLbFecha;
	private JPanel pnDate;
	private JPanel pnLbHoraInicio;
	private JPanel pnHoraInicio;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel pnLbHoraFin;
	private JPanel PnCbHoraFin;
	private JPanel pnInstalación;
	private JPanel pnInstalacion;
	private JPanel panel_12;
	private JPanel panel_13;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblNewLabel;
	private JDateChooser date;
	private JLabel lbHoraInicio;
	private JComboBox<String> cbHoraInicio;
	private JLabel lbHoraFin;
	private JComboBox<String> cbHoraFin;
	private JLabel lbInstalación;
	private JComboBox<String> cbInstalacion;

	/**
	 * Create the frame.
	 */
	public PlanificarJardineroView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 717, 598);
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
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPnSuperior(), BorderLayout.NORTH);
			panel.add(getPnAsignarTarea(), BorderLayout.CENTER);
			panel.add(getPanel_3(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JPanel getPnSuperior() {
		if (pnSuperior == null) {
			pnSuperior = new JPanel();
			pnSuperior.setLayout(new BorderLayout(0, 0));
			pnSuperior.add(getPnTitulo(), BorderLayout.NORTH);
			pnSuperior.add(getPnHorario(), BorderLayout.SOUTH);
		}
		return pnSuperior;
	}

	private JPanel getPnAsignarTarea() {
		if (pnAsignarTarea == null) {
			pnAsignarTarea = new JPanel();
			pnAsignarTarea.setBackground(Color.WHITE);
			pnAsignarTarea.setLayout(new GridLayout(6, 2, 0, 0));
			pnAsignarTarea.add(getPnLbFecha());
			pnAsignarTarea.add(getPanel_2_1());
			pnAsignarTarea.add(getPnLbHoraInicio());
			pnAsignarTarea.add(getPnHoraInicio());
			pnAsignarTarea.add(getPanel_6());
			pnAsignarTarea.add(getPanel_7());
			pnAsignarTarea.add(getPnLbHoraFin());
			pnAsignarTarea.add(getPnCbHoraFin());
			pnAsignarTarea.add(getPnInstalación());
			pnAsignarTarea.add(getPnInstalacion());
			pnAsignarTarea.add(getPanel_12());
			pnAsignarTarea.add(getPanel_13());
		}
		return pnAsignarTarea;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.add(getBtnGuardar());
			panel_3.add(getBtnCancelar());
		}
		return panel_3;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.add(getLbTitulo());
		}
		return pnTitulo;
	}

	public PnSchedule getPnHorario() {
		if (pnHorario == null) {
			pnHorario = new PnSchedule();
			pnHorario.setBackground(Color.WHITE);

		}
		return pnHorario;
	}

	public JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Tareas de Jardinería: nombre");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}

	private JPanel getPnLbFecha() {
		if (pnLbFecha == null) {
			pnLbFecha = new JPanel();
			pnLbFecha.setBackground(Color.WHITE);
		}
		return pnLbFecha;
	}

	private JPanel getPanel_2_1() {
		if (pnDate == null) {
			pnDate = new JPanel();
			pnDate.setBackground(Color.WHITE);
		}
		return pnDate;
	}

	public JDateChooser getDate() {
		if (date == null) {
			date = new JDateChooser();
			date.setPreferredSize(new Dimension(150, 20));
		}
		return date;
	}

	private JPanel getPnLbHoraInicio() {
		if (pnLbHoraInicio == null) {
			pnLbHoraInicio = new JPanel();
			pnLbHoraInicio.setBackground(Color.WHITE);
			pnLbHoraInicio.add(getLblNewLabel());
		}
		return pnLbHoraInicio;
	}

	private JPanel getPnHoraInicio() {
		if (pnHoraInicio == null) {
			pnHoraInicio = new JPanel();
			pnHoraInicio.setBackground(Color.WHITE);
			pnHoraInicio.add(getDate());
		}
		return pnHoraInicio;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.add(getLbHoraInicio());
		}
		return panel_6;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBackground(Color.WHITE);
			panel_7.add(getCbHoraInicio());
		}
		return panel_7;
	}

	private JPanel getPnLbHoraFin() {
		if (pnLbHoraFin == null) {
			pnLbHoraFin = new JPanel();
			pnLbHoraFin.setBackground(Color.WHITE);
			pnLbHoraFin.add(getLbHoraFin());
		}
		return pnLbHoraFin;
	}

	private JPanel getPnCbHoraFin() {
		if (PnCbHoraFin == null) {
			PnCbHoraFin = new JPanel();
			PnCbHoraFin.setBackground(Color.WHITE);
			PnCbHoraFin.add(getCbHoraFin());
		}
		return PnCbHoraFin;
	}

	private JPanel getPnInstalación() {
		if (pnInstalación == null) {
			pnInstalación = new JPanel();
			pnInstalación.setBackground(Color.WHITE);
			pnInstalación.add(getLbInstalación());
		}
		return pnInstalación;
	}

	private JPanel getPnInstalacion() {
		if (pnInstalacion == null) {
			pnInstalacion = new JPanel();
			pnInstalacion.setBackground(Color.WHITE);
			pnInstalacion.add(getCbInstalacion());
		}
		return pnInstalacion;
	}

	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setBackground(Color.WHITE);
		}
		return panel_12;
	}

	private JPanel getPanel_13() {
		if (panel_13 == null) {
			panel_13 = new JPanel();
			panel_13.setBackground(Color.WHITE);
		}
		return panel_13;
	}

	public JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.setBackground(new Color(176, 196, 222));
			btnGuardar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnGuardar;
	}

	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(new Color(176, 196, 222));
			btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnCancelar;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Selecciona una fecha:");
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lblNewLabel;
	}

	private JLabel getLbHoraInicio() {
		if (lbHoraInicio == null) {
			lbHoraInicio = new JLabel("Hora de inicio:");
			lbHoraInicio.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbHoraInicio;
	}

	public JComboBox<String> getCbHoraInicio() {
		if (cbHoraInicio == null) {
			cbHoraInicio = new JComboBox<String>();
			cbHoraInicio.setPreferredSize(new Dimension(150, 22));
			cbHoraInicio.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbHoraInicio;
	}

	private JLabel getLbHoraFin() {
		if (lbHoraFin == null) {
			lbHoraFin = new JLabel("Hora de fin:");
			lbHoraFin.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbHoraFin;
	}

	public JComboBox<String> getCbHoraFin() {
		if (cbHoraFin == null) {
			cbHoraFin = new JComboBox<String>();
			cbHoraFin.setPreferredSize(new Dimension(150, 22));
			cbHoraFin.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbHoraFin;
	}

	private JLabel getLbInstalación() {
		if (lbInstalación == null) {
			lbInstalación = new JLabel("Instalación:");
			lbInstalación.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbInstalación;
	}

	public JComboBox<String> getCbInstalacion() {
		if (cbInstalacion == null) {
			cbInstalacion = new JComboBox<String>();
			cbInstalacion.setPreferredSize(new Dimension(150, 22));
			cbInstalacion.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbInstalacion;
	}
}
