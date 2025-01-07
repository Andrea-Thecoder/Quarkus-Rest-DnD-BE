package it.dnd.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimplyInsertTypeDTO {

    @NotBlank(message = "La descrizione non può essere vuota")
    private String description;



}
