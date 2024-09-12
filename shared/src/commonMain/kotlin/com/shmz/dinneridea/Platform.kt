package com.shmz.dinneridea

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform