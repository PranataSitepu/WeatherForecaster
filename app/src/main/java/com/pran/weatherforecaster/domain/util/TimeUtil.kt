package com.pran.weatherforecaster.domain.util

import java.text.SimpleDateFormat
import java.util.Date

object TimeUtil {

    const val FULL_DATE_FORMAT = "dd MMMM yyy"
    const val SHORT_DATE_FORMAT = "dd MMM yyy"
    const val SHORT_DAY_FORMAT = "EEE"

    fun parseTimestampToDate(timestamp: Long, pattern: String): String? {
        return try {
            val simpleDate = SimpleDateFormat(pattern)
            val netDate = Date(timestamp * 1000)
            simpleDate.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}
