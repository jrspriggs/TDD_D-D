package com.service;

import com.enums.ability;
import com.model.hero;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class combatResolverTest {
    private hero attacker;
    private hero defender;
    private combatResolver combat;
    private int[] modifierExpectations;

    @Before
    public void setup() {
        attacker = new hero();
        defender = new hero();
        combat = combatResolver.getInstance();
        modifierExpectations = new int[]{-5, -4, -4,-3,-3,-2,-2,-1,-1,0,0,1,1,2,2,3,3,4,4,5};
    }

    public void testModifyDieRoll(int expected, int dieRoll) {
        assertEquals(expected, combat.modifyDie(dieRoll, ability.STRENGTH, attacker));
    }

    @Test
    public void testModifyDieRolls() {
        for(int j = 0; j < modifierExpectations.length; j++) {
            attacker.setAbilityScore(ability.STRENGTH,j+1);
            this.testModifyDieRoll(modifierExpectations[j]+10,10);
        }
    }


    @Test
    public void testCombat_noDamage() {
        combat.combat(9, attacker, defender);
        assertEquals(5, defender.getCurrentHitPoints());
    }

    @Test
    public void testCombat_normalDamage() {
        combat.combat(10, attacker, defender);
        assertEquals(4, defender.getCurrentHitPoints());
    }

    @Test
    public void testCombat_Nat20DoubleDamage() {
        combat.combat(20, attacker, defender);
        assertEquals(3, defender.getCurrentHitPoints());
    }

    @Test
    public void testCombat_noXP() {
        combat.combat(9, attacker, defender);
        assertEquals(0, attacker.getExperiencePoints());
    }

    @Test
    public void testCombat_normalXP() {
        combat.combat(10, attacker, defender);
        assertEquals(10, attacker.getExperiencePoints());
    }

    @Test
    public void testCombat_CritNormalXP() {
        combat.combat(20, attacker, defender);
        assertEquals(10, attacker.getExperiencePoints());
    }

    @Test
    public void testCombat_normalXPMulitpleHits() {
        combat.combat(10, attacker, defender);
        combat.combat(10, attacker, defender);
        assertEquals(20, attacker.getExperiencePoints());
    }

    public void testDamage(int dieRoll, int strengthScore, int expectedDamage) {
        attacker.setAbilityScore(ability.STRENGTH,strengthScore);
        combat.dealDamage(dieRoll, attacker, defender);
        assertEquals(5 - expectedDamage, defender.getCurrentHitPoints());

    }

    @Test
    public void testDealDamageNoModifier() {
            this.testDamage(10, 10, 1);
    }

    @Test
    public void testDealDamageNegativeModifier() {
        this.testDamage(10, 1, 1);
    }

    @Test
    public void testDealDamagePositiveModifier() {
        this.testDamage(10, 20, 6);
    }

    @Test
    public void testDealDamageNoModifierCriticalHit() {
        this.testDamage(20, 10, 2);
    }

    @Test
    public void testDealDamageNegativeModifierCriticalHit() {
        this.testDamage(20, 1, 1);
    }

    @Test
    public void testDealDamagePositiveModifierCriticalHit() {
        this.testDamage(20, 20, 12);
    }
    @Test
    public void testCanAttackLowStrength(){
        attacker.setAbilityScore(ability.STRENGTH,1);
        combat.combat(10, attacker, defender);
        assertEquals(5, defender.getCurrentHitPoints());
    }

    @Test
    public void testCanAttackHighStrength(){
        attacker.setAbilityScore(ability.STRENGTH,20);
        combat.combat(10, attacker, defender);
        assertNotEquals(5, defender.getCurrentHitPoints());
    }

    @Test
    public void testCanAttackLowStrengthCritical(){
        attacker.setAbilityScore(ability.STRENGTH,1);
        combat.combat(20, attacker, defender);
        assertNotEquals(5, defender.getCurrentHitPoints());
    }

}
