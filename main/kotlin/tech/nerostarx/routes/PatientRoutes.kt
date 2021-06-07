package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.Patient
import tech.nerostarx.models.Patients

fun Application.configurePatientRoutes(){
    routing {

    }
}

fun toPatient(row: ResultRow):Patient{
    return Patient(
        row[Patients.idPatient],
        row[Patients.namePatient],
        row[Patients.surnamePatient],
        row[Patients.phonePatient]
    )
}