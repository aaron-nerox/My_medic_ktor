package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.Appointment
import tech.nerostarx.models.Appointments

fun Application.configureAppointmentRoutes(){
    routing {

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