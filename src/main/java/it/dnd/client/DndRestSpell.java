package it.dnd.client;

import it.dnd.dto.spell.BasicSpellDTO;
import it.dnd.dto.spell.DettaglioSpellResponseDTO;
import it.dnd.dto.spell.SpellResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/spells")
@RegisterRestClient(configKey = "dnd-api")

public interface DndRestSpell {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   SpellResponseDTO<BasicSpellDTO> getSpells();

    @GET
    @Path("/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    DettaglioSpellResponseDTO getSpellsByName(@PathParam("index")String index);
}
