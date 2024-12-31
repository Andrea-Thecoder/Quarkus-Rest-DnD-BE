package it.dnd.model;


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
public class SpellBook extends  AbstractAuditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String index;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private int level;

    private Integer resourceCost;






}
