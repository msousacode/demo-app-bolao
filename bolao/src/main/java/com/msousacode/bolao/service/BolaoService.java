package com.msousacode.bolao.service;

import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.persistence.entity.BolaoUsuario;
import com.msousacode.bolao.persistence.entity.Usuario;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import com.msousacode.bolao.persistence.entity.types.UsuarioType;
import com.msousacode.bolao.persistence.repository.BolaoRepository;
import com.msousacode.bolao.persistence.repository.BolaoUsuarioRepository;
import com.msousacode.bolao.persistence.repository.CampeonatoRepository;
import com.msousacode.bolao.persistence.repository.UsuarioRepository;
import com.msousacode.bolao.web.dtos.BolaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class BolaoService {

    @Autowired
    private BolaoRepository bolaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BolaoUsuarioRepository bolaoUsuarioRepository;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public Bolao buscar(UUID bolaoId) {

        return bolaoRepository.findById(bolaoId)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));
    }

    @Transactional
    public BolaoDTO cadastrar(Bolao bolao, String userName) {

        var createResult = bolaoRepository.save(bolao);

        salvarRelacionamento(createResult, userName);

        return new BolaoDTO(createResult.getId(), createResult.getNome(), createResult.getDescricao(), null);
    }

    private Usuario buscarUsuarioLogado(String userName) {

        return usuarioRepository.findByUserName(userName)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));
    }

    private void salvarRelacionamento(Bolao bolao, String userName) {

        var usuario = buscarUsuarioLogado(userName);

        var bolaoUsuario = new BolaoUsuario();

        /*
         * Realiza o vinculo entre as entidades Bolao e Usuário.
         */
        bolaoUsuario.setBolao(bolao);
        bolaoUsuario.setUsuario(usuario);
        bolaoUsuario.setUsuarioType(UsuarioType.OWNER);

        bolaoUsuarioRepository.save(bolaoUsuario);
    }

    public BolaoDTO buscarBolao(UUID bolaoId) {

        var bolaoResult = bolaoRepository.findById(bolaoId)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));

        var campeonatoResult = campeonatoRepository.findByBolao_Id(bolaoId)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));

        return new BolaoDTO(bolaoResult, campeonatoResult);
    }
}
