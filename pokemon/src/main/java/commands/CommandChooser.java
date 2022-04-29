package commands;

import ability.Ability;
import pokemon.Pokemon;

import java.util.Random;

/**
 * Command chooser class
 * decides on a command that a Pokemon can use
 */
public class CommandChooser {
    Pokemon pokemon;

    public CommandChooser(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    // types of possible attacks
    private enum TypesOfAttack {
        attack, ability1, ability2
    }

    public Command randomCommand() throws IllegalStateException {

        /* this condition assures that the stunned Pokemon executes its move this round,
            but not the next round */
        if (pokemon.isStunned() && !pokemon.canMoveNexRound()) {
            pokemon.setStunned(false);
            pokemon.setCanMoveNexRound(true);
            if (pokemon.getAbilities() != null)
                for (Ability ability : pokemon.getAbilities())
                    ability.updateCooldownRealTime();

            return null;
        }

        if (pokemon.isStunned()) {
            pokemon.setCanMoveNexRound(false);
        }

        if (pokemon.getAbilities() == null)
            return new CommandAttack();

        // get a random attack
        TypesOfAttack t = TypesOfAttack.values()[new Random().nextInt(TypesOfAttack.values().length)];

        // always updates the cooldown time for an ability
        switch (t) {
            case attack -> {
                if (pokemon.getAbilities() != null)
                    for (Ability ability : pokemon.getAbilities())
                        ability.updateCooldownRealTime();
                return new CommandAttack();
            }
            case ability1 -> {
                if (pokemon.getAbilities().get(0).getCooldownRealTime() > 0)
                    return randomCommand();
                pokemon.getAbilities().get(1).updateCooldownRealTime();
                return new CommandAbility(0);
            }
            case ability2 -> {
                if (pokemon.getAbilities().get(1).getCooldownRealTime() > 0)
                    return randomCommand();
                pokemon.getAbilities().get(0).updateCooldownRealTime();
                return new CommandAbility(1);
            }
            default -> throw new IllegalStateException("Unexpected value: " + t);
        }
    }
}
