package br.com.loteria.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtil {

	private DateUtil() { }
	
	public static LocalDate converterParaLocalDate(Date date) {
	    if (date == null) {
	        return null;
	    }

	    return date.toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDate();
	}

}