package com.estudoandroid.testgx2sicredi.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormat {

    companion object {
        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
            return format.format(date)
        }
    }
}