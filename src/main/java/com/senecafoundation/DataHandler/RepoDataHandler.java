package com.senecafoundation.DataHandler;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.senecafoundation.CharacterTypes.Character;
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
    @Autowired
    private CharacterRepository characterRepository;

    @Transactional
    @Override
    public boolean Create(ICharacter character) {
        ShadowElf testCharacter = (ShadowElf) character;
        testCharacter.setId(UUID.randomUUID());
        this.shadowElfRepository.save(testCharacter);
        return true;
    }

    public boolean CreateCharacter(Character character) {
        Character testCharacter =  character;
        testCharacter.setId(UUID.randomUUID());
        this.characterRepository.save(testCharacter);
        return true;
    }
    /* Read Method? */
    public Optional<Character> ReadCharacter(UUID id) {
        return this.characterRepository.findById(id);
   }

  public boolean UpdateCharacter(Character character) {
    this.characterRepository.save(character);
    return true;
}

public boolean DeleteCharacter(UUID id) {
    this.characterRepository.deleteById(id);
    return true;
}

    public boolean CreateChoice(Choice choice) 
    {
        Choice testChoice = (Choice) choice;
        testChoice.setId(UUID.randomUUID());
        this.choiceRepository.save(testChoice);
        return true;
    }

    /* Read Method?
    public Optional<Choice> ReadChoice(UUID id) {
        return this.choiceRepository.findById(id);
   }
   */

  public boolean UpdateChoice(Choice choice) {
    this.choiceRepository.save(choice);
    return true;
}

public boolean DeleteChoice(UUID id) {
    this.choiceRepository.deleteById(id);
    return true;
}

    public boolean CreateResponse(Response response) 
    {
        Response testResponse = (Response) response;
        testResponse.setId(UUID.randomUUID());
        this.responseRepository.save(testResponse);
        return true;
    }

    /* Read Method?
    public Optional<Response> ReadResponse(UUID id) {
        return this.responseRepository.findById(id);
   }
   */

  public boolean UpdateResponse(Response response) {
    this.responseRepository.save(response);
    return true;
}

public boolean DeleteResponse(UUID id) {
    this.responseRepository.deleteById(id);
    return true;
}

    public boolean CreateScenario(Scenario scenario) 
    {
        Scenario testScenario = (Scenario) scenario;
        testScenario.setId(UUID.randomUUID());
        this.scenarioRepository.save(testScenario);
        return true;
    }

    /* Read Method?
    public Optional<Scenario> ReadScenario(UUID id) {
        return this.scenarioRepository.findById(id);
   }
   */

  public boolean UpdateScenario(Scenario scenario) {
    this.scenarioRepository.save(scenario);
    return true;
}

public boolean DeleteScenario(UUID id) {
    this.scenarioRepository.deleteById(id);
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
