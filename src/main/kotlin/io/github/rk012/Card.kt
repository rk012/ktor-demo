package io.github.rk012

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val title: String,
    val description: String,
    val date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
)
