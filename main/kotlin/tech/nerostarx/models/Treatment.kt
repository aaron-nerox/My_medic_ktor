package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


@Serializable
data class Treatment(
    val idTreatment: Int,
    val idDoc: Int,
    val idPatient: Int,
    val durationTreatment: String,
    val dateStartTreatment: String,
    var medicamentList: ArrayList<Medicament>
)

object Treatments: Table(){
    val idTreatment: Column<Int> = integer("id_treatment").autoIncrement()
    val idDoc: Column<Int> = integer("id_doc")
    val idPatient: Column<Int> = integer("id_patient")
    val durationTreatment: Column<String> = text("treat_duration")
    val dateStartTreatment: Column<String> = text("date_start_treat")

    override val primaryKey = PrimaryKey(idTreatment)
}
