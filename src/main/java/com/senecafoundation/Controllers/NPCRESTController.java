package com.senecafoundation.Controllers;

import java.util.UUID;
import java.util.List;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.DataHandler.NpcDataHandler;
import com.senecafoundation.NPCTypes.NPC;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class NPCRESTController 
{
    @Autowired
    NpcDataHandler dataHandler;

    @PostMapping("/Npc")
    NPC newNpc(@RequestBody NPC newNpc) 
    {
        dataHandler.Create(newNpc);
        return newNpc;
    }

    @GetMapping("/Npc/{id}")
    NPC findNpc(@PathVariable String Id) throws Exception 
    {
        NPC foundNpc = (NPC) dataHandler.Read(UUID.fromString(Id));
        return foundNpc;
    }

    @GetMapping("/Npc")
    List<ICharacter> NpcList() 
    {
        return dataHandler.ReadAll();
    }

    @PutMapping("/Npc/{id}")
    NPC updateNpc(@RequestBody NPC newNpc, @PathVariable String Id) throws Exception
    {
        NPC npc = (NPC) dataHandler.Read(UUID.fromString(Id));
        if(npc != null)
        {
            newNpc.setId(npc.getId());
            return (NPC)dataHandler.Update(newNpc);
        }
        else
        {
            throw new Exception("No Npc found with id: " + Id);
        }
        
    }

    @DeleteMapping("/Npc/{id}")
    void deleteNpc(@PathVariable String Id) throws Exception 
    {
        dataHandler.Delete(UUID.fromString(Id));
    }

}
