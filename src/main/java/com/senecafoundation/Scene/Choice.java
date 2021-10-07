package com.senecafoundation.Scene;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToOne;
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
    private UUID id;
    private int choiceOptionNumber;
    private String choiceText;

    @OneToOne
    private Response response;

    public Choice(int choiceOptionNumber, String choiceText, Response response) 
    {
        this.id = UUID.randomUUID();
        this.choiceOptionNumber = choiceOptionNumber;
        this.response = response;
        this.choiceText = choiceText;
    }

    public Choice() {}

    public UUID getId() 
    {
        return id;
    }

    public void setId(UUID randomUUID) 
    {
        this.id = randomUUID;
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
