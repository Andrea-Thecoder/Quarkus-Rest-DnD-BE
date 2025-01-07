package it.dnd.service;

import io.ebean.*;
import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.SimplyInsertTypeDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.exception.ServiceException;
import it.dnd.model.TipoClasse;
import it.dnd.model.TipoRazza;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

public class TipoRazzaService {

    @Inject
    Database db;

    public PagedResultDTO<TipoRazza> findRazze (BaseSearch request){
        ExpressionList<TipoRazza> query = db.find(TipoRazza.class).where();
        PagedList<TipoRazza> tipoRazzaPagedList = request.paginationOrderAndSort(query).findPagedList();
        return PagedResultDTO.of(tipoRazzaPagedList, a->a);
    }

    public TipoRazza createTipoRazza (SimplyInsertTypeDTO dto){
        try (Transaction tx = db.beginTransaction()) {
            TipoRazza tc = new TipoRazza();
            tc.setDescription(dto.getDescription());
            tc.insert(tx);
            tx.commit();
            return tc;
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public void updateTipoRazza (Long id, SimplyInsertTypeDTO dto){
        try (Transaction tx = db.beginTransaction()) {
            TipoRazza tc = getTipoRazzaById(id);
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

    public Long deleteTipoRazza (Long id){
        try (Transaction tx = db.beginTransaction()) {
            TipoRazza tc = getTipoRazzaById(id);
            tc.delete(tx);
            tx.commit();
            return tc.getId();

        }catch (Exception e){
            throw new ServiceException(e);
        }
    }


    public TipoRazza getTipoRazzaById(Long id){
        return db.find(TipoRazza.class).where()
                .idEq(id)
                .findOneOrEmpty()
                .orElseThrow(() -> new NotFoundException("Tipo razza avente id "+ id + " non esiste." ));
    }


}
