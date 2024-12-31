package it.dnd.client;

import it.dnd.dto.spell.BasicSpellDTO;
import it.dnd.dto.spell.DettaglioSpellResponseDTO;
import it.dnd.dto.spell.SpellResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;


@Path("/spells")
@RegisterRestClient(configKey = "dnd-api")

public interface DndApiClient {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   RestResponse<SpellResponseDTO<BasicSpellDTO>> getSpells();

    @GET
    @Path("/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    DettaglioSpellResponseDTO getSpellsByName(@PathParam("index")String index);
}
