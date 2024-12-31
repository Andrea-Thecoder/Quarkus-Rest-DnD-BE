package it.dnd.dto.personaggio;


import it.dnd.dto.equipaggiamento.DettaglioEquipaggiamentoDTO;
import it.dnd.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DettaglioPersonaggioDTO {


    private String name;
    private TipoRazza race;
    private int level;
    private Double gold;
    private Set<TipoClasse> classi;
    private List<DettaglioEquipaggiamentoDTO> equipaggiamento;


    public static DettaglioPersonaggioDTO of(Personaggio personaggio) {
        DettaglioPersonaggioDTO dto = new DettaglioPersonaggioDTO();
        dto.setName(personaggio.getName());
        dto.setRace(personaggio.getRace());
        dto.setLevel(personaggio.getLevel());
        dto.setGold(personaggio.getGold());
        dto.setClassi(ExtractTipoClasse(personaggio.getClassi()));
        dto.setEquipaggiamento(ExtractEquip(personaggio.getEquipaggiamento()));
        return dto;
    }

    private static Set<TipoClasse> ExtractTipoClasse (Set<PersonaggioClasse> pc){
        return pc.stream().map(PersonaggioClasse::getTipoClasse).collect(Collectors.toSet());
    }

    private static List<DettaglioEquipaggiamentoDTO> ExtractEquip (List<PersonaggioEquipaggiamento> pe){
        return pe.stream()
                .map(peq -> DettaglioEquipaggiamentoDTO.of((peq.getEquipaggiamento())))
                .toList();
    }
}
