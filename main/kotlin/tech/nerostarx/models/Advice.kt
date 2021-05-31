package tech.nerostarx.models

import kotlinx.serialization.Serializable


@Serializable
data class Advice(
    val idAdvice: Int,
    val idPatient: Int,
    val idDoc: Int,
    val AdviceMessage: String
)