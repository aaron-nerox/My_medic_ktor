package tech.nerostarx.routes

import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.ResultRow
import tech.nerostarx.models.*

fun Application.configureTreatmentRoutes(){
    routing {

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