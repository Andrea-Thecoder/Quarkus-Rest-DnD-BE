package it.dnd.service;

import io.ebean.Database;
import io.ebean.ExpressionList;
import io.ebean.PagedList;
import io.ebean.Transaction;
import it.dnd.client.DndApiClient;
import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.personaggio.DettaglioPersonaggioDTO;
import it.dnd.dto.personaggio.InsertPersonaggioDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.exception.ServiceException;
import it.dnd.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;


import java.util.*;

@ApplicationScoped
public class PersonaggioService {

    @Inject
    Database db;

    @Inject
    PersonaggioClasseService personaggioClasseService;

    @Inject
   PersonaggioEquipaggiamentoService personaggioEquipaggiamentoService;

    @RestClient
    DndApiClient dndApiClient;


    public DettaglioPersonaggioDTO createPersonaggio (InsertPersonaggioDTO dto) {
        try(Transaction tx = db.beginTransaction()) {
            Personaggio newPg =  dto.toEntity();
            newPg.setRace(db.reference(TipoRazza.class,dto.getIdRace()));
            newPg.insert(tx);
            Set<PersonaggioClasse> classList = personaggioClasseService.createPersonaggioClasseNoTransaction(newPg.getId(), dto.getIdClassi(),tx);
            newPg.setClassi(classList);
            if(CollectionUtils.isNotEmpty(dto.getEquipaggiamentoList())){

                newPg.setEquipaggiamento(
                        personaggioEquipaggiamentoService.createPersonaggioEquipNoTransaction(newPg.getId(),dto.getEquipaggiamentoList(),tx)
                );
            }else {
                newPg.setEquipaggiamento(null);
            }
            tx.commit();
            System.out.println("ciao"+newPg.getClassi().size());
            return DettaglioPersonaggioDTO.of(newPg);

        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public PagedResultDTO<DettaglioPersonaggioDTO> findPersonaggi(BaseSearch request){
        ExpressionList<Personaggio> query = db.find(Personaggio.class).where();
        PagedList<Personaggio> personaggi = request.paginationOrderAndSort(query).findPagedList();
        return PagedResultDTO.of(personaggi,DettaglioPersonaggioDTO::of);
    }
}
