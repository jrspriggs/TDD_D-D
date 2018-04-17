package com.model;

import com.enums.ability;
import com.enums.alignment;

import java.util.HashMap;

public class hero {

    private String name;
    private alignment heroAlignment;
    private int armorClass;
    private int baseHitPoints;
    private int currentHitPoints;
    private int experiencePoints;
    private int level;
    private HashMap<ability, Integer> abilities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public hero() {
        this.setName("");
        this.setHeroAlignment(alignment.NEUTRAL);
        this.abilities = new HashMap<ability, Integer>();
        this.abilities.put(ability.STRENGTH, 10);
        this.abilities.put(ability.DEXTERITY, 10);
        this.abilities.put(ability.WISDOM, 10);
        this.abilities.put(ability.CONSTITUTION, 10);
        this.abilities.put(ability.INTELLIGENCE, 10);
        this.abilities.put(ability.CHARISMA, 10);
        this.setLevel(1);
        this.setArmorClass(10);
        this.setBaseHitPoints(5);
        this.setCurrentHitPoints(this.getBaseHitPoints());
        this.setExperiencePoints(0);
    }

    public void setHeroAlignment(alignment heroAlignment) {
        this.heroAlignment = heroAlignment;
    }

    public alignment getHeroAlignment() {
        return this.heroAlignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getBaseHitPoints() {
        int hitPoints = 5;
        hitPoints += this.getAbilityModifier(ability.CONSTITUTION);
        if(hitPoints <= 0) {
            hitPoints = 1;
        }
        return hitPoints * this.level;
    }

    public void setBaseHitPoints(int hitPoints) {
        this.baseHitPoints = hitPoints;
    }

    public boolean canBeAttacked(int dieRoll) {
        int modifiedAC = this.getArmorClass() + this.getAbilityModifier(ability.DEXTERITY);
        if(dieRoll >= modifiedAC) {
            return true;
        }
        return false;
    }

    public boolean isAlive() {
        if(this.getCurrentHitPoints() > 0) {
            return true;
        }
        return false;
    }

    public void takeDamage(int damage) {
        this.setCurrentHitPoints(this.getCurrentHitPoints() - damage);
    }

    public Integer getAbilityScore(ability targetAbility) {
        return this.abilities.get(targetAbility);
    }

    public int getAbilityModifier(ability targetAbility) {
        return Math.round((this.getAbilityScore(targetAbility)/2) - 5);
    }

    public void setAbilityScore(ability targetAbility, int abilityScore) {
        this.abilities.put(targetAbility, abilityScore);
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
        int xpLevel = Math.round(this.experiencePoints / 1000);
        xpLevel++;
        if(xpLevel > this.level) {
            this.setLevel(xpLevel);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;

    }
}
