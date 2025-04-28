package com.hackathon.agi.agibank.service;

import com.hackathon.agi.agibank.controller.FuncionarioController;
import com.hackathon.agi.agibank.entity.Funcionario;
import com.hackathon.agi.agibank.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario criarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(String id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public Funcionario alterarStatus(String id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        funcionarioExistente.setStatus(funcionarioAtualizado.getStatus());

        return funcionarioRepository.save(funcionarioExistente);
    }
}

