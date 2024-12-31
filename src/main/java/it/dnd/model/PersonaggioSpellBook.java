package it.dnd.model;

import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.Index;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "a_personaggio_spell_book")
@Index(columnNames = {"personaggio_id", "spell_book_id"},unique = true)
public class PersonaggioSpellBook extends AbstractAuditable{

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name ="personaggio_id", nullable = false)
    @DbForeignKey(onDelete = io.ebean.annotation.ConstraintMode.CASCADE)
    private Personaggio personaggio;

    @ManyToOne
    @JoinColumn(name ="spell_book_id", nullable = false)
    @DbForeignKey(onDelete = io.ebean.annotation.ConstraintMode.CASCADE)
    private SpellBook spellBook;
}
