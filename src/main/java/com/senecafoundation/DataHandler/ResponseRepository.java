package com.senecafoundation.DataHandler;

import java.util.UUID;

import com.senecafoundation.Scene.Response;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponseRepository extends JpaRepository<Response, UUID> {}
