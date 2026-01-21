package giis.demo.tkrun.interviewSlot;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import giis.demo.ui.interviewSlot.InterviewSlotView.ConflictoFranja;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class MetodosEntrevista {

	private Database db;

	public MetodosEntrevista() {
		db = new Database();
	}

	private static final String SQL_GET_HORARIO_ENTRENAMIENTO = "SELECT e.hora_inicio, e.hora_fin "
			+ "FROM Entrenamiento e " + "JOIN Plantilla p ON e.id_equipo = p.id_equipo " + "WHERE p.id_jugador = ? "
			+ "AND e.fecha >= ? AND e.fecha < date(?, '+1 day')";

	private static final String SQL_GET_HORARIO_PARTIDO = "SELECT pa.hora_inicio, pa.hora_fin " + "FROM Partido pa "
			+ "JOIN Plantilla pl ON pa.id_equipo = pl.id_equipo "
			+ "WHERE pl.id_jugador = ? AND pa.fecha >= ? AND pa.fecha < date(?, '+1 day')";

	private static final String SQL_GET_FRANJA_ENTREVISTA = "SELECT hora_inicio, hora_fin FROM FranjaEntrevista WHERE id_jugador = ? AND fecha >= ? AND fecha < date(?, '+1 day')";

	private static final String SQL_INSERT_FRANJA = "INSERT INTO FranjaEntrevista (id_jugador, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?)";

	public Optional<String> getHorarioEntrenamiento(int idJugador, Date fecha) {
		String fechaIso = Util.dateToIsoString2(fecha);
		try (Connection conn = db.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_GET_HORARIO_ENTRENAMIENTO)) {

			ps.setInt(1, idJugador);
			ps.setString(2, fechaIso);
			ps.setString(3, fechaIso);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String hora = rs.getString("hora_inicio") + " - " + rs.getString("hora_fin");
					return Optional.of(hora);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al obtener horario de entrenamiento: " + e.getMessage());
		}

		return Optional.empty();
	}

	public Optional<String> getHorarioPartido(int idJugador, Date fecha) {
		String fechaIso = Util.dateToIsoString2(fecha);
		try (Connection conn = db.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_GET_HORARIO_PARTIDO)) {

			ps.setInt(1, idJugador);
			ps.setString(2, fechaIso);
			ps.setString(3, fechaIso);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String hora = rs.getString("hora_inicio") + " - " + rs.getString("hora_fin");
					return Optional.of(hora);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al obtener horario de partido: " + e.getMessage());
		}

		return Optional.empty();
	}

	public Optional<String> getFranjaEntrevista(int idJugador, Date fecha) {
		String fechaIso = Util.dateToIsoString2(fecha);
		try (Connection conn = db.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_GET_FRANJA_ENTREVISTA)) {

			ps.setInt(1, idJugador);
			ps.setString(2, fechaIso);
			ps.setString(3, fechaIso);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String hora = rs.getString("hora_inicio") + " - " + rs.getString("hora_fin");
					return Optional.of(hora);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al obtener franja de entrevista: " + e.getMessage());
		}

		return Optional.empty();
	}

	public ResultadoFranja crearFranja(int idJugador, Date fecha, String horaInicio, String horaFin) {
	    if (haySolapamientoEntrenamiento(idJugador, fecha, horaInicio, horaFin)) {
			return new ResultadoFranja(ConflictoFranja.ENTRENAMIENTO, null);
	    }

	    if (haySolapamientoPartido(idJugador, fecha, horaInicio, horaFin)) {
			return new ResultadoFranja(ConflictoFranja.PARTIDO, null);
	    }

	    if (haySolapamientoFranja(idJugador, fecha, horaInicio, horaFin)) {
			return new ResultadoFranja(ConflictoFranja.ENTREVISTA, null);
	    }

	    try (Connection conn = db.getConnection()) {
	        conn.setAutoCommit(false);

	        try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT_FRANJA)) {
	            ps.setInt(1, idJugador);
	            ps.setString(2, Util.dateToIsoString2(fecha));
	            ps.setString(3, horaInicio);
	            ps.setString(4, horaFin);

	            int rows = ps.executeUpdate();

				Integer idGenerado = null;

				if (rows > 0) {
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						idGenerado = rs.getInt(1);
					}
				}

	            conn.commit();

				// return rows > 0 ? ConflictoFranja.NINGUNO : ConflictoFranja.ERROR;

				return new ResultadoFranja(rows > 0 ? ConflictoFranja.NINGUNO : ConflictoFranja.ERROR, idGenerado);

	        } catch (SQLException e) {
	            conn.rollback();
	            System.err.println("Rollback ejecutado por error: " + e.getMessage());
				return new ResultadoFranja(ConflictoFranja.ERROR, null);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error general al crear franja: " + e.getMessage());
			return new ResultadoFranja(ConflictoFranja.ERROR, null);
	    }
	}

	private boolean haySolapamientoEntrenamiento(int idJugador, Date fecha, String horaInicio, String horaFin) {
	    String sql = "SELECT e.hora_inicio, e.hora_fin " +
	                 "FROM Entrenamiento e " +
	                 "JOIN Plantilla p ON e.id_equipo = p.id_equipo " +
	                 "WHERE p.id_jugador = ? AND e.fecha >= ? AND e.fecha < date(?, '+1 day')";

	    try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idJugador);
	        ps.setString(2, Util.dateToIsoString2(fecha));
	        ps.setString(3, Util.dateToIsoString2(fecha));
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                String inicio = rs.getString("hora_inicio");
	                String fin = rs.getString("hora_fin");
	                if (haySolapamiento(inicio, fin, horaInicio, horaFin)) {
	                    return true;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al comprobar solapamiento de entrenamiento: " + e.getMessage());
	    }

	    return false;
	}

	private boolean haySolapamientoPartido(int idJugador, Date fecha, String horaInicio, String horaFin) {
	    String sql = "SELECT pa.hora_inicio, pa.hora_fin " +
	                 "FROM Partido pa " +
	                 "JOIN Plantilla pl ON pa.id_equipo = pl.id_equipo " +
	                 "WHERE pl.id_jugador = ? AND pa.fecha >= ? AND pa.fecha < date(?, '+1 day')";

	    try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idJugador);
	        ps.setString(2, Util.dateToIsoString2(fecha));
	        ps.setString(3, Util.dateToIsoString2(fecha));

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                String inicio = rs.getString("hora_inicio");
	                String fin = rs.getString("hora_fin");
	                if (haySolapamiento(inicio, fin, horaInicio, horaFin)) {
	                    return true;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al comprobar solapamiento de partido: " + e.getMessage());
	    }

	    return false;
	}

	private boolean haySolapamientoFranja(int idJugador, Date fecha, String horaInicio, String horaFin) {
	    String sql = "SELECT hora_inicio, hora_fin FROM FranjaEntrevista " +
	                 "WHERE id_jugador = ? AND fecha >= ? AND fecha < date(?, '+1 day')";

	    try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, idJugador);
	        ps.setString(2, Util.dateToIsoString2(fecha));
	        ps.setString(3, Util.dateToIsoString2(fecha));

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                String inicio = rs.getString("hora_inicio");
	                String fin = rs.getString("hora_fin");
	                if (haySolapamiento(inicio, fin, horaInicio, horaFin)) {
	                    return true;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al comprobar solapamiento de entrevista: " + e.getMessage());
	    }

	    return false;
	}

	private boolean haySolapamiento(String inicio1, String fin1, String inicio2, String fin2) {
	    int ini1 = parseHoraToMinutos(inicio1);
	    int fin1m = parseHoraToMinutos(fin1);
	    int ini2 = parseHoraToMinutos(inicio2);
	    int fin2m = parseHoraToMinutos(fin2);

	    // Consideramos solapamiento solo si hay intersección estricta
	    return ini1 < fin2m && ini2 < fin1m;
	}


	private int parseHoraToMinutos(String hora) {
	    String[] partes = hora.trim().split(":");
	    int h = Integer.parseInt(partes[0]);
	    int m = Integer.parseInt(partes[1]);
	    return h * 60 + m;
	}

	public boolean borrarFranja(int idJugador, Date fecha) {
		String sql = "DELETE FROM FranjaEntrevista WHERE id_jugador = ? AND fecha >= ? AND fecha < date(?, '+1 day')";

		try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			// ✅ Conversión de la fecha a formato ISO (usando tu clase Util)
			String fechaIso = Util.dateToIsoString2(fecha);

			ps.setInt(1, idJugador);
			ps.setString(2, fechaIso);
			ps.setString(3, fechaIso);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			System.err.println("Error al borrar franja: " + e.getMessage());
			return false;
		}
	}

	public List<FranjaEntrevistaDTO> getFranjasDeJugador(int idJugador) {
		List<FranjaEntrevistaDTO> franjas = new ArrayList<>();

		String sql = "SELECT id_franja, id_jugador, fecha, hora_inicio, hora_fin, medio_comunicacion "
				+ "FROM FranjaEntrevista " + "WHERE id_jugador = ? " + "ORDER BY fecha, hora_inicio";

		try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idJugador);

			try (ResultSet rs = ps.executeQuery()) {
				boolean found = false;
				while (rs.next()) {
					found = true;
					FranjaEntrevistaDTO franja = new FranjaEntrevistaDTO(rs.getInt("id_franja"),
							rs.getInt("id_jugador"), new Date(Util.isoStringToDate2(rs.getString("fecha")).getTime()),
							rs.getString("hora_inicio"), rs.getString("hora_fin"), rs.getString("medio_comunicacion") // ←
																														// nuevo
																														// campo
					);
					franjas.add(franja);
				}
				if (!found) {
					System.out.println("No se encontraron franjas para el jugador con ID: " + idJugador);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al obtener franjas del jugador: " + e.getMessage());
		}

		return franjas;
	}

	public void asignarEntrevistaYEliminarRestantes(int idFranja, int idJugador, String medio) {
		// Asigna el medio a la franja seleccionada
		String sqlAsignar = "UPDATE FranjaEntrevista SET medio_comunicacion = ? WHERE id_franja = ?";

		// Elimina solo las otras franjas del mismo jugador y del mismo día
		String sqlEliminarMismoDiaJugador = "DELETE FROM FranjaEntrevista " + "WHERE id_jugador = ? "
				+ "AND fecha = (SELECT fecha FROM FranjaEntrevista WHERE id_franja = ?) " + "AND id_franja != ?";

		try (Connection conn = db.getConnection()) {
			conn.setAutoCommit(false);

			try (PreparedStatement psAsignar = conn.prepareStatement(sqlAsignar);
					PreparedStatement psEliminar = conn.prepareStatement(sqlEliminarMismoDiaJugador)) {

				// Asignar medio a la franja elegida
				psAsignar.setString(1, medio);
				psAsignar.setInt(2, idFranja);
				psAsignar.executeUpdate();

				// Eliminar solo otras franjas del mismo jugador en ese día
				psEliminar.setInt(1, idJugador);
				psEliminar.setInt(2, idFranja);
				psEliminar.setInt(3, idFranja);
				psEliminar.executeUpdate();

				conn.commit();

			} catch (SQLException e) {
				conn.rollback();
				System.err.println("Error al asignar entrevista y eliminar franjas: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
		}
	}

	public Optional<String> getEntrevistaAsignada(int idJugador, Date fecha) {
		String sql = "SELECT hora_inicio, hora_fin, medio_comunicacion " + "FROM FranjaEntrevista "
				+ "WHERE id_jugador = ? AND fecha >= ? AND fecha < date(?, '+1 day') "
				+ "AND medio_comunicacion IS NOT NULL AND medio_comunicacion != ''";

		String fechaIso = Util.dateToIsoString2(fecha);

		try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idJugador);
			ps.setString(2, fechaIso);
			ps.setString(3, fechaIso);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String hora = rs.getString("hora_inicio") + " - " + rs.getString("hora_fin");
					String medio = rs.getString("medio_comunicacion");
					return Optional.of("(" + medio + "):" + hora);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al obtener entrevista asignada: " + e.getMessage());
		}

		return Optional.empty();
	}

}
