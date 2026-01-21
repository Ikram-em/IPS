package giis.demo.tkrun.partidos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import giis.demo.util.Database;

public class ParserPartidos {

	private static final String SQL_GET_NOMBRE_EQUIPO_INTERNO = "SELECT nombre FROM Equipo WHERE id_equipo = ?";

	private static final String SQL_GET_NOMBRE_INSTALACION = "SELECT nombre FROM Instalacion WHERE id = ?";

	private Database db = new Database();

	private String file;

	public ParserPartidos(String file) {
		this.db = new Database();
		this.file = file;
	}

	public List<PartidoDTO> parseCSV(String csvFile) {
	    List<PartidoDTO> partidos = new ArrayList<>();
	    String csvSeparator = ";";

	    // Formatters
	    DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter dmyFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            if (line.trim().isEmpty())
	                continue;

	            String[] datos = line.split(csvSeparator);
	            PartidoDTO partido = new PartidoDTO();
	            if (datos.length < 8) {
	                continue;
	            }
	            // Parse fecha: primero ISO, si falla prueba dd/MM/yyyy
	            String fechaStr = datos[0].trim();
	            LocalDate fecha;
	            try {
	                fecha = LocalDate.parse(fechaStr, isoFormatter);
	            } catch (DateTimeParseException e) {
	                fecha = LocalDate.parse(fechaStr, dmyFormatter);
	            }
	            partido.setFecha(fecha.toString()); // siempre en ISO para SQLite

	            // Parse horas
	            partido.setHora_inicio(LocalTime.parse(datos[1].trim()));
	            partido.setHora_fin(LocalTime.parse(datos[2].trim()));

	            // Equipo interno
	            int idEquipo = Integer.parseInt(datos[3].trim());
	            partido.setId_EquipoInterno(idEquipo);
	            partido.setNombreEquipoInterno(getNombreEquipo(String.valueOf(idEquipo)));

	            // Equipo externo
	            partido.setNombreEquipoExterno(datos[4].trim());
	            

	            // Suplemento SI/NO → boolean
	            String suplemento = datos[5].trim();
	            partido.setPriced(suplemento.equalsIgnoreCase("SI"));

	            int precio = Integer.parseInt(datos[6].trim());
	            partido.setPrecioSuplemento(precio);
	            // Instalación
	            int idInst = Integer.parseInt(datos[7].trim());
	            partido.setId_instalacion(idInst);
	            partido.setNombre_instalacion(getNombreInstalacion(String.valueOf(idInst)));

	            partidos.add(partido);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return partidos;
	}

	public List<PartidoDTO> parseJSON(String jsonFile) {
		List<PartidoDTO> partidos = new ArrayList<>();

		try {
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Map<String, Object>>>() {
			}.getType();

			// Leer archivo JSON
			String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFile)));

			// Convertir JSON a lista de mapas
			List<Map<String, Object>> lista = gson.fromJson(jsonContent, listType);

			for (Map<String, Object> item : lista) {
				PartidoDTO partido = new PartidoDTO();

				partido.setFecha((String) item.get("fecha"));
				partido.setHora_inicio(LocalTime.parse((String) item.get("hora_inicio")));
				partido.setHora_fin(LocalTime.parse((String) item.get("hora_final")));

				int idEquipo = ((Number) item.get("id_equipoInterno")).intValue();
				partido.setId_EquipoInterno(idEquipo);
				partido.setNombreEquipoInterno(getNombreEquipo(String.valueOf(idEquipo)));

				partido.setNombreEquipoExterno((String) item.get("NombreEquipoExterno"));

				// SI / NO → boolean
				String suplemento = (String) item.get("tiene_suplemento");
				partido.setPriced(suplemento.equalsIgnoreCase("SI"));
				
				int precio = ((Number) item.get("precio_suplemento")).intValue();
	            partido.setPrecioSuplemento(precio);

				int idInst = ((Number) item.get("id_instalacion")).intValue();
				partido.setId_instalacion(idInst);
				partido.setNombre_instalacion(getNombreInstalacion(String.valueOf(idInst)));

				partidos.add(partido);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return partidos;
	}

	private String getNombreInstalacion(String id) {
		try (Connection conn = db.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_GET_NOMBRE_INSTALACION)) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener nombre de la instalacion: " + e.getMessage());
		}
		return "no name";
	}

	private String getNombreEquipo(String id) {
		try (Connection conn = db.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_GET_NOMBRE_EQUIPO_INTERNO)) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener nombre del equipo: " + e.getMessage());
		}
		return "no name";
	}

	public static void main(String[] args) {
		ParserPartidos parser = new ParserPartidos("src/main/resources/partidos/NuevosPartidos.csv");
		List<PartidoDTO> partidos = parser.parseCSV(parser.file);

		System.out.println("Partidos del Archivo CSV");
		System.out.println("------------");
		for (PartidoDTO p : partidos) {
			System.out.println(p);
		}

		System.out.println();
		System.out.println("Partidos del Archivo JSON");
		System.out.println("------------");
		parser = new ParserPartidos("src/main/resources/partidos/NuevosPartidos.json");
		partidos = parser.parseJSON(parser.file);
		for (PartidoDTO p : partidos) {
			System.out.println(p);
		}
	}
}
