package pokemonprobem;

import java.util.Arrays;

/**
 *
 * @author Shabab-1281539
 */
public class PokemonProbem {

    public static void main(String[] args) {
        Integer[] pokemon1 = {4, 5, -1, null, null};
        Integer[] pokemon2 = {2, 27, 7, 12, null};
        System.out.println(Arrays.deepToString(mergeLineup(pokemon1, pokemon2)));
    }

    public static Integer[] mergeLineup(Integer[] pokemon1, Integer[] pokemon2) {
        Integer[] result = new Integer[pokemon1.length];
        for (int i = 0; i < pokemon1.length; i++) {
            Integer pokemonOneHP = pokemon1[i] == null ? 0 : pokemon1[i];
            Integer pokemonTwoHP = pokemon2[pokemon1.length - i - 1] == null ? 0 : pokemon2[pokemon1.length - i - 1];
            result[i] = pokemonOneHP + pokemonTwoHP;
        }
        return result;
    }

}