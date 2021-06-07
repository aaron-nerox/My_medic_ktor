package tech.nerostarx.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


@Serializable
data class Advice(
    val idAdvice: Int,
    val idPatient: Int,
    val idDoc: Int,
    val AdviceMessage: String
)

object Advices: Table(){
    val idAdvice: Column<Int> = integer("id_advice").autoIncrement()
    val idPatient: Column<Int> = integer("id_patient")
    val idDoc: Column<Int> = integer("id_doctor")
    val adviceMessage: Column<String> = text("advice_message")

    override val primaryKey = PrimaryKey(idAdvice)
}