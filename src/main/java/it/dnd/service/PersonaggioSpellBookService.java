package it.dnd.service;


import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonaggioSpellBookService {
    @Inject
    Database db;

    public PersonaggioSpellBook createPersonaggioSpellbookNoTransaction (UUID pgId, Long idSpell, Transaction tx){
            PersonaggioSpellBook pgs = new PersonaggioSpellBook();
            pgs.setPersonaggio(db.reference(Personaggio.class, pgId));
            pgs.setSpellBook(db.reference(SpellBook.class, idSpell));
            pgs.insert(tx);
            return pgs;
    }

}
