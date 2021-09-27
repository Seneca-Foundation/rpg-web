package com.senecafoundation.DataHandler;

import java.util.List;
import java.util.UUID;
import com.senecafoundation.CharacterTypes.Character;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.Repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoDataHandler<T extends Character> extends CharacterDataHandler {

    @Autowired
    private CharacterRepository<T> characterRepository;

    @Override
    public boolean Create(ICharacter character) {
        Character testCharacter =  (Character) character;
        testCharacter.setId(UUID.randomUUID());
        this.characterRepository.save((T) testCharacter);
        return true;
    }

    @Override
    public ICharacter Read(UUID id) throws Exception {
        return this.characterRepository.findById(id).orElseThrow();
    }

    @Override
    public ICharacter Update(ICharacter character) {
        this.characterRepository.save((T) character);
        return character;
    }

    @Override
    public boolean Delete(UUID id) throws Exception {
        this.characterRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ICharacter> ReadAll() {
        return (List<ICharacter>) this.characterRepository.findAll();
    }
}
