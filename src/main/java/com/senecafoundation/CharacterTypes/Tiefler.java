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
public class Tiefler extends Character 
{
    //Variables
    @Column(nullable = true)
    private int oneHandedBonus;
    @Column(nullable = true)
    private int archeryBonus;

    //Encapsulation
    public int getOneHandedBonus()
    {
        return this.oneHandedBonus;
    }
    public void setOneHandedBonus(int newOneHandedBonus)
    {
        this.oneHandedBonus = newOneHandedBonus;
    }

    public int getArcheryBonus()
    {
        return this.archeryBonus;
    }
    public void setArcheryBonus(int newArcheryBonus)
    {
        this.archeryBonus = newArcheryBonus;
    }

    //Constructor
    public Tiefler(String name, int age, String sex, int oneHanded, int archery, IDataHandler dataHandler)
    {
        super(name, age, sex, dataHandler, 50, 50, 50);
        this.oneHandedBonus = oneHanded;
        this.archeryBonus = archery;
    }

    //Method
    public String PlayerDetails() //override
    {
        return  (this.getName() + " One Handed: " + oneHandedBonus + " Archery: " + archeryBonus);
    }

    public String toString() 
    {
        return super.toString() + "," + this.getOneHandedBonus() + "," + this.getArcheryBonus();
    }
}
