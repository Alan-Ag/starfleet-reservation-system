package fr.starfleet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    // Convertit une Date en String formaté (ex. : "27/03/2025")
    public static String formatDate(Date date) {
        if (date == null) {
            return "Non défini";
        }
        return DATE_FORMAT.format(date);
    }

    // Parse une String en Date (ex. : "27/03/2025" -> Date)
    public static Date parseDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new ParseException("La date ne peut pas être vide", 0);
        }
        return DATE_FORMAT.parse(dateStr);
    }

    // Vérifie si une date est dans le futur
    public static boolean isFutureDate(Date date) {
        if (date == null) {
            return false;
        }
        return date.after(new Date());
    }

    // Calcule la différence en jours entre deux dates
    public static long differenceInDays(Date start, Date end) {
        if (start == null || end == null) {
            return 0;
        }
        long diffInMillies = end.getTime() - start.getTime();
        return diffInMillies / (1000 * 60 * 60 * 24);
    }
}