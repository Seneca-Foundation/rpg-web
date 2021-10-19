package com.senecafoundation.NPCTypes;
import com.senecafoundation.CharacterTypes.Character;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Npc")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class NPC extends Character implements INPC
{
    private String[] phrases;
    public NPC(String name, int age, String sex, int health, int mana, int stamina, String[] phrases)
    {
        super(name, age, sex, health, mana, stamina);
        this.phrases = phrases;
    }
    public NPC() 
    {
        super();
    }
    public String[] getPhrases() {
        return phrases;
    }
    public void setPhrases(String[] phrases) {
        this.phrases = phrases;
    }
    //Methods
    public String PrintPhrases()
    {
        Random rand = new Random();
        int upperbound = this.phrases.length;
        int randomNumber = rand.nextInt(upperbound);
        return(this.getName() + ": " + this.phrases[randomNumber]);
    }
}