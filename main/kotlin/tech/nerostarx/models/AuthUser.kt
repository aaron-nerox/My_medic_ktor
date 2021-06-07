package tech.nerostarx.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


data class Authusr(
    val idAuth: Int,
    val userPhone: String,
    val userPassword: String,
    val userType: String,
    val idUser:Int
)


object AuthUser: Table("AuthUser") {
    val idAuth: Column<Int> = integer("id_auth").autoIncrement()
    val userPhone: Column<String> = text("user_phone")
    val userPassword: Column<String> = text("user_password")
    val userType: Column<String> = text("user_type")
    val idUser: Column<Int> = integer("id_user")

    override val primaryKey = PrimaryKey(idAuth)
}