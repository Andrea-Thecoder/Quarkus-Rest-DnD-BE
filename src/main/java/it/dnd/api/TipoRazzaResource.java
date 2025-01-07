package it.dnd.api;

import it.dnd.client.DndRestRazze;
import it.dnd.dto.DettaglioRazzaDTO;
import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.SimpleResultDTO;
import it.dnd.dto.SimplyInsertTypeDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.model.TipoClasse;
import it.dnd.model.TipoRazza;
import it.dnd.service.TipoRazzaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Tag(name = "API Tipo Razza")
@Path("tipo-razza")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoRazzaResource {

    @Inject
    TipoRazzaService tipoRazzaService;

    @POST
    @Operation(
            summary = "Inserimento di una nuova razza",
            description = "Permette l'inserimento di una nuova razza nel DB."
    )
    public TipoRazza createTipoRazza (
            @Valid SimplyInsertTypeDTO dto
    ){
        log.info("TipoRazzaResource - createTipoRazza");
        return tipoRazzaService.createTipoRazza(dto);
    }

    @GET
    @Operation(
            summary = "Recupera una lista con tutte le razze.",
            description = "Permette il recupero di tutti le razze inseriti nel DB. Restituisce una lista paginata ed accetta queryparams di ricerca"
    )
    public PagedResultDTO<TipoRazza> findTipoRazza(
            @BeanParam BaseSearch request){
        log.info("TipoRazzaResource - findTipoRazza");
        return tipoRazzaService.findRazze(request);
    }
    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Permette la modifa di una razza esistente",
            description = "Permette la modifica di una razza esistente nel DB, tramite il suo Id."
    )
    public SimpleResultDTO<Void> updateRazza(
            @PathParam("id") Long id,
            @Valid SimplyInsertTypeDTO dto
    ){
        log.info("TipoRazzaResource - updateRazza");
        tipoRazzaService.updateTipoRazza(id,dto);
        return SimpleResultDTO.<Void>builder().message("Tipo Razza aggiornato con successo.").build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Permette la cancellazione di un tipo di razza",
            description = "Permette la cancellazione dal DB di un tipo di razza"
    )
    public SimpleResultDTO<Long> deleteTipoRazza(
            @PathParam("id")Long id
    ){
        log.info("TipoRazzaResource - deleteTipoRazza");
        return SimpleResultDTO.<Long>builder()
                .payload(tipoRazzaService.deleteTipoRazza(id))
                .message("Razza cancellata con successo.")
                .build();
    }
}
