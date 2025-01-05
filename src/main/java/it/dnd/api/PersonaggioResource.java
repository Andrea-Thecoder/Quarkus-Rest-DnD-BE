package it.dnd.api;


import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.SimpleResultDTO;
import it.dnd.dto.personaggio.DettaglioPersonaggioDTO;
import it.dnd.dto.personaggio.InsertPersonaggioDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.service.PersonaggioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.UUID;

@Tag(name = "API Personaggio")
@Path("Personaggio")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonaggioResource {

    @Inject
    PersonaggioService personaggioService;


    @POST
    @Operation(
            summary = "Inserimento di un nuovo personaggio",
            description = "Permette l'inserimento di un nuovo personaggio nel DB."
    )
    public DettaglioPersonaggioDTO createPersonaggio (
            @Valid InsertPersonaggioDTO dto
            ){
        log.info("PersonaggioResource - CreatePersonaggio");
        return personaggioService.createPersonaggio(dto);
    }

    @GET
    @Operation(
            summary = "Recupera una lista con tutti i personaggi esistenti.",
            description = "Permette il recupero di tutti i personaggi inseriti nel DB. Restitusice una lista Paginata ed accetta queryparams di ricerca"
    )
    public PagedResultDTO<DettaglioPersonaggioDTO> findPersonaggi(
            @BeanParam BaseSearch request){
        log.info("PersonaggioResource - findPersonaggi");
        return personaggioService.findPersonaggi(request);
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Recupera i dati del singolo personaggio ricercandolo per ID.",
            description = "Permette il recupero del singolo personaggio in base al suo ID."
    )
    public DettaglioPersonaggioDTO getPersonaggioById(
            @PathParam("id")UUID id
            ){
        log.info("PersonaggioResource - getPersonaggioById");
        return personaggioService.getPersonaggioById(id);
    }

    @DELETE
    @Path("/{id}")
    public SimpleResultDTO<UUID> deletePersonaggio(
            @PathParam("id")UUID id
    ){
        return SimpleResultDTO.<UUID>builder()
                .payload(personaggioService.deletePersonaggio(id))
                .message("Cancellato con successo.")
                .build();
    }


}
