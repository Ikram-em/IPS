package giis.demo.tkrun.partidos;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.instalacion.InstalacionDAO;
import giis.demo.tkrun.instalacion.InstalacionDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.partidos.PartidoView;

public class PartidoController extends AbstractController {

    private PartidoView view;
    private PartidoDAO dao;
    private InstalacionDTO campoPrincipal;
    private InstalacionDAO instalacionDAO;

    public PartidoController(PartidoView view, AuditService audit) {
	super(audit);
	this.view = view;
	this.instalacionDAO = new InstalacionDAO();
	try {
	    this.dao = new PartidoDAO();

	    view.getComboEquipoLocal()
		.removeAllItems();
	    for (String e : dao.obtenerEquiposLocales()) {
		view.getComboEquipoLocal()
		    .addItem(e);
	    }

	    view.getTxtEquipoVisitante()
		.setText("");

	    campoPrincipal = instalacionDAO.getFacilityByName(
		"Campo Principal");
	    cargarPartidos();
	    initListeners();
	    this.view.setVisible(true);

	} catch (Exception e) {
	    e.printStackTrace();
	    view.setMensajeInfo(
		"Error al iniciar el controlador: " + e.getMessage());
	}
    }

    private void initListeners() {
	view.getBtnAtras()
	    .addActionListener(e -> view.dispose());

	addLoggedAction(view.getBtnAñadir(), "Añadido partido", () -> {
	    Integer id = addPartido();

	    if (id != null) {
		audit.log("INFO", "Partido creado con id=" + id);
	    }
	});

	addLoggedAction(view.getBtnCargarArchivo(), "Añadido partido", () -> {
	    Integer numPartidos = seleccionarArchivo();

	    if (numPartidos != 0) {
		audit.log("INFO", "Se crearon " + numPartidos
		    + " partidos importados desde un archivo");
	    }
	});

    }

    private Integer addPartido() {
	Integer id = null;
	try {
	    java.util.Date fechaDate = view.getFechaSeleccionada();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(fechaDate);
	    LocalDate fecha = LocalDate.of(cal.get(Calendar.YEAR),
		cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

	    String inicioStr = view.getHoraInicioTexto();
	    String finStr = view.getHoraFinTexto();
	    LocalTime inicioNuevo = LocalTime.parse(inicioStr);
	    LocalTime finNuevo = LocalTime.parse(finStr);

	    String equipoLocal = (String) view.getComboEquipoLocal()
					      .getSelectedItem();
	    String equipoVisitante = view.getTxtEquipoVisitante()
					 .getText()
					 .trim();

	    // Validaciones básicas
	    if (equipoLocal == null || equipoLocal.isEmpty()) {
		view.setMensajeInfo("Selecciona un equipo local.");
		return null;
	    }
	    if (equipoVisitante.isEmpty()) {
		view.setMensajeInfo("Introduce un equipo visitante.");
		return null;
	    }
	    if (equipoLocal.equalsIgnoreCase(equipoVisitante)) {
		view.setMensajeInfo(
		    "No puedes programar un partido entre el mismo equipo.");
		return null;
	    }
	    if (!finNuevo.isAfter(inicioNuevo)) {
		view.setMensajeInfo(
		    "La hora de fin debe ser posterior a la de inicio.");
		return null;
	    }
	    if (java.time.Duration.between(inicioNuevo, finNuevo)
				  .toMinutes() < 60) {
		view.setMensajeInfo(
		    "La duración mínima de un partido es 1 hora.");
		return null;
	    }
	    LocalTime apertura = LocalTime.of(8, 0);
	    LocalTime cierre = LocalTime.of(22, 0);
	    if (inicioNuevo.isBefore(apertura) || finNuevo.isAfter(cierre)) {
		view.setMensajeInfo(
		    "Los partidos deben ser entre 08:00 y 22:00.");
		return null;
	    }

	    // Comprobación de duplicados y solapamientos
	    for (Partido p : dao.obtenerTodos()) {
		if (!LocalDate.parse(p.getFecha())
			      .equals(fecha)) {
		    continue;
		}

		boolean mismoLocal = p.getEquipoLocal()
				      .trim()
				      .equalsIgnoreCase(equipoLocal.trim());
		boolean mismoVisitante = p.getEquipoVisitante()
					  .trim()
					  .equalsIgnoreCase(
					      equipoVisitante.trim());
		boolean mismaHora = LocalTime.parse(p.getHoraInicio())
					     .equals(inicioNuevo)
		    && LocalTime.parse(p.getHoraFin())
				.equals(finNuevo);

		if (mismoLocal && mismoVisitante && mismaHora) {
		    javax.swing.JOptionPane.showMessageDialog(view,
			"El partido ya existe exactamente para esa fecha y hora.",
			"Partido duplicado",
			javax.swing.JOptionPane.WARNING_MESSAGE);
		    return null;
		}

		if ((inicioNuevo.isBefore(LocalTime.parse(p.getHoraFin()))
		    && finNuevo.isAfter(LocalTime.parse(p.getHoraInicio())))
		    && (mismoLocal || mismoVisitante)) {
		    javax.swing.JOptionPane.showMessageDialog(view,
			"No se puede programar: solapamiento con otro partido.",
			"Solapamiento de horario",
			javax.swing.JOptionPane.WARNING_MESSAGE);
		    return null;
		}
	    }

	    // --- Suplemento ---
	    boolean tieneSuplemento = view.getRbtnSuplementoSi()
					  .isSelected();
	    double precioSuplemento = 0.0;

	    if (tieneSuplemento) {
		String precioStr = view.getTxtPrecioSuplemento()
				       .getText()
				       .trim();

		if (precioStr.isEmpty()) {
		    view.setMensajeInfo(
			"Introduce un precio para el suplemento.");
		    return null;
		}

		try {
		    precioSuplemento = Double.parseDouble(precioStr);
		} catch (NumberFormatException nfe) {
		    view.setMensajeInfo("El precio debe ser un número válido.");
		    return null;
		}

		if (precioSuplemento < 0) {
		    view.setMensajeInfo("El precio no puede ser negativo.");
		    return null;
		}
	    }

	    // Insertar en la BD
	    java.sql.Date fechaSql = java.sql.Date.valueOf(fecha);
	    id = dao.insertarPartido(fechaSql, inicioNuevo, finNuevo,
		equipoLocal, equipoVisitante, campoPrincipal.getId(),
		tieneSuplemento, precioSuplemento);

	    view.setMensajeInfo("Partido añadido correctamente.");
	    cargarPartidos();

	    return id;

	} catch (Exception ex) {
	    view.setMensajeInfo("Error: " + ex.getMessage());
	    ex.printStackTrace();
	}
	return id;
    }

    private void cargarPartidos() {
	try {
	    List<Partido> partidos = dao.obtenerTodos();
	    view.mostrarPartidos(partidos);
	} catch (Exception e) {
	    e.printStackTrace();
	    view.setMensajeInfo(
		"Error al cargar los partidos: " + e.getMessage());
	}
    }

    private String validarPartido(LocalDate fecha, LocalTime inicioNuevo,
	LocalTime finNuevo, String equipoLocal, String equipoVisitante) {

// Equipo local vacío
	if (equipoLocal == null || equipoLocal.isEmpty()) {
	    return "Selecciona un equipo local.";
	}

	if (equipoVisitante == null || equipoVisitante.trim()
						      .isEmpty()) {
	    return "Introduce un equipo visitante.";
	}

	if (equipoLocal.equalsIgnoreCase(equipoVisitante)) {
	    return "No puedes programar un partido entre el mismo equipo.";
	}

	if (!finNuevo.isAfter(inicioNuevo)) {
	    return "La hora de fin debe ser posterior a la de inicio.";
	}

	if (java.time.Duration.between(inicioNuevo, finNuevo)
			      .toMinutes() < 60) {
	    return "La duración mínima de un partido es 1 hora.";
	}

	LocalTime apertura = LocalTime.of(8, 0);
	LocalTime cierre = LocalTime.of(22, 0);
	if (inicioNuevo.isBefore(apertura) || finNuevo.isAfter(cierre)) {
	    return "Los partidos deben ser entre 08:00 y 22:00.";
	}

	// -------- VALIDACIÓN BD: duplicados y solapamientos --------
	for (Partido p : dao.obtenerTodos()) {

	    if (!LocalDate.parse(p.getFecha())
			  .equals(fecha)) {
		continue;
	    }

	    boolean mismoLocal = p.getEquipoLocal()
				  .trim()
				  .equalsIgnoreCase(equipoLocal.trim());
	    boolean mismoVisitante = p.getEquipoVisitante()
				      .trim()
				      .equalsIgnoreCase(equipoVisitante.trim());
	    boolean mismaHora = LocalTime.parse(p.getHoraInicio())
					 .equals(inicioNuevo)
		&& LocalTime.parse(p.getHoraFin())
			    .equals(finNuevo);

	    if (mismoLocal && mismoVisitante && mismaHora) {
		return "El partido ya existe exactamente para esa fecha y hora.";
	    }

	    boolean solapa = inicioNuevo.isBefore(
		LocalTime.parse(p.getHoraFin()))
		&& finNuevo.isAfter(LocalTime.parse(p.getHoraInicio()));

	    if (solapa && (mismoLocal || mismoVisitante)) {
		return "No se puede programar: solapamiento con otro partido.";
	    }
	}

	return null; // válido
    }

    /* devuelve el numero de partidos insertados */
    private int seleccionarArchivo() {
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle("Seleccionar archivo CSV o JSON");

	chooser.setAcceptAllFileFilterUsed(false);
	chooser.addChoosableFileFilter(
	    new FileNameExtensionFilter("Archivo CSV", "csv"));
	chooser.addChoosableFileFilter(
	    new FileNameExtensionFilter("Archivo JSON", "json"));

	int result = chooser.showOpenDialog(view);

	if (result == JFileChooser.APPROVE_OPTION) {
	    File archivo = chooser.getSelectedFile();
	    String ruta = archivo.getAbsolutePath();

	    ParserPartidos parser = new ParserPartidos(ruta);
	    List<PartidoDTO> partidosImportados;

	    int countNoValidos = 0;

	    try {
		if (ruta.toLowerCase()
			.endsWith(".csv")) {
		    partidosImportados = parser.parseCSV(ruta);
		} else if (ruta.toLowerCase()
			       .endsWith(".json")) {
		    partidosImportados = parser.parseJSON(ruta);
		} else {
		    view.setMensajeInfo(
			"Formato no soportado. Solo CSV o JSON.");
		    return 0;
		}

		// --- Mostrar diálogo de selección ---
		DialogSeleccionPartidos dialog = new DialogSeleccionPartidos(
		    view, partidosImportados);
		dialog.setVisible(true);

		if (!dialog.isConfirmado()) {
		    view.setMensajeInfo("Importación cancelada.");
		    return 0;
		}

		List<PartidoDTO> seleccionados = dialog.getSeleccionados(
		    partidosImportados);

		// --- Insertar SOLO los seleccionados ---
		for (PartidoDTO dto : seleccionados) {

		    LocalDate fecha = LocalDate.parse(dto.getFecha());
		    Date fechaSql = Date.valueOf(fecha);

		    LocalTime horaInicio = dto.getHora_inicio();
		    LocalTime horaFin = dto.getHora_fin();

		    String error = validarPartido(fecha, horaInicio, horaFin,
			dto.getNombreEquipoInterno(),
			dto.getNombreEquipoExterno());

		    if (error != null) {
			countNoValidos++;
			JOptionPane.showMessageDialog(view,
			    "Partido ignorado:\n" + error + "\n\n"
				+ "Datos del partido:\n" + dto.toString(),
			    "Partido no válido", JOptionPane.WARNING_MESSAGE);
			continue; // no se inserta
		    }

		    dao.insertarPartido(fechaSql, horaInicio, horaFin,
			dto.getNombreEquipoInterno(),
			dto.getNombreEquipoExterno(), dto.getId_instalacion(),
			dto.isPriced(),
			dto.isPriced() ? dto.getPrecioSuplemento() : 0);
		}

		cargarPartidos();
		view.setMensajeInfo("Se importaron "
		    + (seleccionados.size() - countNoValidos) + " partidos.");

		return seleccionados.size() - countNoValidos;

	    } catch (Exception ex) {
		ex.printStackTrace();
		view.setMensajeInfo(
		    "Error al importar archivo: " + ex.getMessage());
	    }
	}
	return 0;
    }
}