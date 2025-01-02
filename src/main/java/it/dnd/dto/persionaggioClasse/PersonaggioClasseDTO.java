package it.dnd.dto.persionaggioClasse;


import it.dnd.model.PersonaggioClasse;
import it.dnd.model.TipoClasse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PersonaggioClasseDTO {

    private String classe;
    private int level;

    public static PersonaggioClasseDTO of (PersonaggioClasse pc){
        PersonaggioClasseDTO dto = new PersonaggioClasseDTO();
        dto.setClasse(pc.getTipoClasse().getDescription());
        dto.setLevel(pc.getLevel());
        return dto;
    }


}
