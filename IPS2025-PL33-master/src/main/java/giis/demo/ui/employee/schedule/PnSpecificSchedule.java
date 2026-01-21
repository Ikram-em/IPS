package giis.demo.ui.employee.schedule;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;


public class PnSpecificSchedule extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel pnContenido;
	private JLabel lbDiaSemana;
	private JLabel lbHoraFin;
	private JLabel lbHoraInicio;
	private JLabel lbVacia;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JSpinner spHoraInicio;
	private JSpinner spHoraFin;

	/**
	 * Create the panel.
	 */
	public PnSpecificSchedule() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPanel(), BorderLayout.CENTER);

	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_4(), BorderLayout.EAST);
			panel.add(getPanel_1(), BorderLayout.NORTH);
			panel.add(getPanel_2(), BorderLayout.WEST);
			panel.add(getPanel_3(), BorderLayout.SOUTH);
			panel.add(getPnContenido(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
		}
		return panel_4;
	}
	private JPanel getPnContenido() {
		if (pnContenido == null) {
			pnContenido = new JPanel();
			pnContenido.setBackground(Color.WHITE);
			pnContenido.setLayout(new GridLayout(3, 3, 0, 0));
			pnContenido.add(getLbVacia());
			pnContenido.add(getLbHoraInicio());
			pnContenido.add(getLbHoraFin());
			pnContenido.add(getLbDiaSemana());
			pnContenido.add(getSpHoraInicio());
			pnContenido.add(getSpHoraFin());
			pnContenido.add(getLblNewLabel());
			pnContenido.add(getLblNewLabel_2());
			pnContenido.add(getLblNewLabel_1());
		}
		return pnContenido;
	}
	public JLabel getLbDiaSemana() {
		if (lbDiaSemana == null) {
			lbDiaSemana = new JLabel("Lunes");
			lbDiaSemana.setHorizontalAlignment(SwingConstants.CENTER);
			lbDiaSemana.setBackground(Color.WHITE);
			lbDiaSemana.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbDiaSemana;
	}
	
	public JSpinner getSpHoraInicio() {
        if (spHoraInicio == null) {
            spHoraInicio = createHourSpinner();
        }
        return spHoraInicio;
    }
	
	public JSpinner getSpHoraFin() {
        if (spHoraFin == null) {
            spHoraFin = createHourSpinner();
        }
        return spHoraFin;
    }
	
	private JLabel getLbHoraFin() {
		if (lbHoraFin == null) {
			lbHoraFin = new JLabel("Hora fin:");
			lbHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
			lbHoraFin.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbHoraFin;
	}
	private JLabel getLbHoraInicio() {
		if (lbHoraInicio == null) {
			lbHoraInicio = new JLabel("Hora de inicio:");
			lbHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);
			lbHoraInicio.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbHoraInicio;
	}
	private JLabel getLbVacia() {
		if (lbVacia == null) {
			lbVacia = new JLabel("");
			lbVacia.setHorizontalAlignment(SwingConstants.CENTER);
			lbVacia.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbVacia;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
		}
		return lblNewLabel_1;
	}
	
	
	
	/**
     * Crea un JSpinner configurado para seleccionar horas con incremento de 30 minutos
     */
    private JSpinner createHourSpinner() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        

        SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.MINUTE);
        JSpinner spinner = new JSpinner(model);
        
        spinner.setValue(calendar.getTime());

        DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);

        spinner.addChangeListener(e -> {
            Date value = (Date) spinner.getValue();
            Calendar c = Calendar.getInstance();
            c.setTime(value);
            int minutes = c.get(Calendar.MINUTE);
            minutes = (minutes / 30) * 30; // redondeo a 0 o 30
            c.set(Calendar.MINUTE, minutes);
            c.set(Calendar.SECOND, 0);
            spinner.setValue(c.getTime());
        });

        return spinner;
    }
}
