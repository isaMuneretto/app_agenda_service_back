package com.app_agenda_service_back.Endereco;

import com.app_agenda_service_back.categoria.CategoriaEntity;
import com.app_agenda_service_back.endereco.EnderecoEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Verificar se a rota de endereço está respondendo corretamente")
    void index() throws Exception{
        mockMvc.perform(get("/enderecos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Verifica se está criando um endereço")
    @Transactional
    @Rollback
    void create() throws Exception{
        EnderecoEntity enderecoExemplo = new EnderecoEntity();
        enderecoExemplo.setEnderecoRua("João Milioli");
        enderecoExemplo.setEnderecoNumero(600);
        enderecoExemplo.setEnderecoBairro("Comerciário");
        enderecoExemplo.setEnderecoCidade("Criciúma");
        enderecoExemplo.setEnderecoEstado("SC");
        enderecoExemplo.setEnderecoCep("88802320");
        enderecoExemplo.setEnderecoComplemento("casa");

        String jsonRequest = objectMapper.writeValueAsString(enderecoExemplo);

        mockMvc.perform(post("/enderecos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)).andExpect(status().isCreated());

        TestTransaction.end();
    }

    @Test
    @DisplayName("Verifica se está alterando o registro corretamente")
    @Transactional
    @Rollback
    void update() throws Exception {
        EnderecoEntity enderecoExemplo = new EnderecoEntity();
        enderecoExemplo.setEnderecoRua("Av. Sentenario");
        enderecoExemplo.setEnderecoNumero(1562);
        enderecoExemplo.setEnderecoBairro("Centru");
        enderecoExemplo.setEnderecoCidade("Criciumaa");
        enderecoExemplo.setEnderecoEstado("SCC");
        enderecoExemplo.setEnderecoCep("88805600");
        enderecoExemplo.setEnderecoComplemento("apartamentu");

        String jsonRequest = objectMapper.writeValueAsString(enderecoExemplo);

        String response = mockMvc.perform(post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)  //requisição do front
                        .content(jsonRequest)).andExpect(status().isCreated()) //verifica se foi inserido e se foi criado
                .andReturn()  //retorna
                .getResponse()  //pega a resposta
                .getContentAsString(); //transforma em String e armazena na variável response

        JsonNode jsonNode = objectMapper.readTree(response); //pega obj mapper e transfrma em uma informação que possa manipular. String é dificil
        Long id = jsonNode.get("enderecoId").asLong(); //pegou da resposta o id criado Long e armazenou na variável id

        EnderecoEntity enderecoUpdate = new EnderecoEntity(); //recria um objeto
        enderecoExemplo.setEnderecoRua("Av. Centenario");
        enderecoExemplo.setEnderecoNumero(156);
        enderecoExemplo.setEnderecoBairro("Centro");
        enderecoExemplo.setEnderecoCidade("Criciuma");
        enderecoExemplo.setEnderecoEstado("SC");
        enderecoExemplo.setEnderecoCep("88805601");
        enderecoExemplo.setEnderecoComplemento("apartamento");
        enderecoExemplo.setEnderecoId(id); //passo o obj corrigido para categoriaUpdate

        //com as info atualizadas precisamos enviar para requisição post
        String jsonUpdateRequest = objectMapper.writeValueAsString(enderecoUpdate);

        mockMvc.perform(put("/enderecos/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdateRequest))
                .andExpect(status().isOk()); //verifica se a resposta está ok
               // .andExpect(jsonPath("$.enderecoRua").value("João Milioli"));  //verifica se no banco realmente foi alterado. Cifrão pesquisa o conteúdo

        TestTransaction.end();
    }
}
