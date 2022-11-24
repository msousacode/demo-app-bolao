package com.msousacode.bolao.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "boloes")
public class Bolao {

    @Id
    @GeneratedValue
    @Column(name = "bolao_id", nullable = false)
    private UUID id;

    @NotEmpty(message = "Nonme do bolão não deve ser vazio.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Size(max = 255, message = "Descrição não deve ultrapassar 255 caracteres.")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "link")
    private String link;

    @OneToMany(mappedBy = "bolao")
    private List<BolaoUsuario> bolaoUsuarioSet = new ArrayList<>();

    public Bolao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
