package com.senecafoundation.DataHandler;

import java.util.ArrayList;
import java.util.List;
import com.senecafoundation.CharacterTypes.Character;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.Nuet;
import com.senecafoundation.Repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NuetDataHandler extends RepoDataHandler<Character> {
    
    @Autowired
    public CharacterRepository<Nuet> characterRepository;

    @Override
    public List<ICharacter> ReadAll() {
        ArrayList<ICharacter> listOfItemsToReturn = new ArrayList<ICharacter>();
        for (Nuet nuet : this.characterRepository.findAll()) {
            listOfItemsToReturn.add(nuet);
        }
        return listOfItemsToReturn;
    }    
}

