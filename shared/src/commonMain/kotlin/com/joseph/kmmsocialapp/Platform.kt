package com.joseph.kmmsocialapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform