package it.dnd.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.ebean.Model;
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
@Table(name = "tipo_equipaggiamento")

public class TipoEquipaggiamento extends Model {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    private String description;
}
