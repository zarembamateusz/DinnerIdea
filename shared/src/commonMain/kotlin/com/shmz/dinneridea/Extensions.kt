package com.shmz.dinneridea

import kotlin.math.abs

fun String.asInt(): Int? =
    if(this.isEmpty()) {
        0
    } else {
        try {
            abs(toInt())
        } catch (e: Exception) {
            null
        }
    }