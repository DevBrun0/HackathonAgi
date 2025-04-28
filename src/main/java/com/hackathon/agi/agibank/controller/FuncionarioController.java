package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.entity.Funcionario;
import com.hackathon.agi.agibank.repository.FuncionarioRepository;
import com.hackathon.agi.agibank.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public Funcionario criar(@RequestBody Funcionario funcionario) {
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
    public Funcionario alterarStatus(@PathVariable String id, @RequestBody Funcionario funcionario) {
        return funcionarioService.alterarStatus(id, funcionario);
    }


}
