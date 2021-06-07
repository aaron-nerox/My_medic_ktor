package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

@Serializable
data class Doctor(
    val idDoc: Int,
    val docName: String,
    val docSurname: String,
    val docPhone: String,
    val docSpeciality: String,
    val docAdrLongi: Long,
    val docAdrLati: Long)


object Doctors: Table(){
    val idDoc: Column<Int> = integer("id_doc").autoIncrement()
    val docName: Column<String> = text("doc_name")
    val docSurname: Column<String> = text("doc_surname")
    val docPhone: Column<String> = text("doc_phone")
    val docSpeciality: Column<String> = text("doc_speciality")
    val docAdrLongi: Column<String> = text("doc_adr_longi")
    val docAdrLati: Column<String> = text("doc_adr_lati")

    override val primaryKey = PrimaryKey(idDoc)
}
