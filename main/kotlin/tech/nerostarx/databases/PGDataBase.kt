package tech.nerostarx.databases

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object PGDataBase {

    private val dataSourceConfig = HikariConfig().apply {
        jdbcUrl         = "jdbc:postgresql://localhost:5432/my_medic_db"
        driverClassName = "org.postgresql.Driver"
        username        = "nerostarx"
        password        = "TheNewWorld"
        maximumPoolSize = 10
    }

    private val datasource = HikariDataSource(dataSourceConfig)

    val dataBaseInstance by lazy{
        Database.connect(datasource)
    }
}