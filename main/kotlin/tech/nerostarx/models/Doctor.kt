package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

@Serializable
data class Doctor(
    val id: Int,
    val docPicUrl: String,
    val name: String,
    val surname: String,
    val phone: String,
    val docSpeciality: String,
    val startHour: String,
    val endHour: String,
    val docAdrLongi: String,
    val docAdrLati: String)


object Doctors: Table(){
    val idDoc: Column<Int> = integer("id").autoIncrement()
    val docPicUrl: Column<String> = text("doc_pic_url")
    val docName: Column<String> = text("name")
    val docSurname: Column<String> = text("surname")
    val docPhone: Column<String> = text("phone")
    val docSpeciality: Column<String> = text("doc_speciality")
    val startHour: Column<String> = text("start_hour")
    val endHour: Column<String> = text("end_hour")
    val docAdrLongi: Column<String> = text("doc_adr_longi")
    val docAdrLati: Column<String> = text("doc_adr_lati")

    override val primaryKey = PrimaryKey(idDoc)
}
