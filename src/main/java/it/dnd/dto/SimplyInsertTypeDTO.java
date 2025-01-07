package it.dnd.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimplyInsertTypeDTO {

    @NotBlank(message = "La descrizione non può essere vuota")
    private String description;


    public String getDescription(){
        return StringUtils.capitalize(description).trim();
    }



}
