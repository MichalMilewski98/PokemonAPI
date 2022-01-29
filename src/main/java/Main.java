import rest.PokemonRestService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String attackType;
        List<String> destPokemonType;

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input: ");
        String input = bf.readLine();

        String[] inputs = input.split(" -> ");

        attackType = inputs[0];
        destPokemonType = Arrays.asList(inputs[1].split(" "));

        PokemonRestService pokemonRestService = new PokemonRestService();
        PokemonCalculator pokemonCalculator = new PokemonCalculator();
        System.out.println(pokemonCalculator.calculateEffectiveness(
                pokemonRestService.getPokemonType(attackType).getBody(), destPokemonType));

        bf.close();
    }
}
