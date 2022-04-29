package pokemon;

import ability.*;
import items.Item;
import items.ItemFactory;

import java.util.List;

/**
 * Pokemon class
 */
public class Pokemon {
    // max number of abilities and items
    public static final int MAX_ABILITIES = 2;
    public static final int MAX_ITEMS = 3;

    private String name;
    private int HP;
    private int HPbackup;
    private int attack = -1;
    private int specialAttack = -1;
    private int defence;
    private int specialDefence;
    private List<Ability> abilities;
    private List<String> items;
    private boolean isStunned = false;
    private boolean canDodge = false;
    private boolean canMoveNexRound = true;

    public Pokemon() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
        this.HPbackup = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public void setSpecialDefence(int specialDefence) {
        this.specialDefence = specialDefence;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    // items are equiped using an ItemFactory instance and calling the equip method
    public void equipItems() {
        ItemFactory itemFactory = ItemFactory.initialize();
        for (String itemName : getItems()) {
            Item item = itemFactory.getItem(ItemFactory.ItemType.valueOf(itemName), this);
            item.equip();
        }
    }

    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public boolean canDodge() {
        return canDodge;
    }

    public void setCanDodge(boolean canDodge) {
        this.canDodge = canDodge;
    }

    public void setHPbackup(int HPbackup) {
        this.HPbackup = HPbackup;
    }

    private int getHPbackup() {
        return HPbackup;
    }

    public boolean canMoveNexRound() {
        return canMoveNexRound;
    }

    public void setCanMoveNexRound(boolean canMoveNexRound) {
        this.canMoveNexRound = canMoveNexRound;
    }

    // resets the attributes after a battle
    public void resetStats() {
        this.HP = this.getHPbackup();
        this.setCanMoveNexRound(true);
        this.setStunned(false);
        this.setCanDodge(false);
        if (this.getAbilities() != null)
            for (Ability ability : this.getAbilities())
                ability.setCooldownRealTime(0);
    }

    public void increaseHPBy(int value) {
        this.HP += value;
    }

    public void increaseAttackBy(int value) {
        this.attack += value;
    }

    public void increaseSpecialAttack(int value) {
        this.specialAttack += value;
    }

    public void increaseDef(int value) {
        this.defence += value;
    }

    public void increaseSpecialDef(int value) {
        this.specialDefence += value;
    }

    // upgrades the Pokemon after winning a battle
    public void increaseAll() {
        this.increaseHPBy(1);
        if (getAttack() != -1)
            this.increaseAttackBy(1);
        else
            this.increaseSpecialAttack(1);
        this.increaseDef(1);
        this.increaseSpecialDef(1);
    }

    public void tookDamage(int value) {
        this.HP -= value;
    }

    // prints details needed for logging the Pokemon's state after round in a battle
    public String state() {
        StringBuilder string = new StringBuilder();
        if (this.HP > 0)
            string.append(name).append(": HP = ").append(HP);
        else
            string.append(name).append(": dead");

        if (getAbilities() != null && this.getHP() > 0)
            string.append("\n\t\t\tAbility 1 cooldown: ")
                    .append(getAbilities().get(0).getCooldownRealTime())
                    .append("\n\t\t\tAbility 2 cooldown: ")
                    .append(getAbilities().get(1).getCooldownRealTime())
                    .append("\n\t\tis stunned the next round?: ")
                    .append(isStunned)
                    .append("\n\t\tdodged?: ")
                    .append(canDodge);

        return string.toString();
    }

    // computes the sum of all stats
    public int getSumOfStats() {
        // + 1 because either attack or special attack is -1
        return this.getHP() + this.attack + this.specialAttack + this.defence + this.specialDefence + 1;
    }

    @Override
    public String toString() {
        return  name +
                ": HP = " + HP +
                ", attack = " + attack +
                ", specialAttack = " + specialAttack +
                ", defence = " + defence +
                ", specialDefence = " + specialDefence +
                ",\n\t| abilities = " + abilities +
                ",\n\t| items = " + items +
                "\n";
    }
}
