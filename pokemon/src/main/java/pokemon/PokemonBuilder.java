package pokemon;

import ability.Ability;

import java.util.ArrayList;

/**
 * Builder class for Pokemon (builder design pattern)
 */
public class PokemonBuilder {
    private Pokemon pokemon = new Pokemon();

    public PokemonBuilder withName(String name) {
        pokemon.setName(name);
        return this;
    }

    public PokemonBuilder withHP(int HP) {
        pokemon.setHP(HP);
        return this;
    }

    public PokemonBuilder withAttack(int attack) {
        pokemon.setAttack(attack);
        return this;
    }

    public PokemonBuilder withSpecialAttack(int specialAttack) {
        pokemon.setSpecialAttack(specialAttack);
        return this;
    }
    public PokemonBuilder withDefence(int defence) {
        pokemon.setDefence(defence);
        return this;
    }

    public PokemonBuilder withSpecialDefence(int specialDefence) {
        pokemon.setSpecialDefence(specialDefence);
        return this;
    }

    public PokemonBuilder withAbilities(Ability ability) {
        if (pokemon.getAbilities() == null)
            pokemon.setAbilities(new ArrayList<>());
        pokemon.getAbilities().add(ability);
        return this;
    }

    public PokemonBuilder withItems(String itemName) {
        if (pokemon.getItems() == null)
            pokemon.setItems(new ArrayList<>());
        pokemon.getItems().add(itemName);
        return this;
    }

    public Pokemon build() {
        return pokemon;
    }
}
