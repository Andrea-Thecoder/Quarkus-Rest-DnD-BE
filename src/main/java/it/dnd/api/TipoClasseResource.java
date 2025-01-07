package it.dnd.api;


import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.SimpleResultDTO;
import it.dnd.dto.SimplyInsertTypeDTO;
import it.dnd.dto.personaggio.DettaglioPersonaggioDTO;
import it.dnd.dto.personaggio.InsertPersonaggioDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.model.TipoClasse;
import it.dnd.service.TipoClasseServices;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.UUID;

@Tag(name = "API Tipo Classe")
@Path("tipo-classe")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoClasseResource {

    @Inject
    TipoClasseServices tipoClasseServices;

    @POST
    @Operation(
            summary = "Inserimento di una nuova classe",
            description = "Permette l'inserimento di una nuova classe nel DB."
    )
    public TipoClasse createTipoClasse (
            @Valid SimplyInsertTypeDTO dto
    ){
        log.info("TipoClasseResource - createTipoClasse");
        return tipoClasseServices.createTipoClasse(dto);
    }

    @GET
    @Operation(
            summary = "Recupera una lista con tutti i tipi di classe.",
            description = "Permette il recupero di tutti i tipi di classe inseriti nel DB. Restituisce una lista paginata ed accetta queryparams di ricerca"
    )
    public PagedResultDTO<TipoClasse> findTipoClassi(
            @BeanParam BaseSearch request){
        log.info("TipoClasseResource - findTipoClassi");
        return tipoClasseServices.findClassi(request);
    }
    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Permette la modifa di una Classe esistente",
            description = "Permette la modifica di una Classe esistente nel DB, tramite il suo Id."
    )
    public SimpleResultDTO<Void> updateTipoClasse(
            @PathParam("id") Long id,
            @Valid SimplyInsertTypeDTO dto
    ){
        log.info("TipoClasseResource - updateTipoClasse");
        tipoClasseServices.updateTipoClasse(id,dto);
        return SimpleResultDTO.<Void>builder().message("Tipo Classe aggiornato con successo.").build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Permette la cancellazione di un tipo di classe",
            description = "Permette la cancellazione dal DB di un tipo di classe"
    )
    public SimpleResultDTO<Long> deleteTipoClasse(
            @PathParam("id")Long id
    ){
        log.info("TipoClasseResource - deleteTipoClasse");
        return SimpleResultDTO.<Long>builder()
                .payload(tipoClasseServices.deleteTipoClasse(id))
                .message("Classe cancellata con successo.")
                .build();
    }
}
