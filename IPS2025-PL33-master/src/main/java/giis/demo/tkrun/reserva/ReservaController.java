package giis.demo.tkrun.reserva;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.ingresos.Ingreso;
import giis.demo.tkrun.ingresos.IngresoDAO;
import giis.demo.tkrun.ingresos.TipoIngreso;
import giis.demo.tkrun.instalacion.InstalacionDAO;
import giis.demo.tkrun.instalacion.InstalacionDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.reserva.ReservaView;
import giis.demo.ui.reserva.VentanaResumenReserva;
import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class ReservaController extends AbstractController {

    private final ReservaDAO reservaDAO;
    private final ReservaView view;
    private List<EstadoFranjaDTO> franjasEstado;
    private Date fechaReservaSeleccionada;
    private InstalacionDAO instalacionDAO;

    private final static double PRECIO_POR_HORA = 50.0;
    private final static int MARGEN_MINUTOS = 90;

	public ReservaController(ReservaView view, AuditService audit) {
		super(audit);
        this.view = view;
        this.reservaDAO = new ReservaDAO();
        this.franjasEstado = new ArrayList<>();
        this.instalacionDAO = new InstalacionDAO();
        loadInstalaciones();
        initializeView();
        addListeners();
        this.view.setVisible(true);
    }

    private void loadInstalaciones() {
        List<InstalacionDTO> instalaciones = this.instalacionDAO.getInstalaciones();
        for (InstalacionDTO instalacion : instalaciones) {
            view.getInstalacionSelector().addItem(instalacion);
        }
    }

    private void initializeView() {
        view.setEstadoDisponibilidadMensaje("Pulse 'Comprobar Estado' para empezar.", Color.BLUE);
        view.getBtnReservar().setEnabled(false); // deshabilitado hasta validar
    }

    private void addListeners() {
        // Siempre habilitado
        view.getBtnBuscarDisponibilidad().addActionListener(
            e -> SwingUtil.exceptionWrapper(this::onComprobarEstado)
        );

        // Ejecuta reserva (validación completa dentro)
		addLoggedAction(view.getBtnReservar(), "Reserva externa realizada", () -> {
			Integer id = onReservar();
			if (id != null) {
				audit.log("INFO", "Reserva externa creada con id=" + id);
			}
		});

    }


    public void run() {
        view.setVisible(true);
    }

    private void onComprobarEstado() {
    	    InstalacionDTO instalacion = (InstalacionDTO) view.getInstalacionSelector().getSelectedItem();
    	    String fechaStr = view.getFechaSeleccionadaString().trim();

    	    if (instalacion == null) {
    	        throw new ApplicationException("Debe seleccionar una instalación.");
    	    }
    	    if (fechaStr.isEmpty())
    	        throw new ApplicationException("El campo de fecha no puede estar vacío.");
    	    if (!Util.isValidIsoDate(fechaStr))
    	        throw new ApplicationException("Formato de fecha incorrecto. Use AAAA-MM-DD.");

    	    fechaReservaSeleccionada = Util.isoStringToDate(fechaStr);

    	    List<HorarioOcupadoDTO> ocupados = reservaDAO.findHorariosOcupados(instalacion.getId(), fechaReservaSeleccionada);
    	    franjasEstado = generarEstadoPorSegmentos(ocupados, fechaReservaSeleccionada);
    	    List<OcupacionResumenDTO> resumen = generarResumenOcupacion(ocupados, fechaReservaSeleccionada);

    	    actualizarTablaResumen(resumen);

    	    if (!resumen.isEmpty()) {
    	        view.setEstadoDisponibilidadMensaje("¡ATENCIÓN! El día tiene horarios ocupados.", Color.ORANGE);
    	    } else {
    	        view.setEstadoDisponibilidadMensaje("¡DÍA TOTALMENTE LIBRE! Proceda con la reserva.", Color.GREEN.darker());
    	    }

    	    // Aquí NO se valida campos del usuario
    	    view.getBtnReservar().setEnabled(true); 
    	}


		private Integer onReservar() {
        ReservaDatos reservaDatos = validarYPrepararReserva();
        if (reservaDatos == null) {
            // Hubo errores, el JOptionPane ya se mostró en validarYPrepararReserva()
			return null; // no hacer nada más
        }
		Integer id = guardarReservaYMostrarResumen(reservaDatos);
		return id;
    }


    private ReservaDatos validarYPrepararReserva() {
        Date fechaDeReserva = new Date();
        String nombre = view.getNombreUsuario().trim();
        String telefono = view.getTelefono().trim();
        String direccion = view.getDireccion().trim();
        String email = view.getEmail().trim();
        String tarjeta = view.getNumTarjeta().trim();

        StringBuilder errores = new StringBuilder();

        // Validaciones
        if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
            errores.append("- Nombre inválido. Solo letras y espacios.\n");
        if (telefono.isEmpty() || !telefono.matches("\\+?\\d{9,15}"))
            errores.append("- Teléfono inválido. Solo números (9-15 dígitos).\n");
        if (direccion.isEmpty() || !direccion.matches("[\\w\\s.,#-]+"))
            errores.append("- Dirección inválida. No puede estar vacía y solo permite letras, números, ., -, #\n");
        if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            errores.append("- Email inválido. Debe tener un formato correcto.\n");
        if (tarjeta.length() != 16)
            errores.append("- La tarjeta debe tener 16 dígitos.\n");

        // Fecha y hora
        fechaReservaSeleccionada = Util.parseIsoDate(view.getFechaSeleccionadaString());
        if (fechaReservaSeleccionada == null)
            errores.append("- Debe pulsar 'Comprobar Estado' antes de reservar.\n");

        String horaInicioStr = view.getHoraInicioReservaSeleccionada();
        if (horaInicioStr == null || horaInicioStr.trim().isEmpty() || !Util.isValidTimeString(horaInicioStr))
            errores.append("- Debe seleccionar una hora de inicio válida (HH:mm).\n");

        int duracionMinutos = view.getDuracionEnMinutos();
        if (duracionMinutos < 60)
            errores.append("- Duración mínima 1 hora.\n");

        // Si hay errores, mostrar un único JOptionPane
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(view,
                    "Corrija los siguientes errores:\n" + errores.toString(),
                    "Errores en los datos", JOptionPane.ERROR_MESSAGE);
            return null; // detiene la reserva
        }

        // Validaciones de disponibilidad
        Date inicioReserva = Util.getFullDateTime(fechaReservaSeleccionada, horaInicioStr);
        Date finReserva = Util.addMinutes(inicioReserva, duracionMinutos);
        Date horaCierre = Util.getFullDateTime(fechaReservaSeleccionada, "22:00");

        if (inicioReserva.before(fechaDeReserva) || finReserva.after(horaCierre)
                || !validarDisponibilidad(inicioReserva, finReserva)) {
            JOptionPane.showMessageDialog(view, "Franja horaria no disponible o fuera de horario.",
                    "Error en la reserva", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Validar solapamientos con reservas existentes
        InstalacionDTO instalacion = (InstalacionDTO) view.getInstalacionSelector().getSelectedItem();
        List<HorarioOcupadoDTO> ocupados = reservaDAO.findHorariosOcupados(instalacion.getId(), fechaReservaSeleccionada);
        for (HorarioOcupadoDTO o : ocupados) {
            Date inicioO = Util.getFullDateTime(fechaReservaSeleccionada, o.getHora_inicio());
            Date finO = Util.getFullDateTime(fechaReservaSeleccionada, o.getHora_fin());
            if (inicioReserva.before(finO) && finReserva.after(inicioO)) {
                JOptionPane.showMessageDialog(view, "Ya existe una reserva en este horario.",
                        "Error en la reserva", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        double duracionHoras = duracionMinutos / 60.0;
        double precioTotal = Math.ceil(duracionHoras * 2) / 2.0 * PRECIO_POR_HORA;

        return new ReservaDatos(nombre, tarjeta, inicioReserva, finReserva, precioTotal);
    }


	private Integer guardarReservaYMostrarResumen(ReservaDatos datos) {
        InstalacionDTO instalacion = (InstalacionDTO) view.getInstalacionSelector().getSelectedItem();
        ReservaDTO reserva = new ReservaDTO(
                datos.nombre,
                datos.tarjeta,
                instalacion.getId(),
                fechaReservaSeleccionada,
                datos.inicioReserva,
                datos.finReserva,
                datos.precioTotal,
                instalacion.getNombre()
        );
		Integer idReserva = reservaDAO.insertReserva(reserva);

        Ingreso ingreso = new Ingreso(
        	    TipoIngreso.RESERVA_EXTERNA,   // <-- usamos el enum
        	    datos.nombre + " - " + instalacion.getNombre(),
        	    new Date(),
        	    datos.precioTotal
        	);

        new IngresoDAO().insertIngreso(ingreso);

        view.setEstadoDisponibilidadMensaje("¡Reserva completada! Precio: " + datos.precioTotal + "€", Color.BLUE);
        javax.swing.SwingUtilities.invokeLater(this::onComprobarEstado);

        VentanaResumenReserva resumen = new VentanaResumenReserva(view, reserva, view);
        resumen.setVisible(true);
        resumen.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                view.dispose();
            }
        });

		return idReserva;
    }

    void actualizarTablaResumen(List<OcupacionResumenDTO> resumen) {
        String[] props = { "inicioOcupado", "finOcupado", "tipo" };
        view.getTablaOcupacionResumen().setModel(SwingUtil.getTableModelFromPojos(resumen, props));
        view.getScrollPaneOcupacion().setVisible(!resumen.isEmpty());
        view.revalidate();
        view.repaint();
    }

    private boolean validarDisponibilidad(Date inicio, Date fin) {
        for (EstadoFranjaDTO franja : franjasEstado) {
            Date franjaInicio = Util.getFullDateTime(fechaReservaSeleccionada, franja.getHoraInicio());
            Date franjaFin = Util.getFullDateTime(fechaReservaSeleccionada, franja.getHoraFin());
            if (inicio.before(franjaFin) && fin.after(franjaInicio) && !franja.getEstado().equals("Libre")) {
                return false;
            }
        }
        return true;
    }

    private List<EstadoFranjaDTO> generarEstadoPorSegmentos(List<HorarioOcupadoDTO> ocupados, Date fecha) {
        List<EstadoFranjaDTO> resultado = new ArrayList<>();
        int duracionSegmento = 30;

        for (int h = 8; h < 22; h++) {
            for (int m = 0; m < 60; m += duracionSegmento) {
                String horaStr = String.format("%02d:%02d", h, m);
                Date segmentoInicio = Util.getFullDateTime(fecha, horaStr);
                Date segmentoFin = Util.addMinutes(segmentoInicio, duracionSegmento);

                EstadoFranjaDTO franja = new EstadoFranjaDTO();
                franja.setHoraInicio(Util.timeToTimeString(segmentoInicio));
                franja.setHoraFin(Util.timeToTimeString(segmentoFin));
                franja.setEstado("Libre");

                for (HorarioOcupadoDTO o : ocupados) {
                    if (o.getHora_inicio() == null || o.getHora_fin() == null || o.getHora_inicio().trim().isEmpty() || o.getHora_fin().trim().isEmpty())
                        continue;

                    Date oInicio = Util.getFullDateTime(fecha, o.getHora_inicio());
                    Date oFin = o.isEsEmpleado() ? Util.addMinutes(Util.getFullDateTime(fecha, o.getHora_fin()), MARGEN_MINUTOS)
                            : Util.getFullDateTime(fecha, o.getHora_fin());

                    if (segmentoInicio.before(oFin) && segmentoFin.after(oInicio)) {
                        franja.setEstado(o.isEsEmpleado() ? "Ocupado (Equipo)" : "Ocupado (Reserva)");
                        break;
                    }
                }

                resultado.add(franja);
            }
        }

        return resultado;
    }

    private List<OcupacionResumenDTO> generarResumenOcupacion(List<HorarioOcupadoDTO> ocupados, Date fecha) {
        List<OcupacionResumenDTO> resumen = new ArrayList<>();
        for (HorarioOcupadoDTO o : ocupados) {
            if (o.getHora_inicio() == null || o.getHora_fin() == null)
                continue;
            String tipo = o.isEsEmpleado() ? "Equipo" : "Reserva";
            Date inicio = Util.getFullDateTime(fecha, o.getHora_inicio());
            Date fin = Util.getFullDateTime(fecha, o.getHora_fin());

            resumen.add(new OcupacionResumenDTO(inicio, fin, tipo, o.isEsEmpleado()));
        }
        return resumen;
    }

    private static class ReservaDatos {
        String nombre;
        String tarjeta;
        Date inicioReserva;
        Date finReserva;
        double precioTotal;

        public ReservaDatos(String nombre, String tarjeta, Date inicioReserva, Date finReserva, double precioTotal) {
            this.nombre = nombre;
            this.tarjeta = tarjeta;
            this.inicioReserva = inicioReserva;
            this.finReserva = finReserva;
            this.precioTotal = precioTotal;
        }
    }
}
