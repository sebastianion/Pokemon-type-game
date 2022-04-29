package items;

import pokemon.Pokemon;

/**
 * Vitamins item
 */
public class Vitamins extends Item {
    public Vitamins(Pokemon pokemon) {
        super(pokemon);
    }

    @Override
    public void equip() {
        super.pokemon.increaseHPBy(2);
        if (super.pokemon.getAttack() != -1)
            super.pokemon.increaseAttackBy(2);
        if (super.pokemon.getSpecialAttack() != -1)
            super.pokemon.increaseSpecialAttack(2);
    }
}
