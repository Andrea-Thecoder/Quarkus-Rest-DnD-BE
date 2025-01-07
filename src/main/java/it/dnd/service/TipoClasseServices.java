package it.dnd.service;

import io.ebean.*;
import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.SimplyInsertTypeDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.exception.ServiceException;
import it.dnd.model.TipoClasse;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.security.Provider;

public class TipoClasseServices {

    @Inject
    Database db;

    public PagedResultDTO<TipoClasse>  findClassi (BaseSearch request){
        ExpressionList<TipoClasse> query = db.find(TipoClasse.class).where();
        PagedList<TipoClasse> tipoClasseList = request.paginationOrderAndSort(query).findPagedList();
        return PagedResultDTO.of(tipoClasseList, a->a);
    }

    public TipoClasse createTipoClasse (SimplyInsertTypeDTO dto){
        try (Transaction tx = db.beginTransaction()) {
            TipoClasse tc = new TipoClasse();
            tc.setDescription(dto.getDescription());
            tc.insert(tx);
            tx.commit();
            return tc;
        } catch (Exception e){
            throw new ServiceException(e);
        }
    }

    public void updateTipoClasse (Long id, SimplyInsertTypeDTO dto){
        try (Transaction tx = db.beginTransaction()) {
            TipoClasse tc = getTipoClasseById(id);
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

    public Long deleteTipoClasse (Long id){
        try (Transaction tx = db.beginTransaction()) {
            TipoClasse tc = getTipoClasseById(id);
            tc.delete(tx);
            tx.commit();
            return tc.getId();

        }catch (Exception e){
            throw new ServiceException(e);
        }
    }


    public TipoClasse getTipoClasseById(Long id){
        return db.find(TipoClasse.class).where()
                .idEq(id)
                .findOneOrEmpty()
                .orElseThrow(() -> new NotFoundException("Tipo classe avente id "+id + " non esiste." ));
    }

}
