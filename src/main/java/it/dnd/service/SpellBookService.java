package it.dnd.service;


import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.client.DndApiClient;
import it.dnd.dto.spell.*;
import it.dnd.exception.ServiceException;
import it.dnd.model.PersonaggioSpellBook;
import it.dnd.model.SpellBook;
import it.dnd.utils.FormatString;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
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
        DettaglioSpellResponseDTO spell = dndApiClient.getSpellsByName(FormatString.dashString(dto.getSpellName()));
        try(Transaction tx = db.beginTransaction()){
            SpellBook newSpell = db.find(SpellBook.class).where().ilike("name", dto.getSpellName()).findOne();
            if(newSpell == null){
                newSpell = new SpellBook();
                newSpell.setIndex(spell.getIndex());
                newSpell.setName(spell.getName());
                newSpell.setLevel(spell.getLevel());
                newSpell.setUrl(spell.getUrl());
                newSpell.setResourceCost(dto.getResourceCost() );
                newSpell.insert(tx);
            }
            personaggioSpellBookService.createPersonaggioSpellbookNoTransaction(dto.getIdPg(),newSpell.getName(),tx);
            tx.commit();;
            return  SpellPersonaggioDTO.of(newSpell);
        }catch (Exception e){
            throw new ServiceException(e);
        }
        }

    public DettaglioSpellResponseDTO getRestSpellByName(String name){
        return dndApiClient.getSpellsByName(FormatString.dashString(name));
    }

    public void deleteSpell (String name){
        SpellBook spell = getSpellByName(name);
        try (Transaction tx = db.beginTransaction()) {
            if (personaggioSpellBookService.countSpellByName(spell.getName()) != 0) {
                throw new ServiceException("Impossibile proseguire con la cancellazione della Spell in quanto usata in altri contesti");
            }
            spell.delete(tx);
            tx.commit();
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public void removeSpellFromPersonaggio(String spellName, UUID idPg){
       PersonaggioSpellBook psb =  personaggioSpellBookService.getPersonaggioSpellBookByIdAndName(spellName,idPg);
       System.out.println(psb);
        try (Transaction tx = db.beginTransaction()){
            psb.delete(tx);
            if(personaggioSpellBookService.countSpellByName(spellName) == 0){
                SpellBook spell = getSpellByName(spellName);
                spell.delete(tx);
            }
            tx.commit();

        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public SpellBook getSpellByName(String name){
        return db.find(SpellBook.class).where()
                .ilike("name",name)
                .findOneOrEmpty().orElseThrow(() ->
                        new NotFoundException("Spell non trovata.")
                );
    }


   /* public SpellBook createSpellNoTansiction (InsertSpellBookDTO dto, Transaction tx){
        SpellBook newSpell = new SpellBook();
        newSpell.setIndex(dto.getIndex());
        newSpell.setName(spell.getName());
        newSpell.setLevel(spell.getLevel());
        newSpell.setUrl(spell.getUrl());
        newSpell.setResourceCost(dto.getResourceCost() );
        newSpell.insert(tx);
        personaggioSpellBookService.createPersonaggioSpellbookNoTransaction(dto.getIdPg(),newSpell.getId(),tx);
    }*/
}
