package com.example.aeroporto.controller;

import com.example.aeroporto.model.Aeroporto;
import com.example.aeroporto.service.AeroportoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeroportos")
public class AeroportoController {

    private final AeroportoService aeroportoService;

    public AeroportoController(AeroportoService aeroportoService) {
        this.aeroportoService = aeroportoService;
    }

    @PostMapping
    public ResponseEntity<Aeroporto> criar(@RequestBody Aeroporto aeroporto) {
        Aeroporto novoAeroporto = aeroportoService.salvar(aeroporto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAeroporto);
    }

    @GetMapping
    public ResponseEntity<List<Aeroporto>> listarTodos() {
        return ResponseEntity.ok(aeroportoService.listarTodos());
    }

    @GetMapping("/{iata}")
    public ResponseEntity<Aeroporto> buscarPorIata(@PathVariable String iata) {
        Optional<Aeroporto> aeroporto = aeroportoService.buscarPorIata(iata);
        return aeroporto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{iata}")
    public ResponseEntity<Aeroporto> atualizar(@PathVariable String iata, @RequestBody Aeroporto aeroportoAtualizado) {
        Optional<Aeroporto> aeroportoExistente = aeroportoService.buscarPorIata(iata);
        
        if (aeroportoExistente.isPresent()) {
            Aeroporto aeroporto = aeroportoExistente.get();
            aeroporto.setNomeAeroporto(aeroportoAtualizado.getNomeAeroporto());
            aeroporto.setCidade(aeroportoAtualizado.getCidade());
            aeroporto.setCodigoPaisIso(aeroportoAtualizado.getCodigoPaisIso());
            aeroporto.setLatitude(aeroportoAtualizado.getLatitude());
            aeroporto.setLongitude(aeroportoAtualizado.getLongitude());
            aeroporto.setAltitude(aeroportoAtualizado.getAltitude());
            
            // Note: We generally shouldn't change the IATA code if it's the key, 
            // but if we did, we'd need validation. Assuming IATA is immutable here for simplicity 
            // or handled carefully.
            // aeroporto.setCodigoIata(aeroportoAtualizado.getCodigoIata()); 

            return ResponseEntity.ok(aeroportoService.atualizar(aeroporto));
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{iata}")
    public ResponseEntity<Void> excluir(@PathVariable String iata) {
        Optional<Aeroporto> aeroporto = aeroportoService.buscarPorIata(iata);
        if (aeroporto.isPresent()) {
            aeroportoService.excluir(aeroporto.get().getIdAeroporto());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build(); // Or noContent if we want idempotency, but strict req says 204 if found?
        // Actually req says "Remove o aeroporto e retorna 204". Does not specify if not found.
        // Standard REST 404 is appropriate if not found, but 204 is fine.
        // Let's stick to simple logic: find -> delete -> 204.
    }
}
