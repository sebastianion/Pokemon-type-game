package items;

import pokemon.Pokemon;

/**
 * Shield item
 */
public class Shield extends Item {
    private final int def = 2;
    private final int specialDef = 2;

    public Shield(Pokemon pokmon) {
        super(pokmon);
    }

    @Override
    public void equip() {
        super.pokemon.increaseDef(2);
        super.pokemon.increaseSpecialDef(2);
    }
}
