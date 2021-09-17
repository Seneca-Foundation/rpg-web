package com.senecafoundation.DataHandler;

import java.util.UUID;

import com.senecafoundation.Scene.Scenario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, UUID> {}
