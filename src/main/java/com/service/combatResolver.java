package com.service;

import com.enums.ability;
import com.model.hero;

public class combatResolver {
    private static combatResolver instance = null;
    protected combatResolver() {
        // Exists only to defeat instantiation.
    }
    public static combatResolver getInstance() {
        if(instance == null) {
            instance = new combatResolver();
        }
        return instance;
    }

    public void combat(int dieRoll, hero attacker, hero defender) {
        int attackRoll = this.modifyDie(dieRoll, ability.STRENGTH, attacker);
        //if defender canBeAttacked attacker
        if(defender.canBeAttacked(attackRoll) || dieRoll == 20) {
            this.dealDamage(dieRoll, attacker, defender);
            attacker.setExperiencePoints(attacker.getExperiencePoints()+10);
        }
    }

    public void dealDamage(int dieRoll, hero attacker, hero defender) {
        int damage = 1;
        int strengthModifier = attacker.getAbilityModifier(ability.STRENGTH);
        damage += strengthModifier;
        if(damage <= 0) {
            damage = 1;
        }

        if(dieRoll == 20) {
            defender.takeDamage(damage);
            if(strengthModifier < 0) {
                return;
            }
        }
        defender.takeDamage(damage);

    }

    public int modifyDie(int dieRoll, ability targetAbility, hero targetHero){
        return dieRoll + targetHero.getAbilityModifier(targetAbility);
    }
}
