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
                val principal = call.principal<UserIdPrincipal>()!!
                call.respondText("Hello ${principal.name}")
            }
        }
    }
}
