package it.dnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleResultDTO <T> {
    private T payload;
    private String message;
}
