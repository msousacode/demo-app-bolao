package com.msousacode.bolao.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "campeonatos")
public class Campeonato {

    @Id
    @GeneratedValue
    @Column(name = "campeonato_id", nullable = false)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "rodada")
    private Integer rodada;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;
}
