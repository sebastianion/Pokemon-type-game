package trainer;

import pokemon.Pokemon;

import java.util.List;

/**
 * Trainer class
 */
public class Trainer {
    // max number of Pokemon
    public static final int SET_POKEMONS = 3;
    private String name;
    private int age;
    private List<Pokemon> pokemons;

    public Trainer() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    // computes the trainer's best Pokemon by summing all stats
    public Pokemon bestPokemon() {
        int max = -1;
        Pokemon retPokemon = null;

        for (Pokemon pokemon : this.getPokemons()) {
            if (max < pokemon.getSumOfStats()) {
                max = pokemon.getSumOfStats();
                retPokemon = pokemon;
            } else if (max == pokemon.getSumOfStats() && retPokemon != null) {
                // if two Pokemon have equal total stats, get the
                // lesser one, lexicographically
                int lexicographic = retPokemon.getName().compareTo(pokemon.getName());

                if (lexicographic > 0)
                    retPokemon = pokemon;
            }
        }

        return retPokemon;
    }
}
