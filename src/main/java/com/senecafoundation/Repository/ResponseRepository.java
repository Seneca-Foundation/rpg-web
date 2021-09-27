package com.senecafoundation.Repository;

import java.util.UUID;

import com.senecafoundation.Scene.Response;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponseRepository<T extends Response> extends JpaRepository<Response, UUID> {}
