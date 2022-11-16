package com.msousacode.bolao.persistence.repository;

import com.msousacode.bolao.persistence.entity.Palpite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PalpiteRepository extends JpaRepository<Palpite, UUID> {
}
