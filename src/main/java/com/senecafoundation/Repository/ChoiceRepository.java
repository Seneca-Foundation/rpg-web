package com.senecafoundation.Repository;

import java.util.UUID;
import com.senecafoundation.Scene.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository<T extends Choice> extends JpaRepository<Choice, UUID> {}
