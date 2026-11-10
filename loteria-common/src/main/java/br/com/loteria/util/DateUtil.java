package br.com.loteria.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

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

	public static String obterDiaDaSemana(LocalDate data) {
		if (data == null) {
			return null;
		}

		return data.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR"));
	}

}