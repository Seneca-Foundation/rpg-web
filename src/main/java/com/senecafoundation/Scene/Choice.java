package com.senecafoundation.Scene;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
// @Table(name = "choice")
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Choice
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    //Variables
    private String id;
    private int choiceOptionNumber;
    private String choiceText;
    private Response response;

    public Choice(int choiceOptionNumber, String choiceText, Response response) 
    {
        this.id = UUID.randomUUID().toString();
        this.choiceOptionNumber = choiceOptionNumber;
        this.response = response;
        this.choiceText = choiceText;
    }

    public Choice() {}

    public String getId() 
    {
        return id;
    }

    public void setId(UUID randomUUID) 
    {
        
    }

    public int getChoiceOptionNumber() {
        return choiceOptionNumber;
    }

    public Response getResponse() {
        return response;
    }

    public String getChoiceText() {
        return choiceText;
    }

    
}
