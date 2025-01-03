package it.dnd.service;


import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.model.*;
import it.dnd.model.query.QPersonaggioSpellBook;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonaggioSpellBookService {
    @Inject
    Database db;

    public PersonaggioSpellBook createPersonaggioSpellbookNoTransaction (UUID pgId, String spellName, Transaction tx){
            PersonaggioSpellBook pgs = new PersonaggioSpellBook();
            pgs.setPersonaggio(db.reference(Personaggio.class, pgId));
            pgs.setSpellBook(db.reference(SpellBook.class, spellName));
            pgs.insert(tx);
            return pgs;
    }

    public PersonaggioSpellBook getPersonaggioSpellBookByIdAndName(String spellName, UUID idPg){
        return  db.find(PersonaggioSpellBook.class)
                .where()
                .ilike("spellBook.name",spellName)
                .eq("personaggio.id",idPg)
                .findOneOrEmpty().orElseThrow(()->
                        new NotFoundException("Il personaggio non possiede la Spell ricercata."));
    }

    public List<SpellBook> getSpellBooksByIdPersonaggio(UUID id){
        return db.find(PersonaggioSpellBook.class)
                .select("spellBook")
                .where()
                .eq("personaggio.id",id)
                .findList()
                .stream()
                .map(PersonaggioSpellBook::getSpellBook)
                .toList();
    }

    public  long countSpellByName (String spellName){
       return db.find(PersonaggioSpellBook.class)
               .select("spellBook.name")
               .where()
               .ilike("spellBook.name",spellName)
               .findCount();
    }

}
