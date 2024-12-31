package it.dnd.service;


import io.ebean.Database;
import it.dnd.client.DndApiClient;
import it.dnd.dto.search.BaseSearch;
import it.dnd.dto.spell.BasicSpellDTO;
import it.dnd.dto.spell.SpellResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

@ApplicationScoped
public class SpellBookService {

    @Inject
    Database db;

    @RestClient
    DndApiClient dndApiClient;

    public RestResponse<SpellResponseDTO<BasicSpellDTO>> findSpells(){
        return dndApiClient.getSpells();
    }
}
