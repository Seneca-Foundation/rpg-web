package com.senecafoundation.Scene;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Response 
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    //Variables
    private String id;
    private String responseText;
    private String resultingSceneIDFromChoce;
    
    public Response(String responseText, String resultingSceneIDFromChoce) {
        this.id = UUID.randomUUID().toString();
        this.responseText = responseText;
        this.resultingSceneIDFromChoce = resultingSceneIDFromChoce;
    }

    public Response() {}

    public String getId() 
    {
        return id;
    }

    public String getResultingSceneIDFromChoice() 
    {
        return resultingSceneIDFromChoce;
    }

    public String getResponseText() 
    {
        return responseText;
    }
}
