import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.List;
import java.util.Map;

public class PokemonCalculator {

    private static final Map<String, Double> multiplierMap = Map.of(
            "no_damage_to", (double) 0,
            "half_damage_to", 0.5,
            "double_damage_to", 2.0
    );

    public double calculateEffectiveness(JsonNode jsonData, List<String> defenderNames) {
        double result = 1;
        JSONObject json = jsonData.getObject();
        JSONObject damageRelations = json.getJSONObject("damage_relations");
        for (String damage : damageRelations.keySet()) {
            if (damage.contains("_to")) {
                JSONArray attackerDamageRelations = damageRelations.getJSONArray(damage);
                for(String defenderName: defenderNames) {
                    for (int i = 0; i <= attackerDamageRelations.length() - 1; i++) {
                        JSONObject defenderType = (JSONObject) attackerDamageRelations.get(i);
                        if (defenderType.get("name").toString().equalsIgnoreCase(defenderName)) {
                            result *= multiplierMap.get(damage);
                        }
                    }
                }
            }
        }
        return result;
    }
}