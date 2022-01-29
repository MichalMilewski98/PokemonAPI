import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

public class PokemonCalculator {

    public void calculateDamage(JsonNode jsonData, String defenderName) {
        HashMap<String, String> multiplierMap = new HashMap<>();
        try {
            multiplierMap.put("half_damage_to", "0.5x");
            multiplierMap.put("double_damage_to", "2x");
            multiplierMap.put("no_to", "0x");

            JSONObject json = jsonData.getObject();
            JSONObject damageRelations = json.getJSONObject("damage_relations");
            for (String damage : damageRelations.keySet()) {
                if (damage.contains("_to")) {
                    JSONArray attackerDamageRelations = damageRelations.getJSONArray(damage);
                    for (int i = 0; i <= attackerDamageRelations.length() - 1; i++) {
                        JSONObject defenderType = (JSONObject) attackerDamageRelations.get(i);
                        if (defenderType.get("name").toString().equalsIgnoreCase(defenderName)) {
                            System.out.println(multiplierMap.get(damage));
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}