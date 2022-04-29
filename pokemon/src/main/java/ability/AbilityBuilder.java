package ability;

/**
 * Builder class for ability (builder design pattern)
 */
public class AbilityBuilder {
    private Ability ability = new Ability();

    public AbilityBuilder withDamage(int damage) {
        ability.setDamage(damage);
        return this;
    }

    public AbilityBuilder withStun(boolean stun) {
        ability.setStun(stun);
        return this;
    }

    public AbilityBuilder withDodge(boolean dodge) {
        ability.setDodge(dodge);
        return this;
    }

    public AbilityBuilder withCooldown(int cooldown) {
        ability.setCooldown(cooldown);
        return this;
    }

    public Ability build() {
        return ability;
    }
}
