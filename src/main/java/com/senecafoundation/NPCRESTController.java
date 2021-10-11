package com.senecafoundation;

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
    List<ICharacter> all() 
    {
        return dataHandler.ReadAll();
    }

    @DeleteMapping("/Npc/{id}")
    void deleteNpc(@PathVariable String Id) throws Exception 
    {
        dataHandler.Delete(UUID.fromString(Id));
    }

}
