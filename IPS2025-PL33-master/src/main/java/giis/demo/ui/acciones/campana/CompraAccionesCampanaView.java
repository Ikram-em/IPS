package giis.demo.ui.acciones.campana;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.accion.AccionistaViewDTO;
import giis.demo.tkrun.accion.campana.CampanaDTO;

public class CompraAccionesCampanaView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JSpinner numberSelector;
    private JTextField campaignValue;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtEmail;
    private JButton btnBuy;
    private JButton btnVerFactura;
    private JLabel etiquetaNumAcciones;
	private JLabel etiquetaNumAccionesAccionista;

    public CompraAccionesCampanaView() {
        setTitle("Compra de Acciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 450);
        setLocationRelativeTo(null);

        initContentPane();
        initTitlePanel();
        initFormPanel();
        initButtonPanel();
    }

    private void initContentPane() {
        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
    }

    private void initTitlePanel() {
        JLabel title = new JLabel("Comprar Acciones");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(33, 33, 33));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel titlePn = new JPanel(new BorderLayout());
        titlePn.setBackground(contentPane.getBackground());
        titlePn.add(title, BorderLayout.CENTER);
        contentPane.add(titlePn, BorderLayout.NORTH);
    }

    private void initFormPanel() {
        JPanel formPn = new JPanel();
        formPn.setLayout(new BoxLayout(formPn, BoxLayout.Y_AXIS));
        formPn.setBackground(Color.WHITE);
        formPn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        
        formPn.add(getEtiquetaNumAcciones());

		formPn.add(getEtiquetaNumAccionesAccionista());

        formPn.add(getLabeledComponent("Campaña:", getCampaignValue()));
        formPn.add(Box.createVerticalStrut(10));

        formPn.add(getLabeledComponent("Cantidad de acciones:", getCantAcciones()));
        formPn.add(Box.createVerticalStrut(15));

        formPn.add(getLabeledComponent("Teléfono:", getTxtTelefono()));
        formPn.add(Box.createVerticalStrut(10));

        formPn.add(getLabeledComponent("Dirección:", getTxtDireccion()));
        formPn.add(Box.createVerticalStrut(10));

        formPn.add(getLabeledComponent("Email:", getTxtEmail()));

        contentPane.add(formPn, BorderLayout.CENTER);
    }

    private void initButtonPanel() {
        JPanel buttonPn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPn.setBackground(contentPane.getBackground());

        buttonPn.add(getBuyBtn());
        buttonPn.add(getVerFacturaBtn());

        contentPane.add(buttonPn, BorderLayout.SOUTH);
    }

    // --- Componentes ---
    public JLabel getEtiquetaNumAccionesAccionista() {
		if (etiquetaNumAccionesAccionista == null) {
			etiquetaNumAccionesAccionista = new JLabel();
			etiquetaNumAccionesAccionista.setFont(new Font("Calibri", Font.PLAIN, 15));
			etiquetaNumAccionesAccionista.setForeground(Color.RED);
			etiquetaNumAccionesAccionista.setText("");
		}
		return etiquetaNumAccionesAccionista;
	}
    
    public JLabel getEtiquetaNumAcciones() {
		if (etiquetaNumAcciones == null) {
			etiquetaNumAcciones = new JLabel();
			etiquetaNumAcciones.setFont(new Font("Calibri", Font.PLAIN, 15));
			etiquetaNumAcciones.setText("");
		}
		return etiquetaNumAcciones;
	}
    
    public JButton getBuyBtn() {
        if (btnBuy == null) btnBuy = new JButton("Comprar");
        return btnBuy;
    }

    public JButton getVerFacturaBtn() {
        if (btnVerFactura == null) {
            btnVerFactura = new JButton("Ver Factura");
            btnVerFactura.setEnabled(false);
        }
        return btnVerFactura;
    }

    public JTextField getCampaignValue() {
        if (campaignValue == null) {
        	campaignValue = new JTextField();
        	campaignValue.setEditable(false);
        }
        return campaignValue;
    }

    public JSpinner getCantAcciones() {
        if (numberSelector == null) {
            numberSelector = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
            numberSelector.setPreferredSize(new Dimension(80, 28));
        }
        return numberSelector;
    }

    public JTextField getTxtTelefono() {
        if (txtTelefono == null) {
            txtTelefono = new JTextField();
            txtTelefono.setPreferredSize(new Dimension(200, 28));
        }
        return txtTelefono;
    }

    public JTextField getTxtDireccion() {
        if (txtDireccion == null) {
            txtDireccion = new JTextField();
            txtDireccion.setPreferredSize(new Dimension(300, 28));
        }
        return txtDireccion;
    }

    public JTextField getTxtEmail() {
        if (txtEmail == null) {
            txtEmail = new JTextField();
            txtEmail.setPreferredSize(new Dimension(300, 28));
        }
        return txtEmail;
    }

    // --- Helper para layout de formulario ---
    private JPanel getLabeledComponent(String label, Component comp) {
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(180, 28));
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);
        panel.add(lbl);
        panel.add(comp);

        return panel;
    }
}
