package giis.demo.tkrun.partidos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DialogSeleccionPartidos extends JDialog {

    private List<JCheckBox> checkboxes = new ArrayList<>();
    private boolean confirmado = false;

    public DialogSeleccionPartidos(JFrame parent, List<PartidoDTO> partidos) {
	super(parent, "Seleccionar partidos a importar", true);
	getContentPane().setLayout(new BorderLayout());

	JPanel panelLista = new JPanel();
	panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));

	for (PartidoDTO dto : partidos) {
	    JCheckBox cb = new JCheckBox(dto.toString());
	    checkboxes.add(cb);
	    panelLista.add(cb);
	}

	JScrollPane scroll = new JScrollPane(panelLista);
	getContentPane().add(scroll, BorderLayout.CENTER);

	JPanel panelBotones = new JPanel();
	JButton btnOk = new JButton("Importar seleccionados");
	JButton btnCancel = new JButton("Cancelar");

	btnOk.addActionListener(e -> {
	    confirmado = true;
	    dispose();
	});

	btnCancel.addActionListener(e -> dispose());

	JButton btnSeleccionarTodos = new JButton("Seleccionar Todos");
	btnSeleccionarTodos.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		seleccionarTodos(partidos);
	    }

	});
	panelBotones.add(btnSeleccionarTodos);

	panelBotones.add(btnOk);
	panelBotones.add(btnCancel);

	getContentPane().add(panelBotones, BorderLayout.SOUTH);

	setSize(500, 400);
	setLocationRelativeTo(parent);
    }

    public boolean isConfirmado() {
	return confirmado;
    }

    public List<PartidoDTO> getSeleccionados(List<PartidoDTO> originales) {
	List<PartidoDTO> seleccionados = new ArrayList<>();
	for (int i = 0; i < originales.size(); i++) {
	    if (checkboxes.get(i)
			  .isSelected()) {
		seleccionados.add(originales.get(i));
	    }
	}
	return seleccionados;
    }

    private void seleccionarTodos(List<PartidoDTO> originales) {
	for (int i = 0; i < originales.size(); i++) {
	    checkboxes.get(i)
		      .setSelected(true);
	}

    }
}