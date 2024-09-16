package com.gkp.auth.data

import android.util.Patterns
import com.gkp.auth.domain.PatternValidator

object EmailValidator : PatternValidator {
    override fun validate(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}