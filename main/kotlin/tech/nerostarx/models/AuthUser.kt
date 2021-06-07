package tech.nerostarx.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object AuthUser: Table() {
    val idAuth: Column<Int> = integer("id_auth").autoIncrement()
    val userPhone: Column<String> = text("user_phone")
    val userPassword: Column<String> = text("user_password")
    val userType: Column<String> = text("user_type")
    val idUser: Column<Int> = integer("id_user")

    override val primaryKey = PrimaryKey(idAuth)
}