package it.dnd.dto.spell;

import it.dnd.model.SpellBook;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SpellPersonaggioDTO extends  BasicSpellDTO {

    private Long id;
    private Integer resourceCost;

    public static SpellPersonaggioDTO of(SpellBook spellbook) {
        // Creiamo una nuova istanza del DTO
        SpellPersonaggioDTO dto = new SpellPersonaggioDTO();

        dto.setId(spellbook.getId());
        if(spellbook.getResourceCost() != null)
            dto.setResourceCost(spellbook.getResourceCost());

        dto.setIndex(spellbook.getIndex());
        dto.setName(spellbook.getName());
        dto.setLevel(spellbook.getLevel());
        dto.setUrl(spellbook.getUrl());

        return dto;
    }
}
