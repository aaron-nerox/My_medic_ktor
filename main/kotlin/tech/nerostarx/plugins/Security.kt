package tech.nerostarx.plugins

import io.ktor.auth.*
import io.ktor.util.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureSecurity() {
    
    authentication {

    	    form(name = "auth") {
				//TODO: search the user from the database

    	        userParamName = "user"
    	        passwordParamName = "password"
    	        validate { credentials ->
					if(credentials.name == "aaron" && credentials.password == "pass"){
						UserIdPrincipal(credentials.name)
					}else{
						null
					}
				}
    	    }
    	}

    routing {
        authenticate("auth") {
            get("/auth/login") {
                val userPrincipal = call.principal<UserIdPrincipal>()

				if(userPrincipal != null){
					call.respondText("Hello ${userPrincipal.name}")
				}else{
					call.respondText("Password or username error")
				}

            }
        }
    }
}
