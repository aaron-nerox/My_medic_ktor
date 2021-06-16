package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.PGDataBase
import tech.nerostarx.models.Doctor
import tech.nerostarx.models.Doctors

fun Route.configureDoctorRoutes(){
    route("/doctor"){
            get{
                val doctors = transaction(PGDataBase.dataBaseInstance){
                    Doctors.selectAll().map { toDoctor(it) }
                }
                call.respond(status = HttpStatusCode.OK, doctors)
            }
            get("{idDoc}"){
                val id = call.parameters["idDoc"] ?: return@get call
                    .respondText("Invalid id", status = HttpStatusCode.BadRequest)

                val doctor = transaction(PGDataBase.dataBaseInstance){
                    Doctors.select {
                        Doctors.idDoc eq id.toInt()
                    }.map { toDoctor(it) }
                }[0]

                call.respond(status = HttpStatusCode.OK,doctor)
            }
    }
}

fun toDoctor(row: ResultRow): Doctor{
    return Doctor(
        row[Doctors.idDoc],
        row[Doctors.docPicUrl],
        row[Doctors.docName],
        row[Doctors.docSurname],
        row[Doctors.docPhone],
        row[Doctors.docSpeciality],
        row[Doctors.startHour],
        row[Doctors.endHour],
        row[Doctors.docAdrLongi],
        row[Doctors.docAdrLati]
    )
}