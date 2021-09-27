package com.senecafoundation.DataHandler;

import java.util.UUID;

import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.DataHandler.Interfaces.IDataHandler;

public abstract class CharacterDataHandler implements IDataHandler
{

    @Override
    public abstract boolean Create(ICharacter character);

    @Override
    public abstract ICharacter Read(UUID id) throws Exception;

    @Override
    public abstract ICharacter Update(ICharacter character);

    @Override
    public abstract boolean Delete(UUID id) throws Exception;
}