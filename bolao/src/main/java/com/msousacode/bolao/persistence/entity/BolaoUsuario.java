package com.msousacode.bolao.persistence.entity;

import com.msousacode.bolao.persistence.entity.types.UsuarioType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios_boloes")
public class BolaoUsuario {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "bolao_id")
    private Bolao bolao;

    @Enumerated(EnumType.STRING)
    @Column(name = "usuario_tipo", nullable = false)
    private UsuarioType usuarioType;
}
