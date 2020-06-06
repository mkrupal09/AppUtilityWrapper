package com.hb.utilities

import android.util.Patterns

object ValidationUtils {

    /**
     * This method is used to check email is valid or not
     * @param email
     */
    fun isValidEmail(email: CharSequence?): Boolean {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * This method is used to check mobileNumber is valid or not
     * @param mobileNumber
     */
    fun isValidPhoneNumber(mobileNumber: String?): Boolean {
        return mobileNumber != null && mobileNumber.length >= 7 &&
                mobileNumber.matches("\\d*".toRegex())
    }

}