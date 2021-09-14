package com.senecafoundation.DataHandler;

import java.util.UUID;

import com.senecafoundation.CharacterTypes.ICharacter;

public interface IDataHandlerRead 
{
    public ICharacter Read(UUID id) throws Exception;
}
