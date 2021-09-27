package com.senecafoundation.DataHandler.Interfaces;

import java.util.List;
import java.util.UUID;

import com.senecafoundation.CharacterTypes.ICharacter;

public interface IDataHandlerRead 
{
    public ICharacter Read(UUID id) throws Exception;
    public List<ICharacter> ReadAll();
}
