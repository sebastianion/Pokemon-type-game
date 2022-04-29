package commands;

import pokemon.Pokemon;

/**
 * Command class (command design pattern)
 */
public abstract class Command {
    private String type;
    private String damage = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public abstract void setAbilityFeatures(Pokemon pokemon);

    public abstract void attack(Pokemon attacker, Pokemon attacked);
}
