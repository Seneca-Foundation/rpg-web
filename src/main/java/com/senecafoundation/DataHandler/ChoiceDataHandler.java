package com.senecafoundation.DataHandler;

import java.util.List;
import java.util.UUID;
import com.senecafoundation.Repository.ChoiceRepository;
import com.senecafoundation.Scene.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceDataHandler <T extends Choice> {

    @Autowired
    private ChoiceRepository<T> choiceRepository;

    public boolean Create(Choice choice) {
        this.choiceRepository.save((T) choice);
        return true;
    }

    public Choice Read(UUID id) throws Exception {
        return this.choiceRepository.findById(id).orElseThrow();
    }

    public List<Choice> ReadAll() {
        return (List<Choice>) this.choiceRepository.findAll();
    }

    public Choice Update(Choice response) {
        this.choiceRepository.save((T) response);
        return response;
    }

    public boolean Delete(UUID id) throws Exception {
        this.choiceRepository.deleteById(id);
        return false;
    }

    
}
