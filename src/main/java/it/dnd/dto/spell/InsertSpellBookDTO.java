package it.dnd.dto.spell;

import it.dnd.model.SpellBook;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class InsertSpellBookDTO {

    @NotBlank(message = "Il nome dell'incantesimo non può essere vuoto")
    private String spellName;
    @NotNull(message = "L'id del personaggio non può essere vuoto")
    private UUID idPg;

    private Integer resourceCost;


}
