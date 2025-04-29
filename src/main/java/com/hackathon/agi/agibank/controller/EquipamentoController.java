package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.request.AlterarStatusRequest;
import com.hackathon.agi.agibank.domain.request.CadastrarEquipamentoRequest;
import com.hackathon.agi.agibank.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/equipamento")
@RestController
@RequiredArgsConstructor

public class EquipamentoController {
    private EquipamentoService equipamentoService;
    @PostMapping
    public void cadastrarEquipamento(@RequestBody CadastrarEquipamentoRequest request){
        equipamentoService.cadastrarEquipamento(request);
    }
    @GetMapping
    public List<Equipamento> listarEquipamento(){
        return equipamentoService.listarEquipamento();
    }

    @PatchMapping("/id")
    public void alterarEstado(@PathVariable String id, @RequestBody AlterarStatusRequest request){
        equipamentoService.alterarEstado(id, request);
    }

//    @GetMapping("/id")
//    public Equipamento equipamentoPorId(){
//        return equipamentoService.equipamentoPorId();
//    }
}
