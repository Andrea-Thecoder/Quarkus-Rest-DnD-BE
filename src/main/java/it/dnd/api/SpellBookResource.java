package it.dnd.api;


import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.dto.spell.BasicSpellDTO;
import it.dnd.dto.spell.SpellResponseDTO;
import it.dnd.service.SpellBookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;


@Tag(name = "API Spellbook")
@Path("spellbook")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpellBookResource {

    @Inject
    SpellBookService spellBookService;

    @GET
    public RestResponse<SpellResponseDTO<BasicSpellDTO>> findSpells(
            @BeanParam BaseSearch request){
        log.info("SpellbookResource - findspells");
        return spellBookService.findSpells();
    }
}
