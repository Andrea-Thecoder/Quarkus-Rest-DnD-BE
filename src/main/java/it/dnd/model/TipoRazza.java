package it.dnd.model;


import io.ebean.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_razza")
public class TipoRazza extends Model {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;

}
