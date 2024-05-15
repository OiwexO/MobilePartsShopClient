package com.iwex.mobilepartsshop.presentation.fragment.profile.device

import androidx.annotation.StringRes

data class EditDeviceFormState(
    val isDataValid: Boolean = false,
    @StringRes val manufacturerError: Int? = null,
    @StringRes val deviceTypeError: Int? = null,
    @StringRes val modelError: Int? = null,
    @StringRes val specificationsError: Int? = null,
)
