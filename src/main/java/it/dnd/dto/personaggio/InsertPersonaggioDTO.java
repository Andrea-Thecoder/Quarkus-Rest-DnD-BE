package it.dnd.dto.personaggio;


import it.dnd.dto.persionaggioClasse.InsertPersonaggioClasseDTO;
import it.dnd.dto.persionaggioClasse.PersonaggioClasseDTO;
import it.dnd.dto.equipaggiamento.InsertEquipaggiamentoDTO;
import it.dnd.model.Personaggio;
import it.dnd.model.TipoRazza;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InsertPersonaggioDTO {

    @NotBlank(message = "Il nome non può essere vuoto")
    private String name;

    @NotNull(message = "La razza non può essere vuoto")
    private Long idRace;

    @PositiveOrZero(message = "Il livello deve avere un valore pari o superiore ad 1")
    private int experience;

    @NotNull(message = "La classe deve non può essere vuota")
    private Set<InsertPersonaggioClasseDTO> classi;

    private List<InsertEquipaggiamentoDTO>  equipaggiamentoList;

    private Double gold;



    public Personaggio toEntity() {
        Personaggio personaggio = new Personaggio();
        personaggio.setName(this.name);
        personaggio.setRace(personaggio.db().reference(TipoRazza.class,this.idRace));
        personaggio.setExperience(this.experience);
        personaggio.setGold(this.gold);
        return personaggio;
    }

}
