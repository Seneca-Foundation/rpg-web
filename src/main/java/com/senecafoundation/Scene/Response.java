package com.senecafoundation.Scene;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
// @Table(name = "response")
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Response 
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String responseText;
    private String resultingSceneIDFromChoce;
    
    public Response(String responseText, String resultingSceneIDFromChoce) 
    {
        this.id = UUID.randomUUID();
        this.responseText = responseText;
        this.resultingSceneIDFromChoce = resultingSceneIDFromChoce;
    }

    public Response() {}

    public UUID getId() 
    {
        return id;
    }

    public void setId(UUID randomUUID) 
    {
        this.id = randomUUID;
    }

    public String getResponseText() {
        return responseText;
    }

    public String getResultingSceneIDFromChoce() {
        return resultingSceneIDFromChoce;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public void setResultingSceneIDFromChoce(String resultingSceneIDFromChoce) {
        this.resultingSceneIDFromChoce = resultingSceneIDFromChoce;
    }
}
