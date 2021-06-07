package tech.nerostarx.models

import kotlinx.serialization.Serializable


@Serializable
data class Treatment(
    val idTreatment: Int,
    val idDoc: Int,
    val idPatient: Int,
    val durationTreatment: String,
    val dateStartTreatment: String,
)
