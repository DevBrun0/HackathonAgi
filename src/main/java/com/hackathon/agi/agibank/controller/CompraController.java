package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.domain.compra.request.CompraRequest;
import com.hackathon.agi.agibank.domain.compra.response.CompraResponse;
import com.hackathon.agi.agibank.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/compra")
@RestController
@RequiredArgsConstructor
public class CompraController {

    private CompraService compraService;

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

    @GetMapping("{/id}")
    public ResponseEntity<?> buscarSolicitacoesPorFuncionario(String idFuncionario){
        List<CompraResponse> solicitacaoFuncionario = compraService.buscarSolicitacoesPorFuncionario(idFuncionario);
        return  ResponseEntity.ok().body(solicitacaoFuncionario);
    }

    @PatchMapping("{/id}")
    public ResponseEntity<?> receberCompra(String idCompra){
        compraService.receberCompra(idCompra);
        return ResponseEntity.ok().build();
    }

}
