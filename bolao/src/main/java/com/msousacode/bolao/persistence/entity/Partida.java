package com.msousacode.bolao.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msousacode.bolao.persistence.entity.types.PartidaStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "partidas")
public class Partida {

    public Partida(Campeonato campeonato) {
        this.campeonato = campeonato;
        this.resultadoTime1 = 0;
        this.resultadoTime2 = 0;
        this.status = PartidaStatusType.NAO_FINALIZADA;
    }

    @Id
    @GeneratedValue
    @Column(name = "partida_id", nullable = false)
    private UUID id;

    @NotEmpty(message = "Time 1 não deve ser vazio.")
    @Column(name = "time_1")
    private String time1;

    @NotEmpty(message = "Time 2 não deve ser vazio.")
    @Column(name = "time_2")
    private String time2;

    @Column(name = "resultado_time_1")
    private Integer resultadoTime1;

    @Column(name = "resultado_time_2")
    private Integer resultadoTime2;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PartidaStatusType status;


    @JsonIgnore//Adicionado para ignorar a serialização dessa propriedade.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;
}
