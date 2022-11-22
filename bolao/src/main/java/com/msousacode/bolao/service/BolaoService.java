package com.msousacode.bolao.service;

import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.Bolao;
import com.msousacode.bolao.persistence.entity.BolaoUsuario;
import com.msousacode.bolao.persistence.entity.Usuario;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import com.msousacode.bolao.persistence.entity.types.UsuarioType;
import com.msousacode.bolao.persistence.repository.BolaoRepository;
import com.msousacode.bolao.persistence.repository.BolaoUsuarioRepository;
import com.msousacode.bolao.persistence.repository.UsuarioRepository;
import com.msousacode.bolao.util.BeanUtil;
import com.msousacode.bolao.web.dtos.BolaoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BolaoService {

    @Autowired
    private BolaoRepository bolaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BolaoUsuarioRepository bolaoUsuarioRepository;

    @Transactional
    public BolaoDTO cadastrar(BolaoDTO bolaoDTO, String userName) {

        var usuario = buscarUsuarioLogado(userName);

        var bolao = (Bolao) BeanUtil.convert(bolaoDTO, new Bolao());

        var bolaoResult = bolaoRepository.save(bolao);

        salvarRelacionamento(bolaoResult, usuario);

        return new BolaoDTO(bolaoResult.getId(), bolaoResult.getNome(), bolaoResult.getDescricao());
    }

    private Usuario buscarUsuarioLogado(String userName) {
        return usuarioRepository.findByUserName(userName)
                .orElseThrow(() -> new ServiceException(ServiceErrorsType.RESOURCE_NOT_FOUND));
    }

    private void salvarRelacionamento(Bolao bolao, Usuario usuario) {

        var bolaoUsuario = new BolaoUsuario();

        bolaoUsuario.setBolao(bolao);
        bolaoUsuario.setUsuario(usuario);
        bolaoUsuario.setUsuarioType(UsuarioType.OWNER);

        bolaoUsuarioRepository.save(bolaoUsuario);
    }
}
