package com.msousacode.bolao.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "usuario_id", nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "usuario")
    private List<BolaoUsuario> bolaoUsuarioSet = new ArrayList<>();
}
