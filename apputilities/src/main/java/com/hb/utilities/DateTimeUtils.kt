package com.hb.utilities

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    /**
     * This method is used to get the date string of one format from
     * another format date string
     *
     * @param dateString - Input date value
     * @param dateFormat - Input date value format
     * @param requiredDateFormat - Required date format
     * @param setTimeZone - boolean value to set the timezone to date or not
     * @param timeZone - timezone to add the date value
     * @return
     */

    fun getDateStringFromDateString(
        dateString: String?,
        dateFormat: String?,
        requiredDateFormat: String?,
        setTimeZone: Boolean,
        timeZone: String?
    ): String? {
        var date: String? = null
        var sdf = SimpleDateFormat(dateFormat)
        if (setTimeZone) sdf.timeZone = TimeZone.getTimeZone(timeZone)
        try {
            val newDate = sdf.parse(dateString)
            sdf = SimpleDateFormat(requiredDateFormat)
            date = sdf.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }


    /**
     * This method is used to get the date string of one format from
     * another format date string with Locale
     *
     * @param dateString - Input date value
     * @param dateFormat - Input date value format
     * @param requiredDateFormat - Required date format
     * @param setTimeZone - boolean value to set the timezone to date or not
     * @param timeZone - timezone to add the date value
     * @param locale - Input locale value
     * @param requiredLocale - Output or required date locale value
     * @return
     */

    fun getDateStringFromDateStringWithLocale(
        dateString: String?,
        dateFormat: String?,
        requiredDateFormat: String?,
        setTimeZone: Boolean,
        timeZone: String?,
        locale: Locale?,
        requiredLocale: Locale?
    ): String? {
        var date: String? = null
        var sdf = SimpleDateFormat(dateFormat, locale)
        if (setTimeZone) sdf.timeZone = TimeZone.getTimeZone(timeZone)
        try {
            val newDate = sdf.parse(dateString)
            sdf =
                requiredLocale?.let { SimpleDateFormat(requiredDateFormat, it) }
                    ?: SimpleDateFormat(requiredDateFormat)
            date = sdf.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    /**
     * This method is used to get the date string
     *
     * @param date - Input date value
     * @param requiredDateFormat - Required date format
     * @return
     */

    fun getDateStringFromDate(
        date: Long,
        requiredDateFormat: String?
    ): String? {
        val newDate = Date(date)
        val sm = SimpleDateFormat(requiredDateFormat)
        return sm.format(newDate)
    }

    /**
     * This method is used to get the current date time
     *
     * @param requiredDateFormat - Required date format
     * @return
     */

    fun getCurrentDateTime(
        requiredDateFormat: String?
    ): String? {
        val df: DateFormat = SimpleDateFormat(requiredDateFormat)
        return df.format(Calendar.getInstance().time)
    }


    /**
     * This method is used to check whether both dates are from same month or not
     *
     * @param dateFormat - date format to check dates
     * @param monthOne - value of first date to check
     * @param monthTwo - value of second date to check
     * @return
     */

    fun isSameMonthDates(
        dateFormat: String?,
        monthOne: String?,
        monthTwo: String?
    ): Boolean {
        var start: Date? = null
        var end: Date? = null
        try {
            start = SimpleDateFormat(dateFormat)
                .parse(monthOne)
            end = SimpleDateFormat(dateFormat)
                .parse(monthTwo)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = start
        cal2.time = end
        val sameMonth =
            cal1[Calendar.MONTH] == cal2[Calendar.MONTH]
        return sameMonth
    }

    /**
     * This method is used to check whether both dates are from same year or not
     *
     * @param dateFormat - date format to check dates
     * @param yearOne - value of first date to check
     * @param yearTwo - value of second date to check
     * @return
     */

    fun isSameYearDates(
        dateFormat: String?,
        yearOne: String?,
        yearTwo: String?
    ): Boolean {
        var start: Date? = null
        var end: Date? = null
        try {
            start = SimpleDateFormat(dateFormat)
                .parse(yearOne)
            end = SimpleDateFormat(dateFormat)
                .parse(yearTwo)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = start
        cal2.time = end
        val sameYear =
            cal1[Calendar.YEAR] == cal2[Calendar.YEAR]
        return sameYear
    }

}