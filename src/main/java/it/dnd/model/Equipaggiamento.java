package it.dnd.model;


import io.ebean.annotation.Index;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipaggiamento")
public class Equipaggiamento extends  AbstractAuditable{

     @Id
    @GeneratedValue
    private Long id;

     @Column(nullable = false)
     @Index
    private String name;

    @ManyToOne
    @JoinColumn(name = "tipo_equipaggiamento_id", nullable = false)
    @Index
     private TipoEquipaggiamento tipoEquipaggiamento;

     private String damage;

     private Integer caBonus;

     private int quantity;

     private Double price;

     private Double weight;

     @Column(columnDefinition = "TEXT")
     private String proprieties;
     //valutare isnerimetno di un map S/O con varie tipologiche associate per facilitare l'inserimento.

    @Column(columnDefinition = "TEXT")
     private String description;

}
