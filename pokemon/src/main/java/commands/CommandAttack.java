package commands;

import pokemon.Pokemon;

/**
 * Use the simple or special attack on another pokemon
 */
public class CommandAttack extends Command {
    public CommandAttack() {
        this.setType("attack");
    }

    @Override
    public void setAbilityFeatures(Pokemon pokemon) {
        pokemon.setCanDodge(false);
        pokemon.setStunned(false);
    }

    @Override
    public void attack(Pokemon attacker, Pokemon attacked) {
        if (attacked.canDodge()) {
            // the attacker's simple attack was wasted
            attacked.setCanDodge(false);
            return;
        }

        if (attacker.getAttack() == -1) {
            if (attacker.getSpecialAttack() > attacked.getSpecialDefence())
                attacked.tookDamage(attacker.getSpecialAttack() - attacked.getSpecialDefence());
        } else {
            if (attacker.getAttack() > attacked.getDefence())
                attacked.tookDamage(attacker.getAttack() - attacked.getDefence());
        }
    }
}
