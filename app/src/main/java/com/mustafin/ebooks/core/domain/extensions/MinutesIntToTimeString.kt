package com.mustafin.ebooks.core.domain.extensions

fun Int.mapMinutesToTimeString(): String {
    val hours = (this / 60).coerceAtLeast(0)
    val minutes = this % (24*60) % 60
    return "${hours}ч ${minutes}м"
}