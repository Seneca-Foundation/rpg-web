package com.senecafoundation.CharacterTypes;
import com.senecafoundation.DataHandler.Interfaces.IDataHandler;
public class Viking extends Character 
{
    //Variables
    private int twoHandedSwordBonus;
    private int speechBonus;

    //Encapsulation
    public int getTwoHandedSwordBonus()
    {
        return this.twoHandedSwordBonus;
    }
    public void setTwoHandedSwordBonus(int newTwoHandedSwordBonus)
    {
        this.twoHandedSwordBonus = newTwoHandedSwordBonus;
    }

    public int getSpeechBonus()
    {
        return this.speechBonus;
    }
    public void setSpeechBonus(int newSpeechBonus)
    {
        this.speechBonus = newSpeechBonus;
    }

    //Constructor
    public Viking(String name, int age, String sex, int twoHandedSwordB, int speechB, IDataHandler dataHandler)
    {
        super(name, age, sex, dataHandler, 65, 20, 85);
        this.twoHandedSwordBonus = twoHandedSwordB;
        this.speechBonus = speechB;
    }

    //Method
    public String PlayerDetails() //override
    {
        return  (this.getName() + " Two-Handed: " + twoHandedSwordBonus + " Speech: " + speechBonus);
    }

    public String toString() 
    {
        return super.toString() + "," + this.getTwoHandedSwordBonus()+ "," + this.getSpeechBonus();
    }
}
