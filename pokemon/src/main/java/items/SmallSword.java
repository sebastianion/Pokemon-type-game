package items;

import pokemon.Pokemon;

/**
 * Small sword item
 */
public class SmallSword extends Item {
    public SmallSword(Pokemon pokemon) {
        super(pokemon);
    }

    @Override
    public void equip() {
        if (super.pokemon.getAttack() != -1)
            super.pokemon.increaseAttackBy(3);
    }
}
