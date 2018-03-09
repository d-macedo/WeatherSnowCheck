package com.weather.dmacedo.weathersnowcheck.app.internal

class CustomException : Exception() {

    var errorCode: Int = 0

    companion object {

        fun emit(errorCode: Int): CustomException {
            val exception = CustomException()
            exception.errorCode = errorCode
            return exception
        }
    }

}
