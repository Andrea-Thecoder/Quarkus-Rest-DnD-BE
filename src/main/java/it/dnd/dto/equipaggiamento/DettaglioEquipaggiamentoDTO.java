package it.dnd.dto.equipaggiamento;

import io.ebean.annotation.Index;
import it.dnd.model.Equipaggiamento;
import it.dnd.model.TipoEquipaggiamento;
import jakarta.persistence.Column;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DettaglioEquipaggiamentoDTO {

    private Long id;
    private String name;
    private String tipoEquipaggiamento;
    private String damage;
    private Integer caBonus;
    private int quantity;
    private Double price;
    private Double weight;
    private String proprieties;
    private String description;

    public static DettaglioEquipaggiamentoDTO of (Equipaggiamento equipaggiamento) {
        DettaglioEquipaggiamentoDTO dto = new DettaglioEquipaggiamentoDTO();
        dto.setId(equipaggiamento.getId());
        dto.setName(equipaggiamento.getName());
        if(equipaggiamento.getTipoEquipaggiamento() != null)
            dto.setTipoEquipaggiamento(equipaggiamento.getTipoEquipaggiamento().getDescription());
        dto.setDamage(equipaggiamento.getDamage());
        dto.setCaBonus(equipaggiamento.getCaBonus());
        dto.setQuantity(equipaggiamento.getQuantity());
        dto.setPrice(equipaggiamento.getPrice());
        dto.setWeight(equipaggiamento.getWeight());
        dto.setProprieties(equipaggiamento.getProprieties());
        dto.setDescription(equipaggiamento.getDescription());
        return dto;
    }

}
