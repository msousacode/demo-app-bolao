package com.msousacode.bolao.entities;

import com.msousacode.bolao.enuns.PartidaStatusType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue
    @Column(name = "partida_id", nullable = false)
    private UUID id;

    @Column(name = "time_1")
    private String time1;

    @Column(name = "time_2")
    private String time2;

    @Column(name = "resultado_time_1")
    private String resultadoTime1;

    @Column(name = "resultado_time_2")
    private String resultadoTime2;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PartidaStatusType status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bolao_id")
    private Bolao bolao;

    @OneToOne(mappedBy = "partida", fetch = FetchType.LAZY)
    private Palpite palpite;
}
