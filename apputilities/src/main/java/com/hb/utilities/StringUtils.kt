package com.hb.utilities

import java.util.regex.Pattern

object StringUtils {

    /**
     * This method is used to Capitalize the given string
     * @param capString
     */

    fun capitalize(capString: String?): String? {
        val capBuffer = StringBuffer()
        val capMatcher =
            Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE)
                .matcher(capString)
        while (capMatcher.find()) {
            capMatcher.appendReplacement(
                capBuffer,
                capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase()
            )
        }
        return capMatcher.appendTail(capBuffer).toString()
    }

    /**
     * This method is used to check String is empty or not
     * @param data
     */

    fun isEmpty(data: String?): Boolean {
        return data == null || data.trim { it <= ' ' }.isEmpty()
    }

}