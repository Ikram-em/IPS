package giis.demo.ui.employee.schedule;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import giis.demo.tkrun.employee.EmployeeViewDTO;

public class ScheduleMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnConsultarHorarios;
	private JPanel pnTituloConsultarHorarios;
	private JPanel pnSalir;
	private JLabel lbConsultarHorarios;
	private JScrollPane sPnListaEmpleados;
	private JPanel pnListaEmpleados;
	private JPanel pnWeeklySchedule;
	private JPanel pnSpecificSchedule;
	private JPanel panel;
	private JPanel pnShowSchedule;
	private JPanel pnTituloHorarioSemanal;
	private JPanel pnGestionarHorarioSemanal;
	private JPanel pnSalir1;
	private JLabel lbModificarHorarioSemanal;
	private JPanel pnTituloHorasSemanales;
	private PnSchedule pnContenedorSemanal;
	private PnSpecificSchedule pnContenedorSemanal1;
	private JLabel lbTituloHorarioSemanal;
	private JButton btnGuardarHorarioSemanal;
	private JButton btnCancelar;
	private JButton btnVolverAtras;
	private JPanel pnTituloHorarioEspecifico;
	private JPanel pnAgregarHorarioEspecifico;
	private JPanel pnSalir2;
	private JLabel lbTituloHorarioEspecifico;
	private JPanel pnContenedorFecha;
	private JDateChooser dateChooser;
	private JPanel pnExplicacionFecha;
	private JLabel lbExplicacionFecha;
	private JButton btnGuardarHorarioEspecifico;
	private JButton btnVolverAtrasEspecifico;
	private JPanel pnTituloVerHorarioCompleto;
	private JLabel lbTituloHorarioCompleto;
	private JPanel pnContenedorHorarioCompleto;
	private JPanel pnTituloHorarioCompleto;
	private PnSchedule pnHorarioCompleto;
	private JLabel lbExplicacionCompleto;
	private JDateChooser calendarioCompleto;
	private JPanel pnBtnAtras;
	private JButton btnAtrasCalendarioFinal;


	/**
	 * Create the frame.
	 */
	public ScheduleMenuView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 775, 443);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnConsultarHorarios(), "pnMainMenu");
		contentPane.add(getPnWeeklySchedule(), "pnWeeklySchedule");
		contentPane.add(getPnSpecificSchedule(), "pnSpecificSchedule");
		contentPane.add(getPnShowSchedule(), "pnShowSchedule");

	}
	
	public CardLayout getCardLayout() {
	    return (CardLayout) contentPane.getLayout();
	}
	
	public JPanel getMainPanel() {
	    return contentPane;
	}

	private JPanel getPnConsultarHorarios() {
		if (pnConsultarHorarios == null) {
			pnConsultarHorarios = new JPanel();
			pnConsultarHorarios.setLayout(new BorderLayout(0, 0));
			pnConsultarHorarios.add(getPnTituloConsultarHorarios(), BorderLayout.NORTH);
			pnConsultarHorarios.add(getPnSalir(), BorderLayout.SOUTH);
			pnConsultarHorarios.add(getSPnListaEmpleados(), BorderLayout.CENTER);
			pnConsultarHorarios.add(getPanel(), BorderLayout.WEST);
		}
		return pnConsultarHorarios;
	}
	private JPanel getPnTituloConsultarHorarios() {
		if (pnTituloConsultarHorarios == null) {
			pnTituloConsultarHorarios = new JPanel();
			pnTituloConsultarHorarios.setBackground(Color.WHITE);
			pnTituloConsultarHorarios.add(getLbConsultarHorarios());
		}
		return pnTituloConsultarHorarios;
	}
	private JPanel getPnSalir() {
		if (pnSalir == null) {
			pnSalir = new JPanel();
			pnSalir.setBackground(new Color(255, 255, 255));
			pnSalir.add(getBtnCancelar());
		}
		return pnSalir;
	}
	private JLabel getLbConsultarHorarios() {
		if (lbConsultarHorarios == null) {
			lbConsultarHorarios = new JLabel("Consultar Horarios:");
			lbConsultarHorarios.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbConsultarHorarios;
	}
	private JScrollPane getSPnListaEmpleados() {
		if (sPnListaEmpleados == null) {
			sPnListaEmpleados = new JScrollPane(getPnListaEmpleados());
			sPnListaEmpleados.setBorder(new TitledBorder(null, "Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
			sPnListaEmpleados.setBackground(Color.WHITE);
			sPnListaEmpleados.setViewportView(getPnListaEmpleados());
		}
		return sPnListaEmpleados;
	}
	public JPanel getPnListaEmpleados() {
		if (pnListaEmpleados == null) {
			pnListaEmpleados = new JPanel();
			pnListaEmpleados.setBackground(Color.WHITE);
			pnListaEmpleados.setLayout(new BoxLayout(pnListaEmpleados, BoxLayout.Y_AXIS));
		}
		return pnListaEmpleados;
	}
	private JPanel getPnWeeklySchedule() {
		if (pnWeeklySchedule == null) {
			pnWeeklySchedule = new JPanel();
			pnWeeklySchedule.setBackground(Color.WHITE);
			pnWeeklySchedule.setLayout(new BorderLayout(0, 0));
			pnWeeklySchedule.add(getPnTituloHorarioSemanal(), BorderLayout.NORTH);
			pnWeeklySchedule.add(getPnGestionarHorarioSemanal(), BorderLayout.CENTER);
			pnWeeklySchedule.add(getPnSalir1(), BorderLayout.SOUTH);
		}
		return pnWeeklySchedule;
	}
	private JPanel getPnSpecificSchedule() {
		if (pnSpecificSchedule == null) {
			pnSpecificSchedule = new JPanel();
			pnSpecificSchedule.setLayout(new BorderLayout(0, 0));
			pnSpecificSchedule.add(getPnTituloHorarioEspecifico(), BorderLayout.NORTH);
			pnSpecificSchedule.add(getPnAgregarHorarioEspecifico(), BorderLayout.CENTER);
			pnSpecificSchedule.add(getPnSalir2(), BorderLayout.SOUTH);
		}
		return pnSpecificSchedule;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}
	private JPanel getPnShowSchedule() {
		if (pnShowSchedule == null) {
			pnShowSchedule = new JPanel();
			pnShowSchedule.setLayout(new BorderLayout(0, 0));
			pnShowSchedule.add(getPnTituloVerHorarioCompleto(), BorderLayout.NORTH);
			pnShowSchedule.add(getPnContenedorHorarioCompleto(), BorderLayout.CENTER);
			pnShowSchedule.add(getPnBtnAtras(), BorderLayout.SOUTH);
		}
		return pnShowSchedule;
	}
	private JPanel getPnTituloHorarioSemanal() {
		if (pnTituloHorarioSemanal == null) {
			pnTituloHorarioSemanal = new JPanel();
			pnTituloHorarioSemanal.setBackground(Color.WHITE);
			pnTituloHorarioSemanal.add(getLbModificarHorarioSemanal());
		}
		return pnTituloHorarioSemanal;
	}
	private JPanel getPnGestionarHorarioSemanal() {
		if (pnGestionarHorarioSemanal == null) {
			pnGestionarHorarioSemanal = new JPanel();
			pnGestionarHorarioSemanal.setBackground(Color.WHITE);
			pnGestionarHorarioSemanal.setLayout(new BorderLayout(0, 0));
			pnGestionarHorarioSemanal.add(getPnTituloHorasSemanales(), BorderLayout.NORTH);
			pnGestionarHorarioSemanal.add(getPnSemanal(), BorderLayout.CENTER);
		}
		return pnGestionarHorarioSemanal;
	}
	private JPanel getPnSalir1() {
		if (pnSalir1 == null) {
			pnSalir1 = new JPanel();
			pnSalir1.setBackground(Color.WHITE);
			pnSalir1.add(getBtnGuardarHorarioSemanal());
			pnSalir1.add(getBtnVolverAtras());
		}
		return pnSalir1;
	}
	private JLabel getLbModificarHorarioSemanal() {
		if (lbModificarHorarioSemanal == null) {
			lbModificarHorarioSemanal = new JLabel("Añadir nuevo Horario Semanal: ");
			lbModificarHorarioSemanal.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbModificarHorarioSemanal;
	}
	
	public void setNameWeeklySchedule(String name, String apellido) {
		this.lbModificarHorarioSemanal.setText("Añadir nuevo Horario Semanal: " + name + " " + apellido);
	}
	
	private JPanel getPnTituloHorasSemanales() {
		if (pnTituloHorasSemanales == null) {
			pnTituloHorasSemanales = new JPanel();
			pnTituloHorasSemanales.setBackground(Color.WHITE);
			pnTituloHorasSemanales.setLayout(new GridLayout(1, 0, 0, 0));
			pnTituloHorasSemanales.add(getLbTituloHorarioSemanal());
		}
		return pnTituloHorasSemanales;
	}
	public PnSchedule getPnSemanal() {
		if (pnContenedorSemanal == null) {
			pnContenedorSemanal = new PnSchedule();
		}
		return pnContenedorSemanal;
	}
	public PnSpecificSchedule getPnSemanal1() {
		if (pnContenedorSemanal1 == null) {
			pnContenedorSemanal1 = new PnSpecificSchedule();
			pnContenedorSemanal1.setVisible(false);
		}
		return pnContenedorSemanal1;
	}
	private JLabel getLbTituloHorarioSemanal() {
		if (lbTituloHorarioSemanal == null) {
			lbTituloHorarioSemanal = new JLabel("     Introduzca las horas que el empleado debe trabajar cada día");
			lbTituloHorarioSemanal.setBackground(Color.WHITE);
			lbTituloHorarioSemanal.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbTituloHorarioSemanal;
	}

	public JButton getBtnGuardarHorarioSemanal() {
		if (btnGuardarHorarioSemanal == null) {
			btnGuardarHorarioSemanal = new JButton("Guardar");
			btnGuardarHorarioSemanal.setBackground(new Color(176, 196, 222));
			btnGuardarHorarioSemanal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnGuardarHorarioSemanal;
	}
	

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setForeground(new Color(255, 255, 255));
			btnCancelar.setBackground(new Color(128, 0, 0));
			btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnCancelar;
	}
	public JButton getBtnVolverAtras() {
		if (btnVolverAtras == null) {
			btnVolverAtras = new JButton("Volver Atrás");
			btnVolverAtras.setBackground(new Color(176, 196, 222));
			btnVolverAtras.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnVolverAtras;
	}
	private JPanel getPnTituloHorarioEspecifico() {
		if (pnTituloHorarioEspecifico == null) {
			pnTituloHorarioEspecifico = new JPanel();
			pnTituloHorarioEspecifico.setBackground(Color.WHITE);
			pnTituloHorarioEspecifico.add(getLbTituloHorarioEspecifico());
		}
		return pnTituloHorarioEspecifico;
	}
	private JPanel getPnAgregarHorarioEspecifico() {
		if (pnAgregarHorarioEspecifico == null) {
			pnAgregarHorarioEspecifico = new JPanel();
			pnAgregarHorarioEspecifico.setBackground(Color.WHITE);
			pnAgregarHorarioEspecifico.setLayout(new BorderLayout(0, 0));
			pnAgregarHorarioEspecifico.add(getPnExplicacionFecha(), BorderLayout.NORTH);
			pnAgregarHorarioEspecifico.add(getPnSemanal1(), BorderLayout.CENTER);
		}
		return pnAgregarHorarioEspecifico;
	}
	private JPanel getPnSalir2() {
		if (pnSalir2 == null) {
			pnSalir2 = new JPanel();
			pnSalir2.add(getBtnGuardarHorarioEspecifico());
			pnSalir2.add(getBtnVolverAtrasEspecifico());
		}
		return pnSalir2;
	}
	private JLabel getLbTituloHorarioEspecifico() {
		if (lbTituloHorarioEspecifico == null) {
			lbTituloHorarioEspecifico = new JLabel("Titulo");
			lbTituloHorarioEspecifico.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTituloHorarioEspecifico;
	}
	
	public void setTitleSpecificSchedule(EmployeeViewDTO employeeDto) {
		getLbTituloHorarioEspecifico().setText("Horario específico para: " + employeeDto.nombre + " " + employeeDto.apellido);
	}
	
	

	private JPanel getPnContenedorFecha() {
		if (pnContenedorFecha == null) {
			pnContenedorFecha = new JPanel();
			pnContenedorFecha.setBackground(Color.WHITE);
			pnContenedorFecha.setLayout(new BorderLayout(0, 0));
			pnContenedorFecha.add(getDateChooser_1(), BorderLayout.CENTER);
		}
		return pnContenedorFecha;
	}
	public JDateChooser getDateChooser_1() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setPreferredSize(new Dimension(100, 20));
			dateChooser.setSize(new Dimension(222, 0));
		}
		return dateChooser;
	}
	
	private JPanel getPnExplicacionFecha() {
		if (pnExplicacionFecha == null) {
			pnExplicacionFecha = new JPanel();
			pnExplicacionFecha.setBackground(Color.WHITE);
			pnExplicacionFecha.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnExplicacionFecha.add(getLbExplicacionFecha());
			pnExplicacionFecha.add(getPnContenedorFecha());
		}
		return pnExplicacionFecha;
	}
	
	private JLabel getLbExplicacionFecha() {
		if (lbExplicacionFecha == null) {
			lbExplicacionFecha = new JLabel("Al seleccionar la fecha se mostrará la semana completa.  ");
			lbExplicacionFecha.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbExplicacionFecha;
	}
	
	public JButton getBtnGuardarHorarioEspecifico() {
		if (btnGuardarHorarioEspecifico == null) {
			btnGuardarHorarioEspecifico = new JButton("Guardar");
			btnGuardarHorarioEspecifico.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnGuardarHorarioEspecifico.setBackground(new Color(176, 196, 222));
		}
		return btnGuardarHorarioEspecifico;
	}
	public JButton getBtnVolverAtrasEspecifico() {
		if (btnVolverAtrasEspecifico == null) {
			btnVolverAtrasEspecifico = new JButton("Volver Atrás");
			btnVolverAtrasEspecifico.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnVolverAtrasEspecifico.setBackground(new Color(176, 196, 222));
		}
		return btnVolverAtrasEspecifico;
	}
	private JPanel getPnTituloVerHorarioCompleto() {
		if (pnTituloVerHorarioCompleto == null) {
			pnTituloVerHorarioCompleto = new JPanel();
			pnTituloVerHorarioCompleto.setBackground(Color.WHITE);
			pnTituloVerHorarioCompleto.add(getLbTituloHorarioCompleto());
		}
		return pnTituloVerHorarioCompleto;
	}
	
	private JLabel getLbTituloHorarioCompleto() {
		if (lbTituloHorarioCompleto == null) {
			lbTituloHorarioCompleto = new JLabel("Calendario completo de ");
			lbTituloHorarioCompleto.setFont(new Font("Calibri", Font.BOLD, 25));
			lbTituloHorarioCompleto.setBackground(Color.WHITE);
		}
		return lbTituloHorarioCompleto;
	}
	
	public void setTituloCompleto(EmployeeViewDTO employee) {
		getLbTituloHorarioCompleto().setText("Calendario completo de: " + employee.nombre);
	}
	
	private JPanel getPnContenedorHorarioCompleto() {
		if (pnContenedorHorarioCompleto == null) {
			pnContenedorHorarioCompleto = new JPanel();
			pnContenedorHorarioCompleto.setLayout(new BorderLayout(0, 0));
			pnContenedorHorarioCompleto.add(getPnVerHorarioCompleto_1(), BorderLayout.NORTH);
			pnContenedorHorarioCompleto.add(getPnHorarioCompleto(), BorderLayout.CENTER);
		}
		return pnContenedorHorarioCompleto;
	}
	private JPanel getPnVerHorarioCompleto_1() {
		if (pnTituloHorarioCompleto == null) {
			pnTituloHorarioCompleto = new JPanel();
			pnTituloHorarioCompleto.setBackground(Color.WHITE);
			pnTituloHorarioCompleto.setLayout(new GridLayout(0, 2, 0, 0));
			pnTituloHorarioCompleto.add(getLbExplicacionCompleto());
			pnTituloHorarioCompleto.add(getCalendarioCompleto());
			
		}
		return pnTituloHorarioCompleto;
	}
	
	public JDateChooser getCalendarioCompleto() {
		if (calendarioCompleto == null) {
			calendarioCompleto = new JDateChooser();
		}
		return calendarioCompleto;
	}
	
	public PnSchedule getPnHorarioCompleto() {
		if (pnHorarioCompleto == null) {
			pnHorarioCompleto = new PnSchedule();
		}
		return pnHorarioCompleto;
	}
	private JLabel getLbExplicacionCompleto() {
		if (lbExplicacionCompleto == null) {
			lbExplicacionCompleto = new JLabel("Seleccione un día para mostrar su semana:");
			lbExplicacionCompleto.setBackground(Color.WHITE);
			lbExplicacionCompleto.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbExplicacionCompleto;
	}
	private JPanel getPnBtnAtras() {
		if (pnBtnAtras == null) {
			pnBtnAtras = new JPanel();
			pnBtnAtras.setBackground(Color.WHITE);
			pnBtnAtras.add(getBtnAtrasCalendarioFinal());
		}
		return pnBtnAtras;
	}
	public JButton getBtnAtrasCalendarioFinal() {
		if (btnAtrasCalendarioFinal == null) {
			btnAtrasCalendarioFinal = new JButton("Volver Atrás");
			btnAtrasCalendarioFinal.setBackground(new Color(176, 196, 222));
			btnAtrasCalendarioFinal.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAtrasCalendarioFinal;
	}
}
