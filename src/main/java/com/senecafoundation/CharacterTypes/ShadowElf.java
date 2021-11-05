package com.senecafoundation.CharacterTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.senecafoundation.DataHandler.Interfaces.IDataHandler;

@Entity
@Table(name = "character")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ShadowElf extends Character 
{
    //Variables
    @Column(nullable = true)
    private int destructionBonus;
    @Column(nullable = true)
    private int alterationBonus;

    //Encapsulation
    public int getDestructionBonus()
    {
        return this.destructionBonus;
    }
    public void setDestructionBonus(int newDestructionBonus)
    {
        this.destructionBonus = newDestructionBonus;
    }

    public int getAlterationBonus()
    {
        return this.alterationBonus;
    }
    public void setAlterationBonus(int newAlterationBonus)
    {
        this.alterationBonus = newAlterationBonus;
    }

    //Constructor
    public ShadowElf(String name, int age, String sex, int destruction, int alteration)
    {
        super(name, age, sex, 30, 80, 80);
        this.destructionBonus = destruction;
        this.alterationBonus = alteration;
    }

    public ShadowElf() {
        super();
    }
    //Method
    public String PlayerDetails() //override
    {
        return  (this.getName() + " Destruction: " + destructionBonus + " Alteration: " + alterationBonus);
    }

    public String toString() 
    {
        return super.toString() + "," + this.getDestructionBonus() + "," + this.getAlterationBonus();
    }
}