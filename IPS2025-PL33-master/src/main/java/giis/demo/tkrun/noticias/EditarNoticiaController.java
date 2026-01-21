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

public class EditarNoticiaController extends AbstractController {

	private CreacionNoticias view;
	private NoticiaDto noticia;
	private Runnable onUpdated;
	private MetodosNoticias model = new MetodosNoticias();

	public EditarNoticiaController(CreacionNoticias creacionNoticias, NoticiaDto noticia, AuditService audit,
			Runnable onUpdated) {
		super(audit);
		this.view = creacionNoticias;
		this.noticia = noticia;
		this.onUpdated = onUpdated;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		view.getBtGuardar().setText("Actualizar noticia");
		cargarDatosNoticia();
		addActionListener();
	}

	private void addActionListener() {
		view.getBtGuardar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarNoticia();
			}
		});
	}

	private void cargarDatosNoticia() {
		view.getTxtTitulo().setText(noticia.getTitulo());
		view.getTxtContenido().setText(noticia.getContenido());
		if (noticia.getImagen() != null) {
			view.setImagenSeleccionada(new File(noticia.getImagen()));
		}
		view.getRbPublicada().setSelected(noticia.isPublicada());
		if (view.getImagenSeleccionada() == null) {
			view.getBtInsertarImagen().setEnabled(true);
			view.getBtBorrarImagen().setEnabled(false);
		} else {
			view.getBtInsertarImagen().setEnabled(false);
			view.getBtBorrarImagen().setEnabled(true);
		}
		view.setNoticia(noticia);
	}

	private void actualizarNoticia() {

		String titulo = view.getTxtTitulo().getText();
		String contenido = view.getTxtContenido().getText();
		boolean publicada = view.getRbPublicada().isSelected();

		if (titulo.isEmpty() || contenido.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String nombreImagen = view.getNoticia().getImagen(); // Imagen existente si no se cambia

		if (view.getImagenSeleccionada() != null) {

			File carpetaDestino = new File("src/main/resources/images/");
			carpetaDestino.mkdirs();

			File destino = new File(carpetaDestino, view.getImagenSeleccionada().getName());
			try {
				Files.copy(view.getImagenSeleccionada().toPath(), destino.toPath(),
						StandardCopyOption.REPLACE_EXISTING);

				nombreImagen = view.getImagenSeleccionada().getName(); // Nueva imagen

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al actualizar la imagen.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		view.getNoticia().setTitulo(titulo);
		view.getNoticia().setContenido(contenido);
		view.getNoticia().setPublicada(publicada);
		view.getNoticia().setImagen(nombreImagen);

		model.actualizarNoticia(view.getNoticia());

		if (onUpdated != null)
			onUpdated.run();

		JOptionPane.showMessageDialog(null, "Noticia actualizada correctamente", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);

		view.dispose();
	}

}
