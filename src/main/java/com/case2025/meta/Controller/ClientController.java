package com.case2025.meta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.case2025.meta.DTO.ClientRequestDTO;
import com.case2025.meta.DTO.ClientResponseDTO;
import com.case2025.meta.Service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO create(@RequestBody @Valid ClientRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public ClientResponseDTO getById(@PathVariable long id) {
        return service.getById(id);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ClientResponseDTO> clients = service.getAll();
        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum cliente encontrado");
        }
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    public ClientResponseDTO update(@PathVariable Long id, @RequestBody @Valid ClientRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
