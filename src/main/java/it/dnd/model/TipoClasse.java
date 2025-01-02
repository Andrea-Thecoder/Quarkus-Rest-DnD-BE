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
@Table(name="tipo_classe")
@JsonIgnoreProperties(ignoreUnknown = true)

public class TipoClasse extends Model {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;
}
