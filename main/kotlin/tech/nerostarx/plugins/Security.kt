package tech.nerostarx.plugins

import io.ktor.auth.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.PGDataBase
import tech.nerostarx.models.AuthUser
import tech.nerostarx.models.Authusr
import tech.nerostarx.models.Doctors
import tech.nerostarx.models.Patients
import tech.nerostarx.routes.toDoctor
import tech.nerostarx.routes.toPatient

fun Application.configureSecurity() {
    
    authentication {

    	    form(name = "auth") {
    	        userParamName = "user"
    	        passwordParamName = "password"
    	        validate { credentials ->
					val user = transaction(PGDataBase.dataBaseInstance){
						AuthUser.select {
							AuthUser.userPhone eq credentials.name and(AuthUser.userPassword eq credentials.password)
						}.map{ toAuthUser(it) }
					}

					if (user.isEmpty()){
						return@validate null
					}else{
						val authUser = user[0]
						return@validate if(credentials.name == authUser.userPhone
							&& credentials.password == authUser.userPassword){
							UserIdPrincipal(authUser.userPhone)
						}else{
							null
						}
					}

				}
    	    }
    	}

    routing {
        authenticate("auth") {
            get("/auth/login") {
                val userPrincipal = call.principal<UserIdPrincipal>()
				if(userPrincipal != null){
					val authUser = transaction(PGDataBase.dataBaseInstance){
						AuthUser.select{
							AuthUser.userPhone eq userPrincipal.name
						}.map { toAuthUser(it) }
					}[0]

					if(authUser.userType == "patient"){
						val user = transaction(PGDataBase.dataBaseInstance){
							Patients.select {
								Patients.idPatient eq authUser.idUser
							}.map { toPatient(it) }
						}[0]
						call.respond(user)
					}else{
						val user = transaction(PGDataBase.dataBaseInstance){
							Doctors.select {
								Doctors.idDoc eq authUser.idUser
							}.map { toDoctor(it) }
						}[0]
						call.respond(user)
					}
				}else{
					call.respondText("Error: Password or phone number error")
				}

            }
        }
    }
}

fun toAuthUser(row: ResultRow): Authusr {
	return Authusr(
		row[AuthUser.idAuth],
		row[AuthUser.userPhone],
		row[AuthUser.userPassword],
		row[AuthUser.userType],
		row[AuthUser.idUser]
	)
}
