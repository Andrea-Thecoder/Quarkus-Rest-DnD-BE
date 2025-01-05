package it.dnd.model;


import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.Index;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spell_book")
@Index(columnNames = {"personaggio_id","spell_name"},unique = true)
public class SpellBook extends  AbstractAuditable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personaggio_id", nullable = false)
    @DbForeignKey(onDelete = io.ebean.annotation.ConstraintMode.CASCADE)
    private Personaggio personaggio;

    @Column(nullable = false)
    private String spellName;

    @Column(nullable = false)
    private int level;

    private Integer resourceCost;






}
