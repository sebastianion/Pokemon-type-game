package commands;

import ability.Ability;
import pokemon.Pokemon;

/**
 * Use the first or second ability on another Pokemon
 */
public class CommandAbility extends Command {
    private final int abilityNo;

    public CommandAbility(int abilityNo) {
        this.abilityNo = abilityNo;
        this.setType("ability " + (abilityNo + 1));
    }

    @Override
    public void setAbilityFeatures(Pokemon pokemon) {
        Ability ability = pokemon.getAbilities().get(abilityNo);
        pokemon.setCanDodge(ability.hasDodge());
        this.setDamage(" (-" + ability.getDamage() + ")");

        ability.setCooldownRealTime(pokemon.getAbilities()
                .get(abilityNo)
                .getCooldown()
        );
    }

    @Override
    public void attack(Pokemon attacker, Pokemon attacked) {
        Ability ability = attacker.getAbilities().get(abilityNo);

        if (ability.hasStun()) {
            attacked.setStunned(true);
            attacked.setCanMoveNexRound(false);
        }

        attacked.tookDamage(ability.getDamage());
    }
}
