package it.dnd.service;


import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.client.DndRestSpell;
import it.dnd.dto.spell.*;
import it.dnd.exception.ServiceException;
import it.dnd.model.Personaggio;
import it.dnd.model.PersonaggioSpellBook;
import it.dnd.model.SpellBook;
import it.dnd.utils.FormatString;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class SpellBookService {

    @Inject
    Database db;

    @Inject
    PersonaggioService personaggioService;

    @RestClient
    DndRestSpell dndRestSpell;

    public SpellResponseDTO<BasicSpellDTO> findSpells(){
        return dndRestSpell.getSpells();
    }

    public DettaglioSpellResponseDTO getSpellDettaglioByName (String spellName){
        return dndRestSpell.getSpellsByName(FormatString.dashString(spellName));
    }

    public List<SpellPersonaggioDTO> getSpellbooksByPersonaggioId(UUID id){
        List<SpellBook> sp = db.find(SpellBook.class)
                .select("spellName,level,resourceCost")
                .where()
                .eq("personaggio.id",id)
                .orderBy("level ASC,spellName ASC")
                .findList();
        return sp.stream().map(SpellPersonaggioDTO::of).toList();
    }

    public SpellPersonaggioDTO createSpell(InsertSpellBookDTO dto){
        DettaglioSpellResponseDTO spell = dndRestSpell.getSpellsByName(FormatString.dashString(dto.getSpellName()));
        try(Transaction tx = db.beginTransaction()){
            SpellBook newSpell = new SpellBook();
                newSpell.setPersonaggio(newSpell.db().reference(Personaggio.class,dto.getIdPg()));
                newSpell.setSpellName(spell.getName());
                newSpell.setLevel(spell.getLevel());
                newSpell.setResourceCost(dto.getResourceCost() );
                newSpell.insert(tx);
                tx.commit();;
            return  SpellPersonaggioDTO.of(newSpell);
        }catch (Exception e){
            throw new ServiceException(e);
        }
        }

    public void removeSpellFromPersonaggio(String spellName, UUID idPg){
        try (Transaction tx = db.beginTransaction()){
            SpellBook sp = getSpellByNameAndPgId(idPg,spellName);
            sp.delete(tx);
            tx.commit();
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public SpellBook getSpellByNameAndPgId(UUID idPg, String spellName){
        return db.find(SpellBook.class).where()
                .eq("personaggio.id", idPg)
                .ilike("spellName", spellName)
                .findOneOrEmpty()
                .orElseThrow(() -> new NotFoundException("L'incnatesimo "+ spellName + " associato al personaggio avente id "+ idPg + " non èstato trovato"));
    }
}
