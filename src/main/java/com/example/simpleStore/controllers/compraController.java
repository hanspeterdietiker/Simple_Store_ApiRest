package com.example.simpleStore.controllers;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simpleStore.dtos.compraDto;
import com.example.simpleStore.entities.compraModel;
import com.example.simpleStore.repositories.CompraRepository;

@RestController
@RequestMapping("/api/v1/compras")
public class compraController {

    private final CompraRepository compraRepository;

    public compraController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @PostMapping("/register-orders")
    public ResponseEntity<compraModel> create(@RequestBody compraModel buy) {
        if (buy != null) {
            compraModel novaCompra = compraRepository.save(buy);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(novaCompra);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/uptade-order/{id}")
    public ResponseEntity<compraDto> updateCompra(@PathVariable Long id, @RequestBody compraModel CompraModel) {

        var idCompra = this.compraRepository.findById(id).orElse(null);

        if (idCompra == null) {
            return ResponseEntity.badRequest().build();

        } else {
            var compraUpdate = this.compraRepository.save(idCompra);
            return ResponseEntity.ok().body(
                    new compraDto(compraUpdate.getCliente(), compraUpdate.getPrice(), compraUpdate.getQuantidade()));
        }

    }

    @GetMapping("/searching-by-orders")
    public List<compraModel> getAllCompras() {
        return compraRepository.findAll();
    }
}
