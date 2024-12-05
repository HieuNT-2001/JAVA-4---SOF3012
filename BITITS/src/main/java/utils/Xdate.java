package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Xdate {
	/**
	 * Converts a given String to a Date object.
	 *
	 * @param dateStr the date string to be converted
	 * @param format  the expected format of the date string (e.g., "yyyy-MM-dd")
	 * @return the corresponding Date object, or null if parsing fails
	 */
	public static Date stringToDate(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
