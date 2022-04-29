package items;

import pokemon.Pokemon;

/**
 * Coat item
 */
public class Coat extends Item {
    public Coat(Pokemon pokemon) {
        super(pokemon);
    }

    @Override
    public void equip() {
        super.pokemon.increaseSpecialDef(3);
    }
}
