package com.msousacode.bolao.repository;

import com.msousacode.bolao.entities.Palpite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PalpiteRepository extends JpaRepository<Palpite, UUID> {
}
