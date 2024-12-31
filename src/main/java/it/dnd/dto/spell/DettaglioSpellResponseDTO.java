package it.dnd.dto.spell;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class DettaglioSpellResponseDTO extends BasicSpellDTO{

    private List<String> desc;
    @JsonProperty("higher_level")
    private List<String> higherLevel;
    private String range;
    private List<String> components;
    private String material;
    private boolean ritual;
    private String duration;
    private boolean concentration;
    @JsonProperty("casting_time")
    private String castingTime;
    private Map<String, Object> damage;
    private Map<String, Object> dc;

    @JsonProperty("area_of_effect")
    private Map<String, Object> areaOfEffect;

    private Map<String, Object> school;
    private List<Map<String, Object>> classes;
    private List<Map<String, Object>> subclasses;

    }
