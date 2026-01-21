package giis.demo.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Utilidades generales de conversión y manipulación de fechas.
 */
public class Util {

    private Util() { throw new IllegalStateException("Utility class"); }

    /**
     * Convierte fecha representada como un string iso (AAAA-MM-DD) a fecha java (Date).
     */
    public static Date isoStringToDate(String isoDateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); 
            return sdf.parse(isoDateString.trim()); 
        } catch (ParseException e) {
            throw new ApplicationException("Formato de fecha incorrecto. Use DD-MM-YYYY");
        }
    }
    
    public static Date isoStringToDate2(String isoDateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); 
            return sdf.parse(isoDateString.trim()); 
        } catch (ParseException e) {
            throw new ApplicationException("Formato de fecha incorrecto. Use yyyy-MM-dd");
        }
    }
    
    /**
     * Convierte un Date a String formato yyyy-MM-dd.
     */
    public static String dateToIsoString(Date javaDate) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(javaDate);
    }
    
    /**
     * Convierte un Date a String formato yyyy-MM-dd.
     */
    public static String dateToIsoString2(Date javaDate) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(javaDate);
    }
    
    public static Date getFullDateTime(Date fecha, String horaStr) {
        if (fecha == null)
            throw new ApplicationException("La fecha base no puede ser nula al combinar fecha y hora.");
        if (horaStr == null || horaStr.trim().isEmpty())
            throw new ApplicationException("La hora no puede ser nula o vacía.");

        try {
            String fechaHoraStr = dateToIsoString(fecha) + " " + horaStr + ":00";
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraStr);
        } catch (ParseException e) {
            throw new ApplicationException("Formato de hora inválido. Use HH:MM .");
        }
    }

    /**
     * Valida que un string tenga formato de fecha ISO (AAAA-MM-DD).
     */
    public static boolean isValidIsoDate(String fechaStr) {
        try {
            isoStringToDate(fechaStr);
            return true;
        } catch (ApplicationException e) {
            return false;
        }
    }
    
    /**
     * Devuelve el día de la semana en formato texto (lunes, martes...).
     */
    public static String getDayOfWeek(Date fecha) {
		@SuppressWarnings("deprecation")
		Format formatter = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        return formatter.format(fecha).toLowerCase();
    }


    /**
     * Devuelve solo la parte de la hora en formato HH:mm.
     */
    public static String timeToTimeString(Date date) {
        Format formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    /**
     * Añade o resta minutos a un Date.
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * Devuelve el inicio del día (00:00:00) de una fecha dada.
     */
    public static Date getStartOfDay(Date date) {
        try {
            String isoDateStr = dateToIsoString(date);
            return isoStringToDate(isoDateStr); 
        } catch (Exception e) {
            throw new ApplicationException("Error al obtener el inicio del día.", e);
        }
    }

    /**
     * Convierte un string ISO (AAAA-MM-DD) a Date.
     */
    public static Date parseIsoDate(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty())
            return null;
        return isoStringToDate(fechaStr); 
    }

    /**
     * Valida que un string tenga formato de hora HH:mm
     */
    public static boolean isValidTimeString(String horaStr) {
        if (horaStr == null) return false;
        return horaStr.matches("^([01]\\d|2[0-3]):([0-5]\\d)$");
    }
    
    public static String dateTimeToIsoString(Date date) {
        if (date == null) {
            return null;
        }
        // Formato con fecha y HORA completa
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date); 
    }

    public static String dateTimeToString(Date fechaDeReserva) {
        if (fechaDeReserva == null) return null;
        // Formato: "YYYY-MM-DD HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fechaDeReserva);
    }

}