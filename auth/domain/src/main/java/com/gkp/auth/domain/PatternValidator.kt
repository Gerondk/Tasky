package com.gkp.auth.domain

interface PatternValidator {
    fun validate(value: String): Boolean
}