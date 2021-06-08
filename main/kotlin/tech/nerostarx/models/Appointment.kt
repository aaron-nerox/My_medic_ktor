package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


@Serializable
data class Appointment(
    var idApt: Int? = null,
    val idDoc: Int,
    val idPatient: Int,
    val date: String,
)

object Appointments: Table() {
    val idApt: Column<Int> = integer("id_apt").autoIncrement()
    val idDoc: Column<Int> = integer("id_doctor")
    val idPatient: Column<Int> = integer("id_patient")
    val date: Column<String> = text("apt_date")

    override val primaryKey = PrimaryKey(idApt)
}

