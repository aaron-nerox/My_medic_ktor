package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


@Serializable
data class Medicament(
    val idMedicament: Int,
    val nameMedicament: String,
    val timesPerDay: Int
)

object Medicaments: Table(){
    val idMedicament: Column<Int> = integer("id_medicament").autoIncrement()
    val nameMedicament: Column<String> = text("name_medicament")
    val timesPerDay: Column<Int> = integer("times_perday")

    override val primaryKey = PrimaryKey(idMedicament)

}
