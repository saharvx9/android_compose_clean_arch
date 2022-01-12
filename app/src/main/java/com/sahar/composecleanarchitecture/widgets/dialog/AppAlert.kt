package com.sahar.composecleanarchitecture.widgets.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Task
import androidx.compose.ui.graphics.vector.ImageVector
import com.sahar.composecleanarchitecture.R


/**
 * AppAlert
 *
 * Generate alert dialog data can be full customise
 */
sealed class AppAlert {

    var icon: ImageVector = Icons.Rounded.Home

    @StringRes
    var title: Int? = null

    @StringRes
    var subtitle: Int? = null

    @StringRes
    var positiveButtonTitle: Int

    @StringRes
    var negativeButtonTitle: Int
    var isNegativeButton: Boolean = false
    var cancelable: Boolean = true


    var titleStr: String? = null
    var subtitleStr: String? = null


    constructor(
        icon: ImageVector = Icons.Rounded.Home,
        @StringRes
        title: Int = -1,
        @StringRes
        subtitle: Int = 0,
        @StringRes
        positiveButtonTitle: Int = R.string.dialog_module_ok_button,
        @StringRes
        negativeButtonTitle: Int = R.string.dialog_module_dismiss_button,
        isNegativeButton: Boolean = false,
        cancelable: Boolean = true
    ) {
        this.icon = icon
        this.title = title
        this.subtitle = subtitle
        this.positiveButtonTitle = positiveButtonTitle
        this.isNegativeButton = isNegativeButton
        this.negativeButtonTitle = negativeButtonTitle
        this.cancelable = cancelable
    }

    constructor(
        icon: ImageVector = Icons.Rounded.Home,
        title: String? = null,
        subtitle: String? = null,
        @StringRes
        positiveButtonTitle: Int = R.string.dialog_module_ok_button,
        @StringRes
        negativeButtonTitle: Int = R.string.dialog_module_dismiss_button,
        isNegativeButton: Boolean = false,
        cancelable: Boolean = true,
    ) {
        this.icon = icon
        this.positiveButtonTitle = positiveButtonTitle
        this.negativeButtonTitle = negativeButtonTitle
        this.isNegativeButton = isNegativeButton
        this.cancelable = cancelable
        this.titleStr = title
        this.subtitleStr = subtitle
    }

    constructor(
        icon: ImageVector = Icons.Rounded.Home,
        @StringRes
        title: Int = -1,
        @StringRes
        positiveButtonTitle: Int = R.string.dialog_module_ok_button,
        @StringRes
        negativeButtonTitle: Int = R.string.dialog_module_dismiss_button,
        isNegativeButton: Boolean = false,
        cancelable: Boolean = true,
        subtitle: String?
    ) {
        this.icon = icon
        this.title = title
        this.positiveButtonTitle = positiveButtonTitle
        this.negativeButtonTitle = negativeButtonTitle
        this.isNegativeButton = isNegativeButton
        this.cancelable = cancelable
        this.subtitleStr = subtitle
    }


    class ErrorDialog : AppAlert(
        icon = Icons.Rounded.Error,
        title = R.string.dialog_module_general_error_title,
        subtitle = R.string.dialog_module_general_error_subtitle,
    )

    class ErrorLoadArticles(message: String) : AppAlert(
        icon = Icons.Rounded.Error,
        title = R.string.dialog_module_general_error_title,
        subtitle = message,
    )

    class DoTaskDialog(subtitle: String) : AppAlert(
        icon = Icons.Rounded.Task,
        title = R.string.dialog_module_general_error_title,
        subtitle = subtitle,
    )
}