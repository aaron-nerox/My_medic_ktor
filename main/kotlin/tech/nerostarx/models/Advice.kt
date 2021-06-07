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