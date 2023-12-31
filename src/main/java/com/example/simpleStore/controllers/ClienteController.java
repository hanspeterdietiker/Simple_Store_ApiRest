package com.example.simpleStore.controllers;


import java.util.Optional;

import com.example.simpleStore.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simpleStore.dtos.ClienteDto;
import com.example.simpleStore.entities.ClienteModel;

@RestController
@RequestMapping("/api/v1/client")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping("/register-client")
    public ResponseEntity<ClienteModel> createClient(@RequestBody @Valid ClienteModel client) {
        var newClient = new ClienteModel(client.getId(), client.getNameClient(),
                client.getEmail(), client.getPassword());
        clienteService.createClient(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @PutMapping("/update-client/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto attClient)   {
        var updatedClient = clienteService.update(id, attClient);
        return ResponseEntity.ok(new ClienteDto(updatedClient.getNameClient(), updatedClient.getEmail()));
    }


    @GetMapping("/searching-by-client/{id}")
    public ResponseEntity<Optional<ClienteModel>> searchById(@PathVariable Long id)   {
        var cliente = clienteService.getById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<String> deleteUserEntity(@PathVariable Long id)   {
        clienteService.deleteClient(id);
        return ResponseEntity.ok().body("Cliente deletado");
    }
}
