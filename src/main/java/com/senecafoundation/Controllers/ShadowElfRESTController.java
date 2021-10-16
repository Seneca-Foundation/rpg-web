package com.senecafoundation.Controllers;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.ShadowElf;
import com.senecafoundation.DataHandler.ShadowElfDataHandler;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class ShadowElfRESTController 
{
    @Autowired
    ShadowElfDataHandler dataHandler;

    @PostMapping("/shadowelfs")
    ShadowElf newShadowelf(@RequestBody ShadowElf newShadowelf) 
    {
        dataHandler.Create(newShadowelf);
        return newShadowelf;
    }

    @GetMapping("/shadowelfs/{Id}")
    ShadowElf findShadowelf(@PathVariable String Id) throws Exception 
    {
        ShadowElf foundShadowelf = (ShadowElf) dataHandler.Read(UUID.fromString(Id));
        return foundShadowelf;
    }

    @GetMapping("/shadowelfs")
    List<ShadowElf> shadowelfList() 
    {
        List<ShadowElf> shadowElfs = new ArrayList<ShadowElf>();
        for (ICharacter shadowElf : dataHandler.ReadAll()) {
            shadowElfs.add((ShadowElf) shadowElf);
        }
        return shadowElfs;
    }

    @PutMapping("/shadowelfs/{Id}")
    ShadowElf updateShadowelf(@RequestBody ShadowElf newShadowelf, @PathVariable String Id) throws Exception
    {
        ShadowElf shadowelf = (ShadowElf) dataHandler.Read(UUID.fromString(Id));
        if(shadowelf != null)
        {
            newShadowelf.setId(shadowelf.getId());
            return (ShadowElf) dataHandler.Update(newShadowelf);
        }
        else
        {
            throw new Exception("No Choice found with id: " + Id);
        }
    }

    @DeleteMapping("/shadowelfs/{Id}")
    void deleteShadowelf(@PathVariable String Id) throws Exception 
    {
        dataHandler.Delete(UUID.fromString(Id));
    }
    
}
