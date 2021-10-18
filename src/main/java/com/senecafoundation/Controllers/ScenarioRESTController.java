package com.senecafoundation.Controllers;

import java.util.UUID;
import java.util.List;
import com.senecafoundation.DataHandler.SceneDataHandler;
import com.senecafoundation.Scene.Scenario;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ScenarioRESTController 
{
    @Autowired
    SceneDataHandler<Scenario> dataHandler;

    @PostMapping("/scenarios")
    Scenario newScenario(@RequestBody Scenario newScenario) 
    {
        dataHandler.Create(newScenario);
        return newScenario;
    }

    @GetMapping("/scenarios/{Id}")
    Scenario findScenario(@PathVariable String Id) throws Exception 
    {
        Scenario foundScenario = (Scenario) dataHandler.Read(UUID.fromString(Id));
        return foundScenario;
    }

    @GetMapping("/scenarios")
    List<Scenario> scenarioList() 
    {
        return dataHandler.ReadAll();
    }

    @PutMapping("/scenarios/{Id}")
    Scenario updateScenario(@RequestBody Scenario newScenario, @PathVariable String Id) throws Exception
    {
        Scenario scenario = (Scenario) dataHandler.Read(UUID.fromString(Id));
        if(scenario != null)
        {
            newScenario.setId(scenario.getId());
            return dataHandler.Update(newScenario);
        }
        else
        {
            throw new Exception("No Choice found with id: " + Id);
        }
    }

    @DeleteMapping("/scenarios/{Id}")
    void deleteScenario(@PathVariable String Id) throws Exception 
    {
        dataHandler.Delete(UUID.fromString(Id));
    }
    
}
