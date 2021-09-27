package com.senecafoundation.Repository;

import com.senecafoundation.NPCTypes.NPC;
import org.springframework.stereotype.Repository;

@Repository
public interface NpcRepository<T extends NPC> extends CharacterRepository<T> {}
