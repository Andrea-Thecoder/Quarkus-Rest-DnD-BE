package it.dnd.api;


import it.dnd.dto.spell.*;
import it.dnd.dto.SimpleResultDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.service.SpellBookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.UUID;


@Tag(name = "API Spellbook")
@Path("spellbook")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpellBookResource {

    @Inject
    SpellBookService spellBookService;

    @GET
    @Operation(
            summary = "Recupera la lista di tutti gli incantesimi del gioco.",
            description = "Permette il recupero, attraverso un Client Rest, di tutti gli icnantesimi del gioco."
    )
    public SpellResponseDTO<BasicSpellDTO> findSpells(
            @BeanParam BaseSearch request){
        log.info("SpellbookResource - findspells");
        return spellBookService.findSpells();
    }

    @GET
    @Path("{spellName}")
    @Operation(
            summary = "Recupera tutte le informazioni di un singolo incantesimo, ricercandolo per nome.",
            description = "Permette di recueprare, attraverso un Client REst, le informazioni in dettaglio di un incantesimo, ricercandolo per nome(in inglese)"
    )
    public DettaglioSpellResponseDTO getSpellbySpellName(
            @PathParam("spellName") String spellName
    ){
        log.info("SpellBookResource - GetSpellBySpellName");
        return spellBookService.getSpellDettaglioByName(spellName);
    }

    @GET
    @Path("/{idPersonaggio}/spellsbook")
    @Operation(
            summary = "Recupera la lista di tutti gli incantesimi appresi da un determinato personaggio.",
            description = "Permette di recueprare la lsita di tutti gli incantesimi appresi da un personaggio tramite il suo id."
    )
    public List<SpellPersonaggioDTO> getSpellBookByIdPersonaggio(
            @PathParam("idPersonaggio") UUID id
    ){
        log.info("SpellBookResource - getSpellBookByIdPersonaggio");
        return spellBookService.getSpellbooksByPersonaggioId(id);
    }

    @POST
    @Operation(
            summary = "Inserisce l'associazione dell'incantesimo al personaggio che l'ha appreso.",
            description = "Permette l'inserimento a DB dell'associazione dell'incantesimo ed il personaggio che l'ha appreso. Se l'incantesimo non è presente nel DB allora salva le informazioni di base nel DB."
    )
    public SpellPersonaggioDTO createSpellbook (
            @Valid InsertSpellBookDTO dto
            ){
        log.info("SpellbookResource - createSpell");
        return  spellBookService.createSpell(dto);
    }

    @DELETE
    @Path("/{spellName}/remove-from-player")
    @Operation(
            summary = "Cancella l'associazione tra l'incantesimo ed il personaggio",
            description ="Permette la cancellazione, a DB, dell'associazione tra il personaggio e l'incantesimo. Elimina anche i dati  dell'incantesimo dal DB se non ci sono altre associazioni per quell'incantesimo. "
    )
    public SimpleResultDTO<Void> removeSpellFromPersonaggio(
            @PathParam("spellName") String  spellName,
            @QueryParam("idPersonaggio") UUID idPg
    ){
        log.info("SpellbookResource - removeSpellFromPersonaggio");
        spellBookService.removeSpellFromPersonaggio(spellName,idPg);
        return SimpleResultDTO.<Void>builder().message("Spell rimossa dal personaggio con successo.").build();
    }
    

}
