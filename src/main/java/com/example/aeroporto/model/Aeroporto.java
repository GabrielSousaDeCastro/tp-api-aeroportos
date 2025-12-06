package com.example.aeroporto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aeroportos")
@Data
public class Aeroporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeroporto")
    private Integer idAeroporto;

    @Column(name = "nome_aeroporto", nullable = false)
    private String nomeAeroporto;

    @Column(name = "codigo_iata", length = 3, nullable = false, unique = true)
    private String codigoIata;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "codigo_pais_iso", length = 2)
    private String codigoPaisIso;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "altitude")
    private Double altitude;
}
