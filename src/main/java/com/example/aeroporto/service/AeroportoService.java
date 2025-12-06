package com.example.aeroporto.service;

import com.example.aeroporto.model.Aeroporto;
import com.example.aeroporto.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportoService {

    @Autowired
    private AeroportoRepository aeroportoRepository;

    public List<Aeroporto> listarTodos() {
        return aeroportoRepository.findAll();
    }

    public Optional<Aeroporto> buscarPorIata(String codigoIata) {
        return aeroportoRepository.findByCodigoIata(codigoIata);
    }

    public Aeroporto salvar(Aeroporto aeroporto) {
        if (aeroportoRepository.existsByCodigoIata(aeroporto.getCodigoIata())) {
            throw new RuntimeException("Aeroporto com IATA " + aeroporto.getCodigoIata() + " já existe.");
        }
        return aeroportoRepository.save(aeroporto);
    }

    public void excluir(Integer id) {
        aeroportoRepository.deleteById(id);
    }
}
