package giis.demo.ui.acciones.misacciones;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelAccion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lbInfo;
	private JButton btAccion;
	
	
	public PanelAccion(int idAccion, String string, String textoBoton, ActionListener listener, Color color) {

        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2, 10, 10));
        
        if(string == null) {
            lbInfo = new JLabel("ID Accion: " + idAccion);
        }else {
            lbInfo = new JLabel("ID Accion: " + idAccion + "- Acionista: " + string);
        }
        lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbInfo);

        btAccion = new JButton(textoBoton);
        btAccion.addActionListener(listener);
        add(btAccion);
        btAccion.setBackground(color);
        setVisible(true);
        
    }
	
	
	
}
;