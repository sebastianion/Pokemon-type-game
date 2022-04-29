package items;

import pokemon.Pokemon;

/**
 * Christmas tree item
 */
public class XmasTree extends Item {
    public XmasTree(Pokemon pokemon) {
        super(pokemon);
    }

    @Override
    public void equip() {
        if (super.pokemon.getAttack() != -1)
            super.pokemon.increaseAttackBy(3);
        super.pokemon.increaseDef(1);
    }
}
