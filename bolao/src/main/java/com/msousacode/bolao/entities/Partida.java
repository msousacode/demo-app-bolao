package com.msousacode.bolao.entities;

import com.msousacode.bolao.enuns.PartidaStatusType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Entity
@Table(name = "partidas")
public class Partida {

    public Partida(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    @Id
    @GeneratedValue
    @Column(name = "partida_id", nullable = false)
    private UUID id;

    @Column(name = "time_1")
    private String time1;

    @Column(name = "time_2")
    private String time2;

    @Column(name = "resultado_time_1")
    private Integer resultadoTime1;

    @Column(name = "resultado_time_2")
    private Integer resultadoTime2;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PartidaStatusType status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bolao_id")
    private Bolao bolao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;

    @OneToOne(mappedBy = "partida", fetch = FetchType.LAZY)
    private Palpite palpite;
}
