package com.model;

import com.enums.ability;
import com.enums.alignment;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class heroTest {

    private hero baseHero;
    private int[] modifierExpectations;

    @Before
    public void setup() {
        baseHero = new hero();
        modifierExpectations = new int[]{-5, -4, -4,-3,-3,-2,-2,-1,-1,0,0,1,1,2,2,3,3,4,4,5};
    }

    @Test
    public void testGetName() {
        assertEquals("", baseHero.getName());
    }

    @Test
    public void testSetName() {
        baseHero.setName("Gimli");

        assertEquals("Gimli", baseHero.getName());


    }
    @Test
    public void testSetAlignment() {
        baseHero.setHeroAlignment(alignment.EVIL);
        assertEquals(alignment.EVIL, baseHero.getHeroAlignment());
    }

    @Test
    public void testGetAlignment() {
        assertEquals(alignment.NEUTRAL, baseHero.getHeroAlignment());
    }

    @Test
    public void testGetArmorClass() {
        assertEquals(10, baseHero.getArmorClass());
    }

    @Test
    public void testSetArmorClass() {
        baseHero.setArmorClass(15);
        assertEquals(15, baseHero.getArmorClass());
    }

    @Test
    public void testGetHitPoints() {
        assertEquals(5, baseHero.getCurrentHitPoints());
    }

    @Test
    public void testGetHitPointsPositiveConstitution() {
        baseHero.setAbilityScore(ability.CONSTITUTION, 20);
        baseHero.setCurrentHitPoints(baseHero.getBaseHitPoints());
        assertEquals(10, baseHero.getCurrentHitPoints());
    }

    @Test
    public void testGetHitPointsNegativeConstitution() {
        baseHero.setAbilityScore(ability.CONSTITUTION, 1);
        baseHero.setCurrentHitPoints(baseHero.getBaseHitPoints());
        assertEquals(1, baseHero.getCurrentHitPoints());
    }

    @Test
    public void testSetCurretnHitPoints() {
        baseHero.setCurrentHitPoints(15);
        assertEquals(15, baseHero.getCurrentHitPoints());
    }

    @Test
    public void testCanAttack_NoHit() {
        assertFalse(baseHero.canBeAttacked(9));
    }

    @Test
    public void testCanAttack_HitMeetAC() {
        assertTrue(baseHero.canBeAttacked(10));
    }

    @Test
    public void testCanAttack_HitBeatAC() {
        assertTrue(baseHero.canBeAttacked(11));
    }

    @Test
    public void testCanAttack_NegativeModifier() {
        baseHero.setAbilityScore(ability.DEXTERITY, 1);
        assertTrue(baseHero.canBeAttacked(10));
    }

    @Test
    public void testCanAttack_PositiveModifier() {
        baseHero.setAbilityScore(ability.DEXTERITY, 20);
        assertFalse(baseHero.canBeAttacked(10));
    }

    @Test
    public void testIsAlive_isAlive() {
        assertTrue(baseHero.isAlive());
    }

    @Test
    public void testIsAlive_isDead() {
        baseHero.setCurrentHitPoints(0);
        assertFalse(baseHero.isAlive());
    }

    @Test
    public void testTakeDamage() {
        baseHero.takeDamage(1);
        assertEquals(4, baseHero.getCurrentHitPoints());
    }

    @Test
    public void testGetAbility_Strength() {
        assertEquals(new Integer(10), baseHero.getAbilityScore(ability.STRENGTH));
    }

    @Test
    public void testGetAbility_Dexterity() {
        assertEquals(new Integer(10), baseHero.getAbilityScore(ability.DEXTERITY));
    }

    @Test
    public void testGetAbility_Constitution() {
        assertEquals(new Integer(10), baseHero.getAbilityScore(ability.CONSTITUTION));
    }

    @Test
    public void testGetAbility_Wisdom() {
        assertEquals(new Integer(10), baseHero.getAbilityScore(ability.WISDOM));
    }

    @Test
    public void testGetAbility_Intelligence() {
        assertEquals(new Integer(10), baseHero.getAbilityScore(ability.INTELLIGENCE));
    }

    @Test
    public void testGetAbility_Charisma() {
        assertEquals(new Integer(10), baseHero.getAbilityScore(ability.CHARISMA));
    }

    @Test
    public void testGetAbility_null() {
        assertNull(baseHero.getAbilityScore(null));
    }

    public void testGetAbilityModifier(int expected) {
        assertEquals(expected, baseHero.getAbilityModifier(ability.STRENGTH));
    }

    @Test
    public void testGetAbilityModifiers() {
        for(int j = 0; j < modifierExpectations.length; j++) {
            baseHero.setAbilityScore(ability.STRENGTH,j+1);
            this.testGetAbilityModifier(modifierExpectations[j]);
        }
    }

    @Test
    public void testGetExperience(){
        baseHero.setExperiencePoints(1);
        assertNotNull(baseHero.getExperiencePoints());
    }

    @Test
    public void testSetExperience(){
        assertEquals(0, baseHero.getExperiencePoints());
    }


    @Test
    public void testGetLevel(){
        assertEquals(1, baseHero.getLevel());
    }

    @Test
    public void testSetLevel(){
        baseHero.setLevel(2);
        assertEquals(2, baseHero.getLevel());
    }

    @Test
    public void testLevel1(){
        baseHero.setExperiencePoints(2);
        assertEquals(1, baseHero.getLevel());
    }

    @Test
    public void testLevel1HighXP(){
        baseHero.setExperiencePoints(999);
        assertEquals(1, baseHero.getLevel());
    }

    @Test
    public void testLevel2(){
        baseHero.setExperiencePoints(1000);
        assertEquals(2, baseHero.getLevel());
    }

    @Test
    public void testLevel3(){
        baseHero.setExperiencePoints(1000);
        baseHero.setExperiencePoints(baseHero.getExperiencePoints() + 1000);
        assertEquals(3, baseHero.getLevel());
    }

}
