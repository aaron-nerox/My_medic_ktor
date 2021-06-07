package tech.nerostarx.plugins

import io.ktor.routing.*
import io.ktor.application.*
import tech.nerostarx.routes.configureAppointmentRoutes
import tech.nerostarx.routes.configureDoctorRoutes
import tech.nerostarx.routes.configureTreatmentRoutes

fun Application.configureRouting() {
    routing {
        configureAppointmentRoutes()
        configureDoctorRoutes()
        configureTreatmentRoutes()
    }
}
