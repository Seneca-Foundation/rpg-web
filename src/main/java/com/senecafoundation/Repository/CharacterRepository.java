package com.senecafoundation.Repository;

import java.util.UUID;
import com.senecafoundation.CharacterTypes.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository<T extends Character> extends JpaRepository<T, UUID> {}
