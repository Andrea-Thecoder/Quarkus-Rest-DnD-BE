package it.dnd.client;


import it.dnd.dto.spell.SpellTableDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/classes")
@RegisterRestClient(configKey = "dnd-api")
public interface DndRestClassi {

    @GET
    @Path("/{className}/levels")
    @Produces(MediaType.APPLICATION_JSON)
    List<SpellTableDTO> getSpellTableClass(@PathParam("className") String className);


    @GET
    @Path("/{className}/levels/{level}")
    @Produces(MediaType.APPLICATION_JSON)
    SpellTableDTO getSpellTableClassByLevel(
            @PathParam("className") String className,
            @PathParam("level") int level
    );
}
