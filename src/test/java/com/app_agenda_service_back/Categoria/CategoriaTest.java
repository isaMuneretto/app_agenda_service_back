package com.app_agenda_service_back.Categoria;

import com.app_agenda_service_back.categoria.CategoriaEntity;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaTest {

    //ajustes: mudar o nome para palavra sozinha e a tabela tem que ser no singular

    @Autowired
    private MockMvc mockMvc;//mock faz uma execução para validar o teste

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Verifica um ID inexistente")
    void idInexistente() throws Exception{
        Long id = 14L;

        mockMvc.perform(get("/categorias/14"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Categoria não encontrada"));
    }

    @Test
    @DisplayName("Verifica um ID existente")
    void findById() throws Exception{
        mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoriaNome").value("jardinagem"));
    }

    @Test
    @DisplayName("Verificar se a rota de categoria está respondendo corretamente")
    void index() throws Exception{
        mockMvc.perform(get("/categorias")
                .contentType(MediaType.APPLICATION_JSON))  //qual o tipo de requisição
                .andExpect(status().isOk());
    }

    @Test //transforma num json e envia para o controlador
    @DisplayName("Verifica se está criando uma categoria")
    @Transactional  //para no BD não criar um obj a cada teste
    @Rollback //voltar
    void create() throws Exception{
        CategoriaEntity categoriaExemplo = new CategoriaEntity();
        categoriaExemplo.setCategoriaNome("Jardinagem");
        categoriaExemplo.setCategoriaDescricao("Serviços de Jardinagem");

        String jsonRequest = objectMapper.writeValueAsString(categoriaExemplo);

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)).andExpect(status().isCreated());

        TestTransaction.end();
    }

    @Test
    @DisplayName("Verifica se está alterando o registro corretamente")
    @Transactional
    @Rollback
    void update() throws Exception {
        CategoriaEntity categoriaExemplo = new CategoriaEntity();
        categoriaExemplo.setCategoriaNome("Pintora");
        categoriaExemplo.setCategoriaDescricao("Servicus di pintura");

        String jsonRequest = objectMapper.writeValueAsString(categoriaExemplo);

        String response = mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)  //requisição do front
                        .content(jsonRequest)).andExpect(status().isCreated()) //verifica se foi inserido e se foi criado
                .andReturn()  //retorna
                .getResponse()  //pega a resposta
                .getContentAsString(); //transforma em String e armazena na variável response

        JsonNode jsonNode = objectMapper.readTree(response); //pega obj mapper e transfrma em uma informação que possa manipular. String é dificil
        Long id = jsonNode.get("categoriaId").asLong(); //pegou da resposta o id criado Long e armazenou na variável id

        CategoriaEntity categoriaUpdate = new CategoriaEntity(); //recria um objeto
        categoriaUpdate.setCategoriaNome("Pintura");
        categoriaUpdate.setCategoriaDescricao("Serviços de Pintura");
        categoriaExemplo.setCategoriaId(id); //passo o obj corrigido para categoriaUpdate

        //com as info atualizadas precisamos enviar para requisição post
        String jsonUpdateRequest = objectMapper.writeValueAsString(categoriaUpdate);

        mockMvc.perform(put("/categorias/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUpdateRequest))
                .andExpect(status().isOk()) //verifica se a resposta está ok
                .andExpect(jsonPath("$.categoriaNome").value("Pintura"));  //verifica se no banco realmente foi alterado. Cifrão pesquisa o conteúdo

        TestTransaction.end();
    }

    @Test
    @DisplayName("Verifica se está deletando uma categoria")
    void deleted() throws Exception{
        mockMvc.perform(delete("/categorias/13"))
                .andExpect(status().isOk());
    }


}
