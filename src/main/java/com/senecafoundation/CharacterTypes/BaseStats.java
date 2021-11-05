package com.senecafoundation.CharacterTypes;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseStats 
{  
    //Encapsulation
    private int health;
    private int mana;
    private int stamina;

    //Accessors and Mutators
    public int getHealth()
    {
        return this.health;
    }
    public void setHealth(int newHealth)
    {
        this.health = newHealth;
    }

    public int getMana()
    {
        return this.mana;
    }
    public void setMana(int newMana)
    {
        this.mana = newMana;
    }
    
    public int getStamina()
    {
        return this.stamina;
    }
    public void setStamina(int newStamina)
    {
        this.stamina = newStamina;
    }

    //Construtor
    public BaseStats(int health, int mana, int stamina)
    {
        this.health = health;
        this.mana = mana;
        this.stamina = stamina;
    }

    public BaseStats() {
    }
    //Method
    public String PrintStats()
    {
        return("Health: " + health + "Mana: " + mana + "Stamina: " + stamina);
    }
}
