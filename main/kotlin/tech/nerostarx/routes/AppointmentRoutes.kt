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
import tech.nerostarx.models.Appointment
import tech.nerostarx.models.Appointments

fun Route.configureAppointmentRoutes(){
    route("/appointment"){
        get("{idDoc}"){
            val id = call.parameters["idDoc"] ?: return@get call
                .respondText("Invalid id", status = HttpStatusCode.BadRequest)

            val appointments = transaction(PGDataBase.dataBaseInstance){
                Appointments.select {
                    Appointments.idApt eq id.toInt()
                }.map { toAppointment(it) }
            }

            call.respond(status = HttpStatusCode.OK, appointments)
        }

        post{
            val pendingAppointment = call.receive<Appointment>()

            val result = transaction(PGDataBase.dataBaseInstance){
                Appointments.insert {
                    it[idDoc] = pendingAppointment.idDoc
                    it[idPatient] = pendingAppointment.idPatient
                    it[date] = pendingAppointment.date
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

fun toAppointment(row: ResultRow):Appointment{
    return Appointment(
        row[Appointments.idApt],
        row[Appointments.idDoc],
        row[Appointments.idPatient],
        row[Appointments.date]
    )
}