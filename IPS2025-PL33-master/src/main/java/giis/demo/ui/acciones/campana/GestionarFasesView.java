package giis.demo.ui.acciones.campana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GestionarFasesView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txFase;
	private JButton btnAdelantar;
	private JButton cancelButton;


	/**
	 * Create the dialog.
	 */
	public GestionarFasesView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 652, 324);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lbFase = new JLabel("Fase Actual:");
				lbFase.setFont(new Font("Calibri", Font.PLAIN, 15));
				lbFase.setBounds(201, 56, 102, 39);
				panel.add(lbFase);
			}
			{
				txFase = new JTextField();
				txFase.setFocusable(false);
				txFase.setFont(new Font("Calibri", Font.PLAIN, 15));
				txFase.setEditable(false);
				txFase.setBounds(313, 59, 142, 32);
				panel.add(txFase);
				txFase.setColumns(10);
			}
			panel.add(getBtnAdelantar());
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Gestionar Campaña:");
				lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 25));
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Calibri", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getBtnAdelantar() {
		if (btnAdelantar == null) {
			btnAdelantar = new JButton("Adelantar Fase");
			btnAdelantar.setBackground(new Color(176, 196, 222));
			btnAdelantar.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnAdelantar.setBounds(226, 129, 153, 32);
		}
		return btnAdelantar;
	}
	
	public JButton getBtnCancelar() {
		return this.cancelButton;
	}

	public JTextField getTxFaseActual() {
		return this.txFase;
	}
}
