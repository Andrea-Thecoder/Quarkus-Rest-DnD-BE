package it.dnd.dto.personaggio;


import it.dnd.dto.equipaggiamento.InsertEquipaggiamentoDTO;
import it.dnd.model.Equipaggiamento;
import it.dnd.model.Personaggio;
import it.dnd.model.TipoRazza;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    //@Min(value = 1)
    private Long idRace;

    @Positive(message = "Il livello deve avere un valore pari o superiore ad 1")
    @Min(value = 1)
    private int level;

    @NotNull(message = "La classe deve non può essere vuota")
    private Set<Long> idClassi;

    private List<InsertEquipaggiamentoDTO>  equipaggiamentoList;

    private Double gold;



    public Personaggio toEntity() {
        Personaggio personaggio = new Personaggio();
        personaggio.setName(this.name);
        personaggio.setLevel(this.level);
        personaggio.setGold(this.gold);
        return personaggio;
    }

}
