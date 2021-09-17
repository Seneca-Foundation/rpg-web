package com.senecafoundation.DataHandler;

import java.util.UUID;

import javax.transaction.Transactional;

import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.ShadowElf;
import com.senecafoundation.Scene.Choice;
import com.senecafoundation.Scene.Response;
import com.senecafoundation.Scene.Scenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RepoDataHandler extends DataHandler {

    @Autowired
    private ShadowElfRepository shadowElfRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private ScenarioRepository scenarioRepository;

    @Transactional
    @Override
    public boolean Create(ICharacter character) {
        ShadowElf testCharacter = (ShadowElf) character;
        testCharacter.setId(UUID.randomUUID());
        this.shadowElfRepository.save(testCharacter);
        return true;
    }

    public boolean CreateChoice(Choice choice) 
    {
        Choice testChoice = (Choice) choice;
        testChoice.setId(UUID.randomUUID());
        this.choiceRepository.save(testChoice);
        return true;
    }

    public boolean CreateResponse(Response response) 
    {
        Response testResponse = (Response) response;
        testResponse.setId(UUID.randomUUID());
        this.responseRepository.save(testResponse);
        return true;
    }

    public boolean CreateScenario(Scenario scenario) 
    {
        Scenario testScenario = (Scenario) scenario;
        testScenario.setId(UUID.randomUUID());
        this.scenarioRepository.save(testScenario);
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

    

}
