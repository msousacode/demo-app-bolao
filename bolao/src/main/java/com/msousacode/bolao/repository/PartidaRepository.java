package com.msousacode.bolao.repository;

import com.msousacode.bolao.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, UUID> {
}
