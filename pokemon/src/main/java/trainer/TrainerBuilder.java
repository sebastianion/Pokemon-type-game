package trainer;

import pokemon.Pokemon;

import java.util.ArrayList;

/**
 * Builder class for Trainer
 */
public class TrainerBuilder {
    private Trainer trainer = new Trainer();

    public TrainerBuilder withName(String name) {
        trainer.setName(name);
        return this;
    }

    public TrainerBuilder withAge(int age) {
        trainer.setAge(age);
        return this;
    }

    public TrainerBuilder withPokemon(Pokemon pokemon) {
        if (trainer.getPokemons() == null)
            trainer.setPokemons(new ArrayList<>());
        trainer.getPokemons().add(pokemon);
        return this;
    }

    public Trainer build() {
        return trainer;
    }
}
