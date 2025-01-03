package it.dnd.dto.spell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DettaglioSpellTableDTO {

    private String className;
    private int level;
    private Map<String, Integer> spellTable;

    public static DettaglioSpellTableDTO of (String className, SpellTableDTO spellTable){
        DettaglioSpellTableDTO dto = new DettaglioSpellTableDTO();
        dto.setClassName(className);
        dto.setLevel(spellTable.getLevel());
        dto.setSpellTable(spellTable.getSpellcasting());
        return dto;
    }
}
