package com.senecafoundation.Controllers;

import java.util.UUID;
import java.util.List;
import com.senecafoundation.DataHandler.ChoiceDataHandler;
import com.senecafoundation.DataHandler.ResponseDataHandler;
import com.senecafoundation.Scene.Choice;
import com.senecafoundation.Scene.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ChoiceRESTController 
{
    @Autowired
    ChoiceDataHandler<Choice> dataHandler;
    @Autowired
    ResponseDataHandler<Response> responseDataHandler;

    @PostMapping("/choice")
    Choice newChoice(@RequestBody Choice newChoice) 
    {
        dataHandler.Create(newChoice);
        return newChoice;
    }

    @GetMapping("/choice/{id}")
    Choice findChoice(@PathVariable String Id) throws Exception 
    {
        Choice foundChoice = (Choice) dataHandler.Read(UUID.fromString(Id));
        return foundChoice;
    }

    @GetMapping("/choice")
    List<Choice> choiceList() 
    {
        return dataHandler.ReadAll();
    }

    @PutMapping("/choice/{id}")
    Choice updateChoice(@RequestBody Choice newChoice, @PathVariable String Id) throws Exception
    {
        Choice choice = (Choice) dataHandler.Read(UUID.fromString(Id));
        if(choice != null)
        {
            newChoice.setId(choice.getId());
            return dataHandler.Update(newChoice);
        }
        else
        {
            throw new Exception("No Choice found with id: " + Id);
        }
    }

    @DeleteMapping("/choice/{id}")
    void deleteChoice(@PathVariable String Id) throws Exception 
    {
        dataHandler.Delete(UUID.fromString(Id));
    }

}
