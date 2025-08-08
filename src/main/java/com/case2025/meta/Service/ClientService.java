package com.case2025.meta.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.tool.schema.internal.ExceptionHandlerHaltImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.case2025.meta.DTO.ClientRequestDTO;
import com.case2025.meta.DTO.ClientResponseDTO;
import com.case2025.meta.Entity.Client;
import com.case2025.meta.Repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientResponseDTO create(ClientRequestDTO dto) {
        Client client = new Client();
        client.setName(dto.name());
        client.setAge(dto.age());
        client.setEmail(dto.email());
        Client saved = repository.save(client);
        return new ClientResponseDTO(saved.getId(), saved.getName(), saved.getAge(), saved.getEmail());
    }

    public ClientResponseDTO getById(long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return new ClientResponseDTO(client.getId(), client.getName(), client.getAge(), client.getEmail());
    }

    public List<ClientResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(client -> new ClientResponseDTO(client.getId(), client.getName(), client.getAge(),
                        client.getEmail()))
                .collect(Collectors.toList());
    }

    public ClientResponseDTO update(long id, ClientRequestDTO dto) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        client.setName(dto.name());
        client.setAge(dto.age());
        client.setEmail(dto.email());
        Client saved = repository.save(client);
        return new ClientResponseDTO(saved.getId(), saved.getName(), saved.getAge(), saved.getEmail());
    }

    public void delete(long id) {
        if (!repository.existsById(id)) {
            System.err.println(new EntityNotFoundException("Usuário não encontrado"));
        }
        repository.deleteById(id);
    }
}
