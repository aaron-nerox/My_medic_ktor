package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import tech.nerostarx.databases.PGDataBase
import tech.nerostarx.models.*

fun Route.configureTreatmentRoutes(){
    route("/treatment"){
        get("/{id_patient}"){
            val idPatient = call.parameters["id_patient"] ?: return@get call
                .respondText("invalid id", status = HttpStatusCode.BadRequest)

            val treatments = transaction(PGDataBase.dataBaseInstance){
                Treatments.select {
                    Treatments.idPatient eq idPatient.toInt()
                }.map{ toTreatment(it)}
            }

            if(!treatments.isNullOrEmpty()){
                for (treatment in treatments){
                    val medicamentList = transaction(PGDataBase.dataBaseInstance){
                        AssociatesMedicamentTreat
                            .join(Medicaments, JoinType.FULL
                            ,additionalConstraint = {AssociatesMedicamentTreat.idMedicament eq Medicaments.idMedicament})
                            .selectAll().map{ toMedicament(it)}
                    }
                    treatment.medicamentList.addAll(medicamentList)
                }
            }

            call.respond(status = HttpStatusCode.OK, treatments)

        }
    }
}

fun toTreatment(row: ResultRow):Treatment{
    return Treatment(
        row[Treatments.idTreatment],
        row[Treatments.idDoc],
        row[Treatments.idPatient],
        row[Treatments.durationTreatment],
        row[Treatments.dateStartTreatment],
        ArrayList()
    )
}

fun toMedicament(row: ResultRow):Medicament{
    return Medicament(
        row[Medicaments.idMedicament],
        row[Medicaments.nameMedicament],
        row[Medicaments.timesPerDay]
    )
}

fun toMedicamentAssociation(row: ResultRow):AssociateMedicamentTreat{
    return AssociateMedicamentTreat(
        row[AssociatesMedicamentTreat.idAssociation],
        row[AssociatesMedicamentTreat.idTreatment],
        row[AssociatesMedicamentTreat.idMedicament]
    )
}