package com.techjava.marvelinfo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    DateUtils() {

    }

    /**
     * Converts String to Date
     *
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */

    public static Date convertStringToDate(String dateString, String format) throws ParseException {
        Date conversionDate = null;
        if (null != dateString) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            conversionDate = sdf.parse(dateString);
        }

        return conversionDate;
    }



    /**
     * Convert date to string.
     *
     * @param conversionDate
     *            the conversion date
     * @param format
     *            the format
     * @return the string
     */

    public static String convertDateToString(Date conversionDate, String format) {
        String dateString = null;
        if (null != conversionDate) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateString = sdf.format(conversionDate);
        }
        return dateString;
    }

    public static int compareDate(Date date1,Date date2){

        return date1.compareTo(date2);

    }

}
