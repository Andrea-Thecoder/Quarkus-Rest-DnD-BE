package it.dnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import io.ebean.annotation.WhoCreated;
import io.ebean.annotation.WhoModified;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;

@MappedSuperclass //indica che questa classe non sarà una tabella nel db ma le sue proprietà saranno ereditate dalle loro sottoclassi
@JsonInclude(value = Include.NON_NULL) //include solo i campi not null, ergo esclude i null!
@Getter
@Setter
//Model è una classe base di Ebean, permette di usare gli EbeanORM e sblocca utility aggiuntive di Ebean
public abstract class AbstractAuditable extends Model {

    @JsonIgnore //campo escluso durante la serializzazione json , ergo non sarà mai presente nei json.
    @Version //utilizzato per la gestione ottimistica della concorrenza, all'aggiornamento del campo ebean controlla se il value è uguale a quello presente nel db, se no allora qualcun'altro ha modificato il record. Previene sovrascritture accidentali ! (!!!)
    @Schema(hidden = true) //OpenApi, indica che il campo è nascosto dalla documentazione api!
    protected Long _version;

    @JsonIgnore
    @WhenCreated //popola automaticamente con data-ora alla sua creazione. Non viene aggiornato nelle successive modifiche.
    @Schema(hidden = true)
    @Column(updatable = false) //impedisce la modifica del campo in fase di aggiornamento
    @Temporal(TemporalType.TIMESTAMP) //indica che type temporale è ovvero data ed ora.
    protected Date _dataCreazione;

    @JsonIgnore
    @WhoCreated //popola automaticamente il campo con l'utente che sta aggiornando il record. Ebean deve essere configurato per trcciare in autonomia  l'utente tramite auth,session etc.
    @Schema(hidden = true)
    @Column(updatable = false)
    protected String _utenteCreazione;

    @JsonIgnore
    @WhenModified //si popola automaticamente di data-ora  ogni qualvolta vi è una modifica del record!
    @Schema(hidden = true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date _dataModifica;

    @JsonIgnore
    @WhoModified //come whoCreated ma si attiva alla modifica del record!
    @Schema(hidden = true)
    protected String _utenteModifica;
}
