package it.dnd.service;

import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.dto.persionaggioClasse.InsertPersonaggioClasseDTO;
import it.dnd.dto.persionaggioClasse.PersonaggioClasseDTO;
import it.dnd.model.Personaggio;
import it.dnd.model.PersonaggioClasse;
import it.dnd.model.TipoClasse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonaggioClasseService {

    @Inject
    Database db;


    public Set<PersonaggioClasse> createPersonaggioClasseNoTransaction (
            UUID pgId,
            Set<InsertPersonaggioClasseDTO> classi,
            Transaction tx){
        return classi.stream().map(classe ->{
            PersonaggioClasse pgc = new PersonaggioClasse();
            pgc.setPersonaggio(db.reference(Personaggio.class, pgId));
            pgc.setTipoClasse(db.reference(TipoClasse.class, classe.getIdClasse()));
            pgc.setLevel(classe.getLevel());
            pgc.insert(tx);
            return pgc;
        }).collect(Collectors.toSet());
    }
}
