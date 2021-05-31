package tech.nerostarx.databases

import org.jetbrains.exposed.sql.Database

object PGDataBase {

    val dataBaseInstance by lazy{
        Database.connect("jdbc:postgresql://localhost:5432/kotlin_db"
            , "org.postgresql.Driver"
            , "nerostarx"
            , "TheNewWorld")
    }
}