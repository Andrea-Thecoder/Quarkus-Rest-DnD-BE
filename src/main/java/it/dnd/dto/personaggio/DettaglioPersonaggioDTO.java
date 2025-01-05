package it.dnd.dto.personaggio;


import it.dnd.dto.persionaggioClasse.PersonaggioClasseDTO;
import it.dnd.dto.equipaggiamento.DettaglioEquipaggiamentoDTO;
import it.dnd.dto.spell.DettaglioSpellTableDTO;
import it.dnd.dto.spell.SpellPersonaggioDTO;
import it.dnd.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DettaglioPersonaggioDTO {


    private UUID id;
    private String name;
    private String race;
    private int experience;
    private Double gold;
    private List<DettaglioSpellTableDTO> spellTable;
    private Set<PersonaggioClasseDTO> classi;
    private List<DettaglioEquipaggiamentoDTO> equipaggiamento;
    private List<SpellPersonaggioDTO> spellbook;



    public static DettaglioPersonaggioDTO of(Personaggio personaggio,List<DettaglioSpellTableDTO> spellTable) {
        DettaglioPersonaggioDTO dto = new DettaglioPersonaggioDTO();
        dto.setId(personaggio.getId());
        dto.setName(personaggio.getName());
        dto.setRace(personaggio.getRace().getDescription());
        dto.setExperience(personaggio.getExperience());
        dto.setGold(personaggio.getGold());
        dto.setSpellTable(spellTable);
        dto.setClassi(ExtractTipoClasse(personaggio.getClassi()));
        dto.setEquipaggiamento(ExtractEquip(personaggio.getEquipaggiamento()));
        dto.setSpellbook(ExtractSpell(personaggio.getSpellBook()));
        return dto;
    }

    private static Set<PersonaggioClasseDTO> ExtractTipoClasse (Set<PersonaggioClasse> pc){
        return pc.stream().map(PersonaggioClasseDTO::of).collect(Collectors.toSet());
    }

    private static List<DettaglioEquipaggiamentoDTO> ExtractEquip (List<PersonaggioEquipaggiamento> pe){
        return pe.stream()
                .map(peq -> DettaglioEquipaggiamentoDTO.of((peq.getEquipaggiamento())))
                .toList();
    }
    private static List<SpellPersonaggioDTO> ExtractSpell (List<SpellBook> ps){
        return ps.stream()
                .sorted(Comparator.comparingInt(SpellBook::getLevel)
                        .thenComparing(SpellBook::getSpellName)
                )
                .map(SpellPersonaggioDTO::of)
                .toList();
    }
}
