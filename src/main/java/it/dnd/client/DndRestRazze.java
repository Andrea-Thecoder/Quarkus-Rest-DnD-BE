package it.dnd.client;


import it.dnd.dto.DettaglioRazzaDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;

@Path("/races")
@RegisterRestClient(configKey = "dnd-api")
public interface DndRestRazze {

    @GET
    @Path("/{raceName}")
    DettaglioRazzaDTO getDettaglioRazzaByName(@RestPath("raceName") String razza);
}
