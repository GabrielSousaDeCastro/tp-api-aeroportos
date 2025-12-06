package com.example.aeroporto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.aeroporto.model.Aeroporto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AeroportoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void criar_DeveRetornarStatus201() throws Exception {
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setCodigoIata("BSB");
        aeroporto.setNomeAeroporto("Aeroporto Internacional de Brasília");
        aeroporto.setCidade("Brasília");
        aeroporto.setCodigoPaisIso("BR");

        mockMvc.perform(post("/api/v1/aeroportos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aeroporto)))
                .andExpect(status().isCreated());
    }
}
