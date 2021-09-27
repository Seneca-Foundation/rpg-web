package com.senecafoundation.CharacterTypes;

import com.senecafoundation.DataHandler.Interfaces.IDataHandler;

public class Halfling extends Character 
{
    //Variables
    private int archeryBonus;
    private int pickPocketBonus;

    //Encapsulation
    public int getArcheryBonus()
    {
        return this.archeryBonus;
    }
    public void setArcheryBonus(int newArcheryBonus)
    {
        this.archeryBonus = newArcheryBonus;
    }

    public int getPickPocketBonus()
    {
        return this.pickPocketBonus;
    }
    public void setPickPocketBonus(int newPickPocketBonus)
    {
        this.pickPocketBonus = newPickPocketBonus;
    }

    //Constructor
    public Halfling(String name, int age, String sex, int archery, int pickpocket, IDataHandler dataHandler)
    {
        super(name, age, sex, dataHandler, 30, 50, 60);
        this.archeryBonus = archery;
        this.pickPocketBonus = pickpocket;
    }

    //Method
    public String PlayerDetails() //override
    {
        return  (this.getName() + " Archery: " + archeryBonus + " Pickpocket: " + pickPocketBonus);
    }

    public String toString() 
    {
        return super.toString() + "," + this.getArcheryBonus() + "," + this.getPickPocketBonus();
    }
}
