package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.Advice
import tech.nerostarx.models.Advices


fun Application.configureAdviceRoutes(){
    routing {

    }
}

fun toAdvice(row: ResultRow):Advice{
    return Advice(
        row[Advices.idAdvice],
        row[Advices.idPatient],
        row[Advices.idDoc],
        row[Advices.adviceMessage]
    )
}