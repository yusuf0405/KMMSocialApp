package com.joseph.kmmsocialapp.android.common.fake_data

import com.joseph.kmmsocialapp.android.common.extensions.addDays
import com.joseph.kmmsocialapp.android.common.extensions.formatAsFullyDayMonthYear
import com.joseph.kmmsocialapp.android.common.extensions.formatAsHoursMinutes
import java.util.Date
import java.util.Locale
import java.util.UUID

data class Message(
    val userId: String,
    val userImage: String,
    val releaseDate: String,
    val releaseTime: String,
    val message: String,
    val isYouMessage: Boolean
)

fun randomMassages() = listOf(
    "Alex, let’s meet this weekend. I’ll check\n" +
            "with Dave too \uD83D\uDE0E",
    "Sure. Let’s aim for saturday",
    "I’m visiting mom this sunday \uD83D\uDC7B",
    "Alrighty! Will give you a call shortly \uD83E\uDD17",
    "❤️",
    "Hey you! Are you there?",
    "\uD83D\uDC4B Hi Jess! What’s up?"
)

val sampleMessages = listOf(
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(1).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(1,5).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(1).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(1).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(1).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(1,4).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(0).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(0).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(0).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(0,4).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(0).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(0,).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(2).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(2,4).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(2).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(2).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(2).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(2,1).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(4).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(3,1).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(4).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(3).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(4).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(3,22).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(4).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(3,4).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(0).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(0).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(0,3).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(0,3).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(0).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(0).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = true
    ),
    Message(
        userImage = sampleAvatars.random(),
        userId = UUID.randomUUID().toString(),
        releaseDate = Date().addDays(2).formatAsFullyDayMonthYear().capitalize(Locale.ROOT),
        releaseTime = Date().addDays(2).formatAsHoursMinutes(),
        message = randomMassages().random(),
        isYouMessage = false
    ),
)