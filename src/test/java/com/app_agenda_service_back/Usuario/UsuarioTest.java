package com.app_agenda_service_back.Usuario;

import com.app_agenda_service_back.endereco.EnderecoService;
import com.app_agenda_service_back.usuario.UsuarioDTO;
import com.app_agenda_service_back.usuario.UsuarioEntity;
import com.app_agenda_service_back.usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnderecoService enderecoService;

    @MockBean
    private UsuarioService usuarioService;


    @Test
    @DisplayName("Validar se um cpf inválido está salvando no banco")
    void validaCpf() throws Exception{
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioCpf("111.111.111-11");
        usuario.setUsuarioNome("Cliente");
        usuario.setUsuarioEmail("cliente@cliente.com");
        usuario.setUsuarioSenha("123");
        usuario.setUsuarioDataNascimento(LocalDate.now());

        String jsonRequest = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
        .andExpect(status().isBadRequest());
    }

    @Test
    void create() throws Exception{
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioCpf("165.680.560-06");
        usuario.setUsuarioNome("");
        usuario.setUsuarioEmail("");
        usuario.setUsuarioSenha("123");
        usuario.setUsuarioDataNascimento(LocalDate.now());

        String jsonRequest = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCorreto() throws Exception{
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioCpf("165.680.560-06");
        usuario.setUsuarioNome("Renata");
        usuario.setUsuarioEmail("renata@renata.com");
        usuario.setUsuarioSenha("123");
        usuario.setUsuarioDataNascimento(LocalDate.now());

        String jsonRequest = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

//    @Test
//    void getById() throws Exception{
//        Long id = 1L;
//
//        UsuarioDTO usuario = usuarioService.findById(id);
//
//        usuario.getUsuarioNome("Nome não vazio");
//
//        String jsonRequest = usuario.toString();
//
//        mockMvc.perform(post("/"))
//    }

}
