package com.senecafoundation.DataHandler;

import java.util.UUID;

import com.senecafoundation.NPCTypes.NPC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NPCRepository extends JpaRepository<NPC, UUID> {}
