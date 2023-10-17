package com.example.simpleStore.Compra;

import java.util.List;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class compraController {
    private final Icompra icompra;

    public compraController(Icompra icompra) {
        this.icompra = icompra;
    }

    @PostMapping("/")
    public List<compraModel> getAllCompras(){
        return icompra.findAll();
    }
}
