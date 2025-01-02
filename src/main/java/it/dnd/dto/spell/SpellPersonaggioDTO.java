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

    private Integer resourceCost;

    public static SpellPersonaggioDTO of(SpellBook spellbook) {
        SpellPersonaggioDTO dto = new SpellPersonaggioDTO();
        if(spellbook.getResourceCost() != null)
            dto.setResourceCost(spellbook.getResourceCost());

        dto.setIndex(spellbook.getIndex());
        dto.setName(spellbook.getName());
        dto.setLevel(spellbook.getLevel());
        dto.setUrl(spellbook.getUrl());

        return dto;
    }
}
