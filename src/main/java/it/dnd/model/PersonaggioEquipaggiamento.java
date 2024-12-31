package it.dnd.model;


import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.Index;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "a_personaggio_equipaggiamento")
@Index(columnNames = {"personaggio_id", "equipaggiamento_id"})
public class PersonaggioEquipaggiamento extends AbstractAuditable{

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name ="personaggio_id", nullable = false)
    @DbForeignKey(onDelete = io.ebean.annotation.ConstraintMode.CASCADE)
    private Personaggio personaggio;

    @ManyToOne
    @JoinColumn(name ="equipaggiamento_id", nullable = false)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    private Equipaggiamento equipaggiamento;

}
