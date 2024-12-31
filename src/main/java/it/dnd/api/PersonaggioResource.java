package it.dnd.api;


import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.personaggio.DettaglioPersonaggioDTO;
import it.dnd.dto.personaggio.InsertPersonaggioDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.dto.spell.BasicSpellDTO;
import it.dnd.dto.spell.DettaglioSpellDTO;
import it.dnd.dto.spell.DettaglioSpellResponseDTO;
import it.dnd.dto.spell.SpellResponseDTO;
import it.dnd.service.PersonaggioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "API Personaggio")
@Path("Personaggio")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonaggioResource {

    @Inject
    PersonaggioService personaggioService;


    @POST
    public DettaglioPersonaggioDTO createPersonaggio (
            @Valid InsertPersonaggioDTO dto
            ){
        log.info("PersonaggioResource - CreatePersonaggio");
        return personaggioService.createPersonaggio(dto);
    }

    @GET
    public PagedResultDTO<DettaglioPersonaggioDTO> findPersonaggi(
            @BeanParam BaseSearch request){
        log.info("PersonaggioResource - findPersonaggi");
        return personaggioService.findPersonaggi(request);
    }

   /* @GET
    @Path("spell-by-name/{id}")
    public DettaglioSpellDTO getSpellByName(
            @PathParam("id") String id
    ){
        return personaggioService.getSpellById(id);
    }*/



}
