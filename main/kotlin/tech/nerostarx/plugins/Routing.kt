package tech.nerostarx.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.request.*
import tech.nerostarx.routes.configureAdviceRoutes
import tech.nerostarx.routes.configureAppointmentRoutes
import tech.nerostarx.routes.configureDoctorRoutes
import tech.nerostarx.routes.configureTreatmentRoutes

fun Application.configureRouting() {
    routing {
        configureAdviceRoutes()
        configureAppointmentRoutes()
        configureDoctorRoutes()
        configureTreatmentRoutes()
    }
}
