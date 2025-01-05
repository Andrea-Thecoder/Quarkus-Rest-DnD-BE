package it.dnd.service;

import io.ebean.Database;
import io.ebean.ExpressionList;
import io.ebean.PagedList;
import io.ebean.Transaction;
import it.dnd.client.DndRestClassi;
import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.personaggio.DettaglioPersonaggioDTO;
import it.dnd.dto.personaggio.InsertPersonaggioDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.dto.spell.DettaglioSpellTableDTO;
import it.dnd.dto.spell.SpellTableDTO;
import it.dnd.exception.ServiceException;
import it.dnd.model.*;
import it.dnd.model.enumerator.CharacterClass;
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
    DndRestClassi dndRestClassi;


    public DettaglioPersonaggioDTO createPersonaggio (InsertPersonaggioDTO dto) {
        try(Transaction tx = db.beginTransaction()) {
            Personaggio newPg =  dto.toEntity();
            newPg.insert(tx);
            Set<PersonaggioClasse> classList = personaggioClasseService.createPersonaggioClasseNoTransaction(newPg.getId(), dto.getClassi(),tx);
            newPg.setClassi(classList);
            if(CollectionUtils.isNotEmpty(dto.getEquipaggiamentoList())){

                newPg.setEquipaggiamento(
                        personaggioEquipaggiamentoService.createPersonaggioEquipNoTransaction(newPg.getId(),dto.getEquipaggiamentoList(),tx)
                );
            }else {
                newPg.setEquipaggiamento(null);
            }
            tx.commit();
            return DettaglioPersonaggioDTO.of(newPg,createSpellTableByClass(newPg.getClassi()));

        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public PagedResultDTO<DettaglioPersonaggioDTO> findPersonaggi(BaseSearch request){
        ExpressionList<Personaggio> query = db.find(Personaggio.class).where();
        if (request.getSort() == null) {
          query.orderBy("name ASC, id ASC");
        }
        PagedList<Personaggio> personaggi = request.paginationOrderAndSort(query).findPagedList();
        return PagedResultDTO.of(personaggi, p -> DettaglioPersonaggioDTO.of(p, createSpellTableByClass(p.getClassi())));
    }

    public DettaglioPersonaggioDTO getPersonaggioById (UUID id){
        Personaggio pg = getPersonaggioByidOrThrow(id);

        return DettaglioPersonaggioDTO.of(pg,createSpellTableByClass(pg.getClassi()));
    }

    public UUID deletePersonaggio(UUID id){
        Personaggio pg = getPersonaggioByidOrThrow(id);
        try (Transaction tx = db.beginTransaction()) {
            pg.delete(tx);
            tx.commit();;
            return pg.getId();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    public Personaggio getPersonaggioByidOrThrow(UUID id){
        return db.find(Personaggio.class)
                .where()
                .idEq(id)
                .findOneOrEmpty()
                .orElseThrow(() -> new ServiceException("Personaggio avente l'id : "+ id + " non esiste."));
    }

    private List<DettaglioSpellTableDTO> createSpellTableByClass(
            Set<PersonaggioClasse> classi
    ){
        List<DettaglioSpellTableDTO> spellTableList = new ArrayList<>();
                            for (PersonaggioClasse pc  : classi){
                                String classe = CharacterClass.CheckClassName(pc.getTipoClasse().getDescription());
                                if(classe == null)
                                    continue;
                                int level = pc.getLevel();
                                if(level > 20) level = 20;
                                SpellTableDTO spellTable = dndRestClassi.getSpellTableClassByLevel(classe,level);
                                spellTableList.add(DettaglioSpellTableDTO.of(classe,spellTable));
                            }
                            return spellTableList;
    }
}
