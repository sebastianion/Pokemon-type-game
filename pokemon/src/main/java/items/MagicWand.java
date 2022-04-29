package items;

import pokemon.Pokemon;

/**
 * Magic wand item
 */
public class MagicWand extends Item {
    public MagicWand(Pokemon pokemon) {
        super(pokemon);
    }

    @Override
    public void equip() {
        if (super.pokemon.getSpecialAttack() != -1)
            super.pokemon.increaseSpecialAttack(3);
    }
}
