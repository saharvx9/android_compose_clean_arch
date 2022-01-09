package com.sahar.composecleanarchitecture.ui.pages.login

enum class LoginFieldType {
    USER_NAME_EDIT_TEXT,
    PASSWORD_EDIT_TEXT
}

data class LoginFieldState(
    val input: String?,
    private var valid: Boolean = true,
    var error: String? = null
) {
    val isValid get() = valid

    init {
        when {
            input.isNullOrEmpty() -> {
                valid = false
                error = "Cannot be empty"
            }
            else -> {
                error = null
                valid = true
            }
        }
    }
}