package com.example.aeroporto.repository;

import com.example.aeroporto.model.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AeroportoRepository extends JpaRepository<Aeroporto, Integer> {
    Optional<Aeroporto> findByCodigoIata(String codigoIata);
    boolean existsByCodigoIata(String codigoIata);
}
