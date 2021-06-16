package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.PGDataBase
import tech.nerostarx.models.Appointment
import tech.nerostarx.models.Appointments
import tech.nerostarx.models.AptParams
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun Route.configureAppointmentRoutes(){
    route("/appointment"){
        post{
            val params = call.receive<AptParams>()
            val id = params.idDoc ?: return@post call
                .respondText("Invalid id", status = HttpStatusCode.BadRequest)
            val date = params.date ?: return@post call
                .respondText("Invalid date", status = HttpStatusCode.BadRequest)

            val appointments = transaction(PGDataBase.dataBaseInstance){
                Appointments.select {
                    Appointments.idDoc eq id and(Appointments.date eq date)
                }.map { toAppointment(it) }
            }

            call.respond(status = HttpStatusCode.OK, appointments)
        }

        post("/add"){
            val pendingAppointment = call.receive<Appointment>()

            val formatPattern = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val hourFormatPattern = DateTimeFormatter.ofPattern("HH:MM")
            val calendar = LocalDateTime.now()
            val currentDate = calendar.format(formatPattern)

            val currentTime = calendar.format(hourFormatPattern)

            val result = transaction(PGDataBase.dataBaseInstance){
                Appointments.insert {
                    it[idDoc] = pendingAppointment.idDoc
                    it[idPatient] = pendingAppointment.idPatient
                    it[date] = currentDate
                    it[hour] = currentTime
                }.resultedValues
            }

            if(result != null){
                call.respond(HttpStatusCode.Created, result.map { toAppointment(it) })
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
        row[Appointments.date],
        row[Appointments.hour]
    )
}