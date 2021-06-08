package tech.nerostarx.routes


import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.PGDataBase
import tech.nerostarx.models.Advice
import tech.nerostarx.models.Advices


fun Route.configureAdviceRoutes(){
    route("/advice"){
        get("{idPatient}"){
            val id = call.parameters["idPatient"] ?: return@get call
                .respondText("Invalid id", status = HttpStatusCode.BadRequest)

            val advices = transaction(PGDataBase.dataBaseInstance){
                Advices.select {
                    Advices.idPatient eq id.toInt()
                }.map { toAdvice(it) }
            }

            call.respond(HttpStatusCode.OK, advices)
        }

        post {
            val pendingAdvice = call.receive<Advice>()

            val result = transaction(PGDataBase.dataBaseInstance){
                Advices.insert {
                    it[idDoc] = pendingAdvice.idDoc
                    it[idPatient] = pendingAdvice.idPatient
                    it[adviceMessage] = pendingAdvice.AdviceMessage
                }.resultedValues
            }

            if(result != null){
                call.respond(HttpStatusCode.Created, "sucess created")
            }else{
                call.respond(HttpStatusCode.InternalServerError, "failed not created")
            }
        }
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