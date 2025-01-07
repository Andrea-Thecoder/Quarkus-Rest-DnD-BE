package it.dnd.api;


import it.dnd.dto.PagedResultDTO;
import it.dnd.dto.SimpleResultDTO;
import it.dnd.dto.SimplyInsertTypeDTO;
import it.dnd.dto.search.BaseSearch;
import it.dnd.model.TipoClasse;
import it.dnd.model.TipoEquipaggiamento;
import it.dnd.service.TipoEquipaggiamentoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "API Tipo Equipaggiamento")
@Path("tipo-equipaggiamento")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoEquipaggiamentoResource {

    @Inject
    TipoEquipaggiamentoService tipoEquipaggiamentoService;

    @POST
    @Operation(
            summary = "Inserimento di un nuovo tipo d'equipaggiamento",
            description = "Permette l'inserimento di un nuovo tipo d'equipaggiamento nel DB."
    )
    public TipoEquipaggiamento createTipoEquip (
            @Valid SimplyInsertTypeDTO dto
    ){
        log.info("tipoEquipaggiamentoService - createTipoEquip");
        return tipoEquipaggiamentoService.createTipoEquip(dto);
    }

    @GET
    @Operation(
            summary = "Recupera una lista con tutti i tipi d'equipaggiamento esistenti.",
            description = "Permette il recupero di tutti i tipi d'equipaggiamento inseriti nel DB. Restituisce una lista paginata ed accetta queryparams di ricerca"
    )
    public PagedResultDTO<TipoEquipaggiamento> findTipoEquip(
            @BeanParam BaseSearch request){
        log.info("tipoEquipaggiamentoService - findTipoEquip");
        return tipoEquipaggiamentoService.findTipoEquip(request);
    }
    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Permette la modifica di un tipo Equipaggiamento esistente",
            description = "Permette la modifica di un tipo Equipaggiamento esistente nel DB, tramite il suo Id."
    )
    public SimpleResultDTO<Void> updateTipoEquip(
            @PathParam("id") Long id,
            @Valid SimplyInsertTypeDTO dto
    ){
        log.info("tipoEquipaggiamentoService - updateTipoEquip");
        tipoEquipaggiamentoService.updateTipoEquip(id,dto);
        return SimpleResultDTO.<Void>builder().message("Tipo Classe aggiornato con successo.").build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Permette la cancellazione di un tipo d'equipaggiamento",
            description = "Permette la cancellazione dal DB di un tipo d'equipaggiamento"
    )
    public SimpleResultDTO<Long> deleteTipoEquip(
            @PathParam("id")Long id
    ){
        log.info("tipoEquipaggiamentoService - deleteTipoEquip");
        return SimpleResultDTO.<Long>builder()
                .payload(tipoEquipaggiamentoService.deleteTipoEquip(id))
                .message("Classe cancellata con successo.")
                .build();
    }
}
