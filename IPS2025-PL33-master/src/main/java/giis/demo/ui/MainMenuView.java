package giis.demo.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenuView extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JPanel contentPane;
	private JButton btnEmpleados;
	private JButton btnReservas;
	private JButton btnTienda;
	private JButton btnIngresos;
	private JButton btnEquipos;
	private JButton btnPartidos;
    private JButton btnAcciones;
	private JButton btnNoticias;
	private JButton btnEntradas;
	private JButton btEntrenamientos;
	private JButton btLogout;
	private JButton btAbonos;
	private JButton btnNoticiasMenu;
	private JButton btnEstadisticas;
	private JButton btnStock;
	private JButton btnPortalMedico;
	private JButton btGestionarFranjas;
	private JButton btnVerLog;
	private JButton getEstadisticasPartidos;
	private JButton getEstadisticasJugador;

	public MainMenuView() {
		setBackground(Color.LIGHT_GRAY);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	public JButton getBtnEquipos() {
		if (btnEquipos == null) {
			btnEquipos = new JButton("Gestionar Equipos");
			btnEquipos.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnEquipos.setBackground(Color.WHITE);
			btnEquipos.setPreferredSize(new Dimension(300, 30));
		}
		return btnEquipos;
	}
	
	public JButton getBtnReservas() {
		if (btnReservas == null) {
			btnReservas = new JButton("Reserva Instalaciones");
			btnReservas.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnReservas.setBackground(Color.WHITE);
			btnReservas.setPreferredSize(new Dimension(300, 30));
		}
		return btnReservas;
	}

	public JButton getBtnFranjas() {
		if (btGestionarFranjas == null) {
			btGestionarFranjas = new JButton("Gestionar Franjas");
			btGestionarFranjas.setFont(new Font("Calibri", Font.PLAIN, 15));
			btGestionarFranjas.setBackground(Color.WHITE);
			btGestionarFranjas.setPreferredSize(new Dimension(300, 30));
		}
		return btGestionarFranjas;
	}
	
	public JButton getBtnEmpleados() {
		if (btnEmpleados == null) {
			btnEmpleados = new JButton("Gestionar Empleados");
			btnEmpleados.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnEmpleados.setBackground(Color.WHITE);
			btnEmpleados.setPreferredSize(new Dimension(300, 30));
		}
		return btnEmpleados;
	}
	
	public JButton getBtnTienda() {
		if (btnTienda == null) {
			btnTienda = new JButton("Tienda");
			btnTienda.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnTienda.setBackground(Color.WHITE);
			btnTienda.setPreferredSize(new Dimension(300, 30));
		}
		return btnTienda;
	}

    public JButton getBtnComprarEntradas() {
        if (btnEntradas == null) {
            btnEntradas = new JButton("Comprar Entradas");
            btnEntradas.setFont(new Font("Calibri", Font.PLAIN, 15));
            btnEntradas.setBackground(Color.WHITE);
            btnEntradas.setPreferredSize(new Dimension(300, 30));
        }
        return btnEntradas;
    }
    
    public JButton getBtnAbonos() {
    	if (btAbonos == null) {
    		btAbonos = new JButton("Menu Abonos");
    		btAbonos.setFont(new Font("Calibri", Font.PLAIN, 15));
    		btAbonos.setBackground(Color.WHITE);
    		btAbonos.setPreferredSize(new Dimension(300, 30));
        }
        return btAbonos;
    }

	public JButton getBtnIngresos() {
		if (btnIngresos == null) {
			btnIngresos = new JButton("Historial de Ingresos");
			btnIngresos.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnIngresos.setBackground(Color.WHITE);
			btnIngresos.setPreferredSize(new Dimension(300, 30));
		}
	    return btnIngresos;
	}
	
	public JButton getBtnPartidos() {
		if (btnPartidos == null) {
			btnPartidos = new JButton("Menu Partidos");
			btnPartidos.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnPartidos.setBackground(new Color(255, 255, 255));
			btnPartidos.setPreferredSize(new Dimension(300, 30));
		}
		
		return btnPartidos;
	}
	public JButton getBtnAcciones() {
		if (btnAcciones == null) {
			btnAcciones = new JButton("Portal Acciones");
			btnAcciones.setBackground(Color.WHITE);
			btnAcciones.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnAcciones.setPreferredSize(new Dimension(300, 30));
		}
		return btnAcciones;
	}
    
    public JButton getBtnGestionNoticias() {
		if (btnNoticias == null) {
			btnNoticias = new JButton("Gestionar Noticias");
			btnNoticias.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnNoticias.setBackground(new Color(255, 255, 255));
			btnNoticias.setPreferredSize(new Dimension(300, 30));
		}
	    return btnNoticias;
	}

    public JButton getBtnEntrenamientos() {
		if (btEntrenamientos == null) {
			btEntrenamientos = new JButton("Añadir entrenamiento");
			btEntrenamientos.setFont(new Font("Calibri", Font.PLAIN, 15));
			btEntrenamientos.setBackground(new Color(255, 255, 255));	
			btEntrenamientos.setPreferredSize(new Dimension(300, 30));
		}
		return btEntrenamientos;
	}
    
    public JPanel getSpacing() {
    	JPanel spacing = new JPanel();
    	spacing.setPreferredSize(new Dimension(300, 8));
    	spacing.setMaximumSize(new Dimension(300, 8));
    	spacing.setBackground(null);
    	return spacing;
    }
    
    public JButton getBtnLogout() {
		if (btLogout == null) {
			btLogout = new JButton("Cerrar sesión");
			btLogout.setFont(new Font("Calibri", Font.BOLD, 15));
			btLogout.setBackground(new Color(255, 255, 255));
			btLogout.setPreferredSize(new Dimension(300, 30));
		}
		return btLogout;
	}


	public JButton getBtnNoticiasMenu() {
		if (btnNoticiasMenu == null) {
			btnNoticiasMenu = new JButton("Noticias");
			btnNoticiasMenu.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnNoticiasMenu;
	}

	public JButton getBtnEstadisticas() {
		if (btnEstadisticas == null) {
			btnEstadisticas = new JButton("Estadísticas Generales del Club");
	        btnEstadisticas.setFont(new Font("Calibri", Font.PLAIN, 15));
	        btnEstadisticas.setBackground(Color.WHITE);
	    }
	    return btnEstadisticas;
	}
	 
	public JButton getBtnStock() {
		if (btnStock == null) {
			btnStock = new JButton("Ver Stock");
		    btnStock.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnStock;
	}

	public JButton getBtnPortalMedico() {
		if (btnPortalMedico == null) {
			btnPortalMedico = new JButton("Portal Médico");
			btnPortalMedico.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnPortalMedico;
	}

	public JButton getBtnVerLog() {
		if (btnVerLog == null) {
			btnVerLog = new JButton("Consultar log del sistema");
			btnVerLog.setBackground(Color.WHITE);
			btnVerLog.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnVerLog;
	}
	
	public JButton getBtnEstadisticasPartido() {
		if (getEstadisticasPartidos == null) {
			getEstadisticasPartidos = new JButton("Estadisticas por Partido");
			getEstadisticasPartidos.setBackground(Color.WHITE);
			getEstadisticasPartidos.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return getEstadisticasPartidos;
	}
	
	public JButton getBtnEstadisticasJugador() {
		if (getEstadisticasJugador == null) {
			getEstadisticasJugador = new JButton("Consultar estadísticas por jugador");
			getEstadisticasJugador.setBackground(Color.WHITE);
			getEstadisticasJugador.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return getEstadisticasJugador;
	}
}