package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.domain.Compra;
import com.hackathon.agi.agibank.domain.compra.response.CompraResponse;
import com.hackathon.agi.agibank.service.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/compra")
@RestController
public class CompraController {

    private CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity fazerCompraEquipamento(String categoria, String idFuncionario){
        compraService.solicitarCompra(categoria, idFuncionario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> buscarTodasSolicitacoes(){
        List<CompraResponse> listaCompras = compraService.buscarTodasSolicitacoes();
        return  ResponseEntity.ok().body(listaCompras);
    }

    @GetMapping("/{idCompra}")
    public ResponseEntity buscarSolicitacoesPorFuncionario(@PathVariable String idCompra){
        Compra compra = compraService.buscarSolicitacoesPorId(idCompra);
        return  ResponseEntity.ok().body(compra);
    }

    @PatchMapping("/{idCompra}")
    public ResponseEntity<?> receberCompra(@PathVariable String idCompra){
        compraService.receberCompra(idCompra);
        return ResponseEntity.ok().build();
    }

}
