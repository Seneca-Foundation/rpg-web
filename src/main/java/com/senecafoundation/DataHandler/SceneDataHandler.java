package com.senecafoundation.DataHandler;

import java.util.List;
import java.util.UUID;
import com.senecafoundation.Repository.ScenarioRepository;
import com.senecafoundation.Scene.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SceneDataHandler<T extends Scenario> {

    @Autowired
    private ScenarioRepository<T> scenarioRepository;

    public boolean Create(Scenario scenario) {
        this.scenarioRepository.save((T) scenario);
        return true;
    }

    public Scenario Read(UUID id) throws Exception {
        return this.scenarioRepository.findById(id).orElseThrow();
    }

    public List<Scenario> ReadAll() {
        return (List<Scenario>) this.scenarioRepository.findAll();
    }

    public Scenario Update(Scenario response) {
        this.scenarioRepository.save((T) response);
        return response;
    }

    public boolean Delete(UUID id) throws Exception {
        this.scenarioRepository.deleteById(id);
        return false;
    }

    
}
