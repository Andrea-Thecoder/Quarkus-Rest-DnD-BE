package it.dnd.model.enumerator;


import java.lang.reflect.Array;
import java.util.Arrays;

public enum CharacterClass implements  IEnum {

    BARD("bardo"),
    CLERIC("Chierico"),
    DRUID("Druido"),
    PALADIN("Paladino"),
    RANGER("Ranger"),
    SORCERER("Stregone"),
    WIZARD("Mago");

    private final   String className;

    CharacterClass(String className){
        this.className = className;
    }

    @Override
    public String getClassName(){
        return this.className;
    }

    @Override
    public String getName(){
        return this.name();
    }

    public static String CheckClassName (String description){
       return Arrays.stream(values())
                .filter(classname ->
                     classname.getClassName().equalsIgnoreCase(description))
                .map(Enum::name)
                .map(String::toLowerCase)
                .findFirst().orElse(null);
    }
}

