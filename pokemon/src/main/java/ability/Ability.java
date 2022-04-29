package ability;

/**
 * Ability class
 */
public class Ability {
    private int damage;
    private boolean stun;
    private boolean dodge;
    private int cooldown;
    private int cooldownRealTime = 0;

    public Ability() {}

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean hasStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean hasDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCooldownRealTime() {
        return cooldownRealTime;
    }

    public void setCooldownRealTime(int cooldownRealTime) {
        this.cooldownRealTime = cooldownRealTime;
    }

    // updates the cooldown time if it is needed
    public void updateCooldownRealTime() {
        if (cooldownRealTime > 0)
            this.cooldownRealTime--;
    }

    @Override
    public String toString() {
        return "{damage = " + damage +
                ", hasStun: " + stun +
                ", hasDodge: " + dodge +
                ", cooldown = " + cooldown +
                '}';
    }
}
