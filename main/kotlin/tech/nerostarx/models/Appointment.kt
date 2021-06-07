package tech.nerostarx.models

import kotlinx.serialization.Serializable


@Serializable
data class Appointment(
    val idApt: Int,
    val idDoc: Int,
    val idPatient: Int,
    val date: String,
)

