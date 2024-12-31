package it.dnd.dto.spell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasicSpellDTO {
    private String index;
    private String name;
    private int level;
    private String url;
}

