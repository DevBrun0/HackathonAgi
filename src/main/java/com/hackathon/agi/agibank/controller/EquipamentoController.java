package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.equipamento.request.AlterarStatusRequest;
import com.hackathon.agi.agibank.domain.equipamento.request.CadastrarEquipamentoRequest;
import com.hackathon.agi.agibank.repository.EquipamentoRepository;
import com.hackathon.agi.agibank.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/equipamento")
@RestController
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;
    private final EquipamentoRepository equipamentoRepository;

    @PostMapping
    public void cadastrarEquipamento(@RequestBody CadastrarEquipamentoRequest request){
        equipamentoService.cadastrarEquipamento(request);
    }
    @GetMapping
    public List<Equipamento> listarEquipamento(){
        return equipamentoService.listarEquipamento();
    }

    @PatchMapping("/{id}")
    public void alterarEstado(@PathVariable String id, @RequestBody AlterarStatusRequest request){
        equipamentoService.alterarEstado(id, request);
    }

    @GetMapping("/{id}")
    public Equipamento equipamentoPorId(@PathVariable String id){

        return equipamentoService.equipamentoPorId(id);
    }

    @DeleteMapping
    public void deleta(){
        equipamentoRepository.deleteAll();
    }
}
