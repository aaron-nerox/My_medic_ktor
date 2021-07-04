package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


@Serializable
data class Patient(
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String
)

object Patients: Table(){
    val idPatient: Column<Int> = integer("id").autoIncrement()
    val namePatient: Column<String> = text("name")
    val surnamePatient: Column<String> = text("surname")
    val phonePatient: Column<String> = text("phone")

    override val primaryKey = PrimaryKey(idPatient)
}