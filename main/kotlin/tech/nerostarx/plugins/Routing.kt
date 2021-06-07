package tech.nerostarx.plugins

import io.ktor.routing.*
import io.ktor.application.*
import tech.nerostarx.routes.*

fun Application.configureRouting() {
    routing {
        configureDoctorRoutes()
        configurePatientRoutes()
        configureAppointmentRoutes()
        configureTreatmentRoutes()
        configureAdviceRoutes()
    }
}
