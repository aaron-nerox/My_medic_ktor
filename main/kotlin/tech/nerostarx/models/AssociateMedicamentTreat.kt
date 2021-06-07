package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


@Serializable
data class AssociateMedicamentTreat(
    val idAssociation: Int,
    val idTreatment: Int,
    val idMedicament: Int
)
