package com.app_agenda_service_back.usuario;

import com.app_agenda_service_back.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public UsuarioDTO create(UsuarioDTO usuarioDTO){
        UsuarioEntity usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO update(Long id,UsuarioDTO usuarioDTO){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));
        usuarioDTO.setUsuarioId(id);
        usuario = usuarioMapper.updateEntity(usuarioDTO,usuario);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity findByUsuarioEmail(String usuarioEmail) {
        return usuarioRepository.findByUsuarioEmail(usuarioEmail);
    }
}
