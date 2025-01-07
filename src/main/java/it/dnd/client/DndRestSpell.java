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

    //L'api in questione ricerca by index, ovvero nome completo della spell, col trattino invece di eventuali spazi e tutto in minuscolo. FormatString utils ha un method per convertire  il nome della spell nel formato accettato dalla rest.
    @GET
    @Path("/{spellName}")
    @Produces(MediaType.APPLICATION_JSON)
    DettaglioSpellResponseDTO getSpellsByName(@PathParam("spellName")String spellName);
}
