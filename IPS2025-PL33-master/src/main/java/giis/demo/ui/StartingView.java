package giis.demo.ui;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.login.LoginController;
import giis.demo.ui.login.LoginMenuView;
import giis.demo.util.Database;

public class StartingView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEjecutar;
	private JButton btnCargarDatos;
	private JButton btnInicializarBaseDatos;

	public StartingView() {
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getBtnInicializarBaseDatos());
		contentPane.add(getBtnCargarDatos());
		contentPane.add(getBtnEjecutar());
	}
	


	private JButton getBtnEjecutar() {
		if (btnEjecutar == null) {
			btnEjecutar = new JButton("Ejecutar aplicación");
			btnEjecutar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new LoginController(new LoginMenuView());
				}
			});
		}
		return btnEjecutar;
	}
	
	
	private JButton getBtnCargarDatos() {
		if (btnCargarDatos == null) {
			btnCargarDatos = new JButton("Cargar Datos Iniciales para Pruebas");
			btnCargarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Database db = new Database();
					db.createDatabase(false);
					db.loadDatabase();
				}
			});
		}
		return btnCargarDatos;
	}
	
	private JButton getBtnInicializarBaseDatos() {
		if (btnInicializarBaseDatos == null) {
			btnInicializarBaseDatos = new JButton("Inicializar Base de Datos en blanco");
			btnInicializarBaseDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Database db = new Database();
					db.createDatabase(false);

					// Borrar ficheros .log generados por el logger
					File logDir = new File("logs"); // Ajusta según la carpeta donde guardes los logs
					if (logDir.exists() && logDir.isDirectory()) {
						File[] logs = logDir.listFiles((dir, name) -> name.endsWith(".log"));
						if (logs != null) {
							for (File log : logs) {
								if (!log.delete()) {
									System.err.println("No se pudo borrar el fichero de log: " + log.getName());
								}
							}
						}
					}
				}
			});
		}
		return btnInicializarBaseDatos;
	}
}
