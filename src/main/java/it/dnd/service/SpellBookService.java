package it.dnd.service;


import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.client.DndRestSpell;
import it.dnd.dto.spell.*;
import it.dnd.exception.ServiceException;
import it.dnd.model.PersonaggioSpellBook;
import it.dnd.model.SpellBook;
import it.dnd.utils.FormatString;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SpellBookService {

    @Inject
    Database db;

    @Inject
    PersonaggioSpellBookService personaggioSpellBookService;

    @RestClient
    DndRestSpell dndRestSpell;

    public SpellResponseDTO<BasicSpellDTO> findSpells(){
        return dndRestSpell.getSpells();
    }

    public DettaglioSpellResponseDTO getSpellDettaglioByName (String spellName){
        return dndRestSpell.getSpellsByName(FormatString.dashString(spellName));
    }

    public List<SpellPersonaggioDTO> getSpellbooksByPersonaggioId(UUID id){
        List<SpellBook> sp = personaggioSpellBookService.getSpellBooksByIdPersonaggio(id);
        return sp.stream().map(SpellPersonaggioDTO::of).toList();
    }

    public SpellPersonaggioDTO createSpell(InsertSpellBookDTO dto){
        DettaglioSpellResponseDTO spell = dndRestSpell.getSpellsByName(FormatString.dashString(dto.getSpellName()));
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

}
