package com.senecafoundation.NPCTypes;
import com.senecafoundation.CharacterTypes.Character;
import com.senecafoundation.DataHandler.FileDataHandler;
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
    public NPC(String name, int age, String sex, String[] phrases)
    {
        super(name, age, sex, new FileDataHandler("./" + name + ".CSV"), 50, 50, 50);
        this.phrases = phrases;
    }
    public NPC() 
    {
        super();
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