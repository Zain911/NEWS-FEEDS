package com.example.news_feeds.domain.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Util {
    fun dateFormat(date :String): String? {
        val dateTime = OffsetDateTime.parse(date)
        val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, uuuu", Locale.ENGLISH)
        return dateTimeFormatter.format(dateTime)
    }
}