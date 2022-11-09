package com.msousacode.bolao.repositories;

import com.msousacode.bolao.entities.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, UUID> {
}
