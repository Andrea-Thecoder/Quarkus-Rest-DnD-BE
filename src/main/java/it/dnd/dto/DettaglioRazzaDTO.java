package it.dnd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DettaglioRazzaDTO {

    private List<SimplyNameClass> languages;

    @JsonProperty("starting_proficiencies")
    private List<SimplyNameClass> proficiency;

    @JsonProperty("ability_bonuses")
    private List<AbilityBonus> abilityBonus;


    private List<SimplyNameClass> traits;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SimplyNameClass {
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AbilityBonus {
        @JsonProperty("ability_score")
        private SimplyNameClass ability;
        private int bonus;
    }



}
