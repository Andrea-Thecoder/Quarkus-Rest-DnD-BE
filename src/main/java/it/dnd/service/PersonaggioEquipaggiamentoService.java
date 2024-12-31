package it.dnd.service;


import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.dto.equipaggiamento.InsertEquipaggiamentoDTO;
import it.dnd.model.Equipaggiamento;
import it.dnd.model.Personaggio;
import it.dnd.model.PersonaggioEquipaggiamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class PersonaggioEquipaggiamentoService {

    @Inject
    Database db;

    @Inject
    EquipaggiamentoService equipaggiamentoService;

    public List<PersonaggioEquipaggiamento> createPersonaggioEquipNoTransaction (UUID pgId, List<InsertEquipaggiamentoDTO> equip, Transaction tx){
        return equip.stream()
                .map(eq -> {
                    Long idEquip = equipaggiamentoService.createEquipaggiamentoNoTransaction(eq,tx);
                    PersonaggioEquipaggiamento pgEquip = new PersonaggioEquipaggiamento();
                    pgEquip.setPersonaggio(db.reference(Personaggio.class,pgId));
                    pgEquip.setEquipaggiamento(db.reference(Equipaggiamento.class,idEquip));
                    pgEquip.insert(tx);
                    return pgEquip;
                }).toList();
    }
}
