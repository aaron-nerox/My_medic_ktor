package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.Doctor
import tech.nerostarx.models.Doctors

fun Application.configureDoctorRoutes(){
    routing {

    }
}

fun toDoctor(row: ResultRow): Doctor{
    return Doctor(
        row[Doctors.idDoc],
        row[Doctors.docName],
        row[Doctors.docSurname],
        row[Doctors.docPhone],
        row[Doctors.docSpeciality],
        row[Doctors.docAdrLongi],
        row[Doctors.docAdrLati]
    )
}