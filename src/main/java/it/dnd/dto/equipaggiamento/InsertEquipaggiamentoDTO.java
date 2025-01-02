package it.dnd.dto.equipaggiamento;

import it.dnd.model.Equipaggiamento;
import it.dnd.model.TipoEquipaggiamento;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class InsertEquipaggiamentoDTO {

    @NotBlank(message = "Il nome non può essere vuoto")
    private String name;

    @PositiveOrZero(message = "La quantità non può essere un valore negativo")
    private int quantity;

    @NotNull(message = "TipoEquip id deve avere un valore")
    private Long idTipoEquipaggiamento;

    private String damage;
    private Integer caBonus;
    private Double price;
    private Double weight;
    private String proprieties;
    private String description;

    public Equipaggiamento toEntity() {
        Equipaggiamento equipaggiamento = new Equipaggiamento();
        equipaggiamento.setName(this.name);
        equipaggiamento.setQuantity(this.quantity);
        equipaggiamento.setTipoEquipaggiamento(equipaggiamento.db().reference(TipoEquipaggiamento.class,this.idTipoEquipaggiamento));
        equipaggiamento.setDamage(this.damage);
        equipaggiamento.setCaBonus(this.caBonus);
        equipaggiamento.setPrice(this.price);
        equipaggiamento.setWeight(this.weight);
        equipaggiamento.setProprieties(this.proprieties);
        equipaggiamento.setDescription(this.description);
        return equipaggiamento;
    }


}
