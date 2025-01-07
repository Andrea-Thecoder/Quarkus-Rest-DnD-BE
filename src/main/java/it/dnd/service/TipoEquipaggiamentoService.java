package it.dnd.service;

import io.ebean.*;
import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.SimplyInsertTypeDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.exception.ServiceException;
import it.dnd.model.TipoClasse;
import it.dnd.model.TipoEquipaggiamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TipoEquipaggiamentoService {

    @Inject
    Database db;

    public PagedResultDTO<TipoEquipaggiamento> findTipoEquip (BaseSearch request){
        ExpressionList<TipoEquipaggiamento> query = db.find(TipoEquipaggiamento.class).where();
        PagedList<TipoEquipaggiamento> tipoEquipList = request.paginationOrderAndSort(query).findPagedList();
        return PagedResultDTO.of(tipoEquipList, a->a);
    }

    public TipoEquipaggiamento createTipoEquip (SimplyInsertTypeDTO dto){
        try (Transaction tx = db.beginTransaction()) {
            TipoEquipaggiamento tc = new TipoEquipaggiamento();
            tc.setDescription(dto.getDescription());
            tc.insert(tx);
            tx.commit();
            return tc;
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public void updateTipoEquip (Long id, SimplyInsertTypeDTO dto){
        try (Transaction tx = db.beginTransaction()) {
            TipoEquipaggiamento tc = getTipoEquipById(id);
            tc.setDescription(dto.getDescription());
            tc.save(tx);
            tx.commit();
        }
        catch (DuplicateKeyException e){
            throw new ServiceException("Descrizione già esistente. " +  e);
        }
        catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public Long deleteTipoEquip (Long id){
        try (Transaction tx = db.beginTransaction()) {
            TipoEquipaggiamento tc = getTipoEquipById(id);
            tc.delete(tx);
            tx.commit();
            return tc.getId();

        }catch (Exception e){
            throw new ServiceException(e);
        }
    }


    public TipoEquipaggiamento getTipoEquipById(Long id){
        return db.find(TipoEquipaggiamento.class).where()
                .idEq(id)
                .findOneOrEmpty()
                .orElseThrow(() -> new NotFoundException("Tipo Equipaggiamento avente id "+id + " non esiste." ));
    }
}
