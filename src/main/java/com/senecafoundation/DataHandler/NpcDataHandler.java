package com.senecafoundation.DataHandler;

import java.util.ArrayList;
import java.util.List;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.NPCTypes.NPC;
import com.senecafoundation.Repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NpcDataHandler extends RepoDataHandler<NPC> {
    
    @Autowired
    public CharacterRepository<NPC> characterRepository;

    @Override
    public List<ICharacter> ReadAll() {
        ArrayList<ICharacter> listOfItemsToReturn = new ArrayList<ICharacter>();
        for (NPC npc : this.characterRepository.findAll()) {
            listOfItemsToReturn.add(npc);
        }
        return listOfItemsToReturn;
    }    
}
