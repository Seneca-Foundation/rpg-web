package com.senecafoundation.DataHandler;

import java.util.UUID;

import javax.transaction.Transactional;

import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.ShadowElf;
import com.senecafoundation.Scene.Choice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoDataHandler extends DataHandler {

    @Autowired
    private ShadowElfRepository shadowElfRepository;

    @Transactional
    @Override
    public boolean Create(ICharacter character) {
        ShadowElf testCharacter = (ShadowElf) character;
        testCharacter.setId(UUID.randomUUID());
        this.shadowElfRepository.save(testCharacter);
        return true;
    }

    @Override
    public ICharacter Read(UUID id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICharacter Update(ICharacter character) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean Delete(UUID id) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean Create(Choice choice) 
    {
        return true;
    }

    

}
