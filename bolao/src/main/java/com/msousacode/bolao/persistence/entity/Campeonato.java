package com.msousacode.bolao.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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


    @OneToMany(mappedBy = "campeonato", fetch = FetchType.EAGER)
    public List<Partida> partidas = new ArrayList<>();
}
