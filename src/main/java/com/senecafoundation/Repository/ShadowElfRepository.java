package com.senecafoundation.Repository;

import com.senecafoundation.CharacterTypes.Character;
import org.springframework.stereotype.Repository;

@Repository
public interface ShadowElfRepository<T extends Character> extends CharacterRepository<T> {}
