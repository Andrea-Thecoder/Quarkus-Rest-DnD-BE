package it.dnd.dto.spell;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.dnd.json.deserializer.SpellTableDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpellTableDTO {

    private int  level;
    @JsonProperty("spellcasting")
    @JsonDeserialize(using = SpellTableDeserializer.class)
    private Map<String, Integer> spellcasting;

}
