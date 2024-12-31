package it.dnd.service;

import io.ebean.Database;
import io.ebean.Transaction;
import it.dnd.dto.equipaggiamento.DettaglioEquipaggiamentoDTO;
import it.dnd.dto.equipaggiamento.InsertEquipaggiamentoDTO;
import it.dnd.exception.ServiceException;
import it.dnd.model.Equipaggiamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class EquipaggiamentoService {

    @Inject
    Database db;


    public DettaglioEquipaggiamentoDTO createEquipaggiamento (InsertEquipaggiamentoDTO dto){
        try(Transaction tx = db.beginTransaction()) {
            Equipaggiamento newEquip =  dto.toEntity();
            newEquip.insert(tx);
            tx.commit();
            return DettaglioEquipaggiamentoDTO.of(newEquip);
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }


    public Long createEquipaggiamentoNoTransaction (InsertEquipaggiamentoDTO dto,Transaction tx){
            Equipaggiamento newEquip =  dto.toEntity();
            newEquip.insert(tx);
            return newEquip.getId();

    }
}
