package items;

import pokemon.Pokemon;

/**
 * Factory class for Item (factory design pattern)
 */
public class ItemFactory {
    public static ItemFactory instance;

    private ItemFactory() {}

    public static ItemFactory initialize() {
        if (instance == null)
            instance = new ItemFactory();
        return instance;
    }

    // types of items
    public static enum ItemType {
        Shield, Vest, SmallSword, MagicWand,
        Vitamins, XmasTree, Coat
    }

    // get an item
    public Item getItem(ItemType type, Pokemon pokemon) {
        return switch (type) {
            case Shield -> new Shield(pokemon);
            case Vest -> new Vest(pokemon);
            case SmallSword -> new SmallSword(pokemon);
            case MagicWand -> new MagicWand(pokemon);
            case Vitamins -> new Vitamins(pokemon);
            case XmasTree -> new XmasTree(pokemon);
            case Coat -> new Coat(pokemon);
            default -> throw new IllegalArgumentException("Invalid object.");
        };
    }
}
