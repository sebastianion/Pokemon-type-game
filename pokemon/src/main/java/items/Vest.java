package items;

import pokemon.Pokemon;

/**
 * Vest item
 */
public class Vest extends Item {
    public Vest(Pokemon pokemon) {
        super(pokemon);
    }

    @Override
    public void equip() {
        super.pokemon.increaseHPBy(10);
    }
}
