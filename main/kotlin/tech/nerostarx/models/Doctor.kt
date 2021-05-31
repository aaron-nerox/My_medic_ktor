package tech.nerostarx.models

import kotlinx.serialization.Serializable

@Serializable
data class Doctor(
    val idDoc: Int,
    val docName: String,
    val docSurname: String,
    val docPhone: String,
    val docSpeciality: String,
    val docAdrLongi: Long,
    val docAdrLati: Long)
