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

object AssociatesMedicamentTreat: Table(){
    val idAssociation : Column<Int> = integer("id_association").autoIncrement()
    val id_treatment: Column<Int> = integer("id_treatment")
    val id_medicament: Column<Int> = integer("id_medicament")
    override val primaryKey = PrimaryKey(idAssociation)
}
