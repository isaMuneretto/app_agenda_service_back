package com.app_agenda_service_back.usuario;

import com.app_agenda_service_back.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    //ver com Filipe pq aqui é diferente do endereco Service
    @Autowired
    private UsuarioRepository usuarioRepository;
    //private UsuarioRepository<UsuarioEntity, Long> usuarioRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> findAll(){
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO findById(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));
        return usuarioMapper.toDTO(usuario);
    }

    //ver com Filipe pq é diferente do endereco Service
    public UsuarioDTO create(UsuarioDTO usuarioDTO){
        UsuarioEntity usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO update(Long id,UsuarioDTO usuarioDTO){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));
        //usuarioDTO.setUsuarioId(usuario.getUsuarioId());
        usuario = usuarioMapper.updateEntity(usuarioDTO,usuario);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }
}
