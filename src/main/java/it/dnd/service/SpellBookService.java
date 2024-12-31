package it.dnd.service;


import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.client.DndApiClient;
import it.dnd.dto.search.BaseSearch;
import it.dnd.dto.spell.*;
import it.dnd.exception.ServiceException;
import it.dnd.model.PersonaggioSpellBook;
import it.dnd.model.SpellBook;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.UUID;

@ApplicationScoped
public class SpellBookService {

    @Inject
    Database db;

    @Inject
    PersonaggioSpellBookService personaggioSpellBookService;

    @RestClient
    DndApiClient dndApiClient;

    public RestResponse<SpellResponseDTO<BasicSpellDTO>> findSpells(){
        return dndApiClient.getSpells();
    }

    public SpellPersonaggioDTO createSpell(InsertSpellBookDTO dto){
        DettaglioSpellResponseDTO spell = getSpellByName(dto.getSpellName());
        if(spell == null) {
            throw new ServiceException("La spell indicata non esiste");
        }
        try(Transaction tx = db.beginTransaction()){
            SpellBook newSpell = new SpellBook();
            newSpell.setIndex(spell.getIndex());
            newSpell.setName(spell.getName());
            newSpell.setLevel(spell.getLevel());
            newSpell.setUrl(spell.getUrl());
            newSpell.setResourceCost(dto.getResourceCost() );
            newSpell.insert(tx);
            personaggioSpellBookService.createPersonaggioSpellbookNoTransaction(dto.getIdPg(),newSpell.getId(),tx);
            tx.commit();;
            return  SpellPersonaggioDTO.of(newSpell);
        }catch (Exception e){
            throw new ServiceException(e);
        }
        }

    public DettaglioSpellResponseDTO getSpellByName(String name){
        return dndApiClient.getSpellsByName(name);
    }
}
