package it.dnd.json.deserializer;

import com.fasterxml.jackson.core.JsonParser; // Assicurati di usare Jackson
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SpellTableDeserializer  extends JsonDeserializer <Map<String, Integer>>{

    @Override
    public Map<String, Integer> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Map<String, Integer> spellSlots = new TreeMap<>();

        // Itera su tutti i campi presenti nel nodo JSON
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
                spellSlots.put(key, entry.getValue().asInt());
        }
        return spellSlots;
    }
}
