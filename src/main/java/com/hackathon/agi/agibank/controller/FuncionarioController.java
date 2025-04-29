package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.domain.Almoxarifado;
import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.funcionario.request.FuncionarioRequest;
import com.hackathon.agi.agibank.service.FuncionarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController implements FuncionarioApi {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @ApiResponse(description = "")
    @PostMapping
    public Funcionario criar(@RequestBody FuncionarioRequest funcionario) {
        return funcionarioService.criarFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> listar() {
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("/{id}")
    public Funcionario buscarPorId(@PathVariable String id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }

    @PatchMapping("/{id}")
    public List<Equipamento> desligarFuncionario(@PathVariable String id) {
        return funcionarioService.desligarFuncionario(id);
    }


}
