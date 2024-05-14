package com.iwex.mobilepartsshop.data.remote

class ApiConstants {

    companion object {

        private const val GLOBAL_MAPPING_V1 = "/api/v1"

        private const val PART_MAPPING_V1 = "$GLOBAL_MAPPING_V1/part"

        private const val USER_MAPPING_V1 = "$GLOBAL_MAPPING_V1/user"

        const val BASE_URL = "http://192.168.0.112:8080"

        const val AUTHENTICATION_MAPPING_V1 = "$GLOBAL_MAPPING_V1/authentication/authorize/customer"

        const val REGISTRATION_MAPPING_V1 = "$GLOBAL_MAPPING_V1/authentication/register"

        const val ORDERS_MAPPING_V1 = "$GLOBAL_MAPPING_V1/order/orders"

        const val DEVICE_TYPES_MAPPING_V1 = "$PART_MAPPING_V1/device-types"

        const val MANUFACTURERS_MAPPING_V1 = "$PART_MAPPING_V1/manufacturers"

        const val PART_TYPES_MAPPING_V1 = "$PART_MAPPING_V1/part-types"

        const val PARTS_MAPPING_V1 = "$PART_MAPPING_V1/parts"

        const val RECOMMENDATIONS_MAPPING_V1 = "$PART_MAPPING_V1/recommendations"

        const val REVIEWS_MAPPING_V1 = "$GLOBAL_MAPPING_V1/review/reviews"

        const val ADDRESSES_MAPPING_V1 = "$USER_MAPPING_V1/addresses"

        const val DEVICES_MAPPING_V1 = "$USER_MAPPING_V1/devices"
    }
}