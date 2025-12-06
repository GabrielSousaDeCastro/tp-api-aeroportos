package com.example.aeroporto.service;

import com.example.aeroporto.model.Aeroporto;
import com.example.aeroporto.repository.AeroportoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AeroportoServiceTest {

    @InjectMocks
    private AeroportoService aeroportoService;

    @Mock
    private AeroportoRepository aeroportoRepository;

    @Test
    public void salvar_DeveSalvarAeroporto_QuandoIataNaoExiste() {
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setCodigoIata("GRU");
        aeroporto.setNomeAeroporto("Guarulhos");

        when(aeroportoRepository.existsByCodigoIata("GRU")).thenReturn(false);
        when(aeroportoRepository.save(any(Aeroporto.class))).thenReturn(aeroporto);

        Aeroporto salvo = aeroportoService.salvar(aeroporto);

        assertNotNull(salvo);
        assertEquals("GRU", salvo.getCodigoIata());
        verify(aeroportoRepository, times(1)).save(aeroporto);
    }

    @Test
    public void salvar_DeveLancarExcecao_QuandoIataJaExiste() {
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setCodigoIata("GRU");

        when(aeroportoRepository.existsByCodigoIata("GRU")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            aeroportoService.salvar(aeroporto);
        });

        assertEquals("Aeroporto com IATA GRU já existe.", exception.getMessage());
        verify(aeroportoRepository, never()).save(any());
    }
}
