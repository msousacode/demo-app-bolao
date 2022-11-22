package com.msousacode.bolao.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "boloes")
public class Bolao {

    @Id
    @GeneratedValue
    @Column(name = "bolao_id", nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "link")
    private String link;

    /*@ManyToMany
    @JoinTable(
            name = "usuarios_boloes",
            joinColumns = @JoinColumn(name = "bolao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuarios = new ArrayList<>();*/

    @OneToMany(mappedBy = "bolao")
    Set<BolaoUsuario> bolaoUsuarioSet;
}
