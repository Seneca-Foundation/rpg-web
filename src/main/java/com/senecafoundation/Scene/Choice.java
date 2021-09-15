package com.senecafoundation.Scene;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Choice
{
    //Variables
    private String id;
    private int choiceOptionNumber;
    private String choiceText;
    private Response response;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

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

    public int getChoiceOptionNumber() 
    {
        return choiceOptionNumber;
    }

    public Response getResponse() 
    {
        return response;
    }

    public String getChoiceText() 
    {
        return choiceText;
    }
}
