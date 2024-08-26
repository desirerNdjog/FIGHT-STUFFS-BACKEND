package org.accenture.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author: desirejuniorndjog.
 * @created: 23/08/2024 : 23:19
 * @project: FLIGHTSTUFF
 */
public class DateUtils {

    protected DateUtils(){}

    /**
     * function map a string date with parttern dd/mm/yyyy to LocalDate object
     * @param date
     * @return
     */
    public static LocalDate stringDateToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
