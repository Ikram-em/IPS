package giis.demo.ui.teammanagment.entrenamiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import giis.demo.tkrun.instalacion.InstalacionDTO;
import giis.demo.tkrun.teammanagement.entrenamiento.EquipoSimpleDTO;

public class TrainingCreationView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel centerPn;
	private JComboBox<EquipoSimpleDTO> teamSelector;
	private JComboBox<InstalacionDTO> installationSelector;
	private JDateChooser dateChooser;
	private JSpinner horaInicio;
	private JSpinner horaFin;
	private JButton acceptBtn;

	public TrainingCreationView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 451);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(getTitlePanel(), BorderLayout.NORTH);
		contentPane.add(getCenterPn(), BorderLayout.CENTER);
		contentPane.add(getBottomPn(), BorderLayout.SOUTH);
	}
	
	private JPanel getTitlePanel() {
		JPanel titlePn = new JPanel();
		titlePn.setLayout(new FlowLayout(FlowLayout.CENTER));
		titlePn.add(createLabel("Añadir nuevo entrenamiento", Font.BOLD, 18));
		return titlePn;
	}
	
	private JPanel getCenterPn() {
		if (centerPn == null) {
			centerPn = new JPanel();
			centerPn.setLayout(new BoxLayout(centerPn, BoxLayout.Y_AXIS));
			centerPn.setBorder(new EmptyBorder(10, 10, 10, 10));
			
			centerPn.add(getKeyValuePn(createLabel("Equipo:", Font.BOLD, 15), getTeamSelector()));
			
			centerPn.add(getKeyValuePn(createLabel("Instalación:", Font.BOLD, 15), getInstallationSelector()));
			
			centerPn.add(getKeyValuePn(createLabel("Fecha:", Font.BOLD, 15), getDate()));
			
			centerPn.add(getKeyValuePn(createLabel("Hora de inicio:", Font.BOLD, 15), getStartTime()));
			
			centerPn.add(getKeyValuePn(createLabel("Hora de fin:", Font.BOLD, 15), getEndTime()));
		}
		
		return centerPn;
	}
	
	private JPanel getBottomPn() {
		JPanel bottomPn = new JPanel();
		bottomPn.add(getAcceptButton());
		return bottomPn;
	}
	
	private JPanel getKeyValuePn(Component key, Component value) {
		JPanel keyValuePn = new JPanel();
		keyValuePn.setLayout(new FlowLayout(FlowLayout.LEFT));
		key.setPreferredSize(new Dimension(100, 30));
		value.setPreferredSize(new Dimension(300, 30));
		keyValuePn.add(key);
		keyValuePn.add(value);
		return keyValuePn;
	}
	
	public JComboBox<EquipoSimpleDTO> getTeamSelector() {
		if (teamSelector == null) {
			teamSelector = new JComboBox<EquipoSimpleDTO>();
		}
		return teamSelector;
	}
	
	public JComboBox<InstalacionDTO> getInstallationSelector() {
		if (installationSelector == null) {
			installationSelector = new JComboBox<InstalacionDTO>();
		}
		return installationSelector;
	}
	
	public JDateChooser getDate() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setPreferredSize(new Dimension(200, 50));
			dateChooser.setMaximumSize(new Dimension(200, 50));
		}
		return dateChooser;
	}
	
	public JSpinner getStartTime() {
		if (horaInicio == null) {
			horaInicio = createHourSpinner();
		}
		return horaInicio;
	}
	
	public JSpinner getEndTime() {
		if (horaFin == null) {
			horaFin = createHourSpinner();
		}
		return horaFin;
	}
	
	public JButton getAcceptButton() {
		if (acceptBtn == null) {
			acceptBtn = new JButton("Crear entrenamiento");
		}
		return acceptBtn;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setBorder(BorderFactory.createEmptyBorder(8,0,8,0));
        return label;
    }
	
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
