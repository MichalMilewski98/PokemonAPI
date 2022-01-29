package rest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class PokemonRestService {

    private static String API_URL = "https://pokeapi.co/api/v2/type/";

    public HttpResponse<JsonNode> getPokemonType(String type) {

        return Unirest.get(API_URL + type)
                .header("accept", "application/json")
                .asJson()
                .ifFailure(response -> {
                    System.out.println("Status: " + response.getStatus());
                    response.getParsingError().ifPresent(e -> {
                        System.out.println("Original body: " + e.getOriginalBody());
                    });
                });
    }

}
