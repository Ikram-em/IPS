package giis.demo.tkrun.noticias;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.noticias.CreacionNoticias;

public class CrearNoticiaController extends AbstractController {

	private CreacionNoticias view;
	private MetodosNoticias nModel = new MetodosNoticias();

	public CrearNoticiaController(CreacionNoticias creacionNoticias, AuditService audit) {
		super(audit);
		this.view = creacionNoticias;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		addActionListener();
	}

	private void addActionListener() {
		view.getBtGuardar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearNoticia();
			}
		});
	}

	public void crearNoticia() {

		boolean publicada = view.getRbPublicada().isSelected();
		String titulo = view.getTxtTitulo().getText();
		String contenido = view.getTxtContenido().getText();

		if (titulo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe introducir un título para la noticia.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (contenido.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe establecer un contenido para la noticia.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String nombreImagen = null;

		if (view.getImagenSeleccionada() != null) {

			File carpetaDestino = new File("src/main/resources/images/");
			carpetaDestino.mkdirs();

			File destino = new File(carpetaDestino, view.getImagenSeleccionada().getName());

			try {
				Files.copy(view.getImagenSeleccionada().toPath(), destino.toPath(),
						StandardCopyOption.REPLACE_EXISTING);

				nombreImagen = view.getImagenSeleccionada().getName();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al guardar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// Guardar noticia (adaptar si tu método usa imagen)
		nModel.crearNoticia(titulo, contenido, publicada, nombreImagen);

		JOptionPane.showMessageDialog(null, "Noticia creada con éxito\nTítulo: " + titulo, "Éxito",
				JOptionPane.INFORMATION_MESSAGE);


		view.dispose(); // Cerrar ventana

		resetFormulario();
	}

	private void resetFormulario() {

		// Limpiar campos de texto
		view.getTxtTitulo().setText("");
		view.getTxtContenido().setText("");

		// Limpiar estado del radio button
		view.getRbBorrador().setSelected(true);

		// Borrar imagen si la hubiera
		view.borrarImagen();

		// Devolver texto a la etiqueta
		view.getLbPrevisualizar().setText("PREVISUALIZAR IMAGEN");

		// Devolver foco al título
		view.getTxtTitulo().requestFocus();
	}



}
