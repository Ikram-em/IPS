package giis.demo.ui.acciones.campana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CrearCampanaOptionPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lbNumeroAcciones;
	private JSpinner spinner;
	private JPanel panel_1;
	private JLabel lbTitulo;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public CrearCampanaOptionPanel() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 711, 308);
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
			panel.add(getLbNumeroAcciones());
			panel.add(getSpinner());
		}
		contentPanel.add(getPanel_1(), BorderLayout.NORTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public JButton getOkButton() {
		return this.okButton;
	}
	
	public JButton getCancelButton() {
		return this.cancelButton;
	}
	
	private JLabel getLbNumeroAcciones() {
		if (lbNumeroAcciones == null) {
			lbNumeroAcciones = new JLabel("<html>Introduzca el número máximo de acciones que se podrán vender:</html>");
			lbNumeroAcciones.setHorizontalAlignment(SwingConstants.CENTER);
			lbNumeroAcciones.setFont(new Font("Calibri", Font.PLAIN, 15));
			lbNumeroAcciones.setBounds(61, 52, 304, 40);
		}
		return lbNumeroAcciones;
	}
	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner(new SpinnerNumberModel(50, 50, 200, 10));
			spinner.setBounds(404, 65, 197, 27);
		}
		return spinner;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.add(getLbTitulo());
		}
		return panel_1;
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Crear Campaña");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}
}
