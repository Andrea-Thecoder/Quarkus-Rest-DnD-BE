package it.dnd.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "anagrafica_personaggio")
public class Personaggio extends  AbstractAuditable{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TipoRazza race;

    private Integer experience;

    private Double gold;

    @OneToMany(mappedBy = "personaggio")
    private Set<PersonaggioClasse> classi;

    @OneToMany(mappedBy = "personaggio")
    private List<PersonaggioEquipaggiamento> equipaggiamento;

    @OneToMany(mappedBy = "personaggio")
    private List<PersonaggioSpellBook> spellBook;


}
