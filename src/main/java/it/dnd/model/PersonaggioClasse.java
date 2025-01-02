package it.dnd.model;


import io.avaje.lang.Nullable;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.Index;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "a_personaggio_classe")
@Index(columnNames = {"personaggio_id", "tipo_classe_id"}, unique = true)
public class PersonaggioClasse extends  AbstractAuditable {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name ="personaggio_id", nullable = false)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    private Personaggio personaggio;

    @ManyToOne
    @JoinColumn(name ="tipo_classe_id", nullable = false)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    private TipoClasse tipoClasse;

    @Column(nullable = false)
    private int level;
}
