package com.senecafoundation.DataHandler;

import java.util.ArrayList;
import java.util.List;
import com.senecafoundation.CharacterTypes.Character;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.ShadowElf;
import com.senecafoundation.Repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShadowElfDataHandler extends RepoDataHandler<Character> {
    
    @Autowired
    public CharacterRepository<ShadowElf> characterRepository;

    @Override
    public List<ICharacter> ReadAll() {
        ArrayList<ICharacter> listOfItemsToReturn = new ArrayList<ICharacter>();
        for (ShadowElf shadowElf : this.characterRepository.findAll()) {
            listOfItemsToReturn.add(shadowElf);
        }
        return listOfItemsToReturn;
    }    
}
