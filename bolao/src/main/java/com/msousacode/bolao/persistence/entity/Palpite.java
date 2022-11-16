package com.msousacode.bolao.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "palpites")
public class Palpite {

    @Id
    @GeneratedValue
    @Column(name = "palpite_id", nullable = false)
    private UUID id;

    @Column(name = "palpite_time_1", nullable = false)
    private Integer palpiteTime1;

    @Column(name = "palpite_time_2", nullable = false)
    private Integer palpiteTime2;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;
}
