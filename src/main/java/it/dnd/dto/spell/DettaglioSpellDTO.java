package it.dnd.dto.spell;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DettaglioSpellDTO extends  BasicSpellDTO {
    private String duration;
    private String range;
    private List<String> desc;
    private boolean concentration;
    @JsonProperty("casting_time")
    private String castingTime;
    private Map<String, Object> damage;
    private Map<String, Object> dc;
    private List<Map<String, Object>> classes;
    @JsonProperty("area_of_effect")
    private Map<String, Object> areaOfEffect;

    public static DettaglioSpellDTO of(DettaglioSpellResponseDTO responseDTO) {
        DettaglioSpellDTO dto = new DettaglioSpellDTO();
        dto.setIndex(responseDTO.getIndex());
        dto.setName(responseDTO.getName());
        dto.setLevel(responseDTO.getLevel());
        dto.setUrl(responseDTO.getUrl());
        dto.setDuration(responseDTO.getDuration());
        dto.setRange(responseDTO.getRange());
        dto.setDesc(responseDTO.getDesc());
        dto.setConcentration(responseDTO.isConcentration());
        dto.setCastingTime(responseDTO.getCastingTime());
        dto.setDamage(responseDTO.getDamage());
        dto.setDc(responseDTO.getDc());
        dto.setClasses(responseDTO.getClasses());
        dto.setAreaOfEffect(responseDTO.getAreaOfEffect());

        return dto;
    }
}
