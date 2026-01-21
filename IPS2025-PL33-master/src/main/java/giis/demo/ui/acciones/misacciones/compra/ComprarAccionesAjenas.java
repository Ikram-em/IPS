package giis.demo.ui.acciones.misacciones.compra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ComprarAccionesAjenas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelLista;


	public ComprarAccionesAjenas() {
        setTitle("Comprar acciones de otro accionista");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        setMinimumSize(getSize());

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

		contentPane.add(getPanelLista());

        JScrollPane scrollPane = new JScrollPane(panelLista);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Comprar acciones de otro accionista"));
        contentPane.add(scrollPane, BorderLayout.CENTER);


    }

	public JPanel getPanelLista() {
		if (this.panelLista == null) {
			this.panelLista = new JPanel();
			panelLista.setLayout(new GridLayout(0, 1, 10, 10));
			panelLista.setBackground(Color.WHITE);
		}
		return panelLista;
	}


}
