package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


@Serializable
data class Patient(
    val idPatient: Int,
    val namePatient: String,
    val surnamePatient: String,
    val phonePatient: String
)

object Patients: Table(){
    val idPatient: Column<Int> = integer("id_patient").autoIncrement()
    val namePatient: Column<String> = text("name_patient")
    val surnamePatient: Column<String> = text("surname_patient")
    val phonePatient: Column<String> = text("phone_patient")

    override val primaryKey = PrimaryKey(idPatient)
}