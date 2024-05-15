package com.iwex.mobilepartsshop.data.remote

import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.BASE_URL
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.MANUFACTURERS_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.PARTS_MAPPING_V1
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ApiUtils {

    companion object {

        fun getManufacturerLogoUrl(manufacturerId: Long): String {
            return "$BASE_URL$MANUFACTURERS_MAPPING_V1/$manufacturerId/logo"
        }

        fun getPartImageUrl(partId: Long): String {
            return "$BASE_URL$PARTS_MAPPING_V1/$partId/image"
        }

        fun stringToRequestBody(value: String) =
            value.toRequestBody("text/plain".toMediaTypeOrNull())

        fun stringsToRequestBody(values: List<String>) = values.map { stringToRequestBody(it) }

        fun intToRequestBody(value: Int) = stringToRequestBody("$value")

        fun longToRequestBody(value: Long) = stringToRequestBody("$value")

        fun doubleToRequestBody(value: Double) = stringToRequestBody("$value")

    }
}