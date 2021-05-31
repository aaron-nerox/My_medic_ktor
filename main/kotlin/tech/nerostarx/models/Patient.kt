package tech.nerostarx.models

import kotlinx.serialization.Serializable


@Serializable
data class Patient(
    val idPatient: Int,
    val namePatient: String,
    val surnamePatient: String,
    val phonePatient: String,
    val patientPassword: String
)