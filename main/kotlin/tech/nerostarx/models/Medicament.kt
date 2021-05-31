package tech.nerostarx.models

import kotlinx.serialization.Serializable


@Serializable
data class Medicament(
    val idMedicament: Int,
    val nameMedicament: String,
    val timesPerDay: Int
)
