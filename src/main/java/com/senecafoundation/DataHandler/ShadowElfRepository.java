package com.senecafoundation.DataHandler;

import java.util.UUID;

import com.senecafoundation.CharacterTypes.ShadowElf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShadowElfRepository extends JpaRepository<ShadowElf, UUID> {}
