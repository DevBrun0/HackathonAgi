package com.hackathon.agi.agibank.service;

import com.hackathon.agi.agibank.domain.Almoxarifado;
import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.enums.StatusFuncionario;
import com.hackathon.agi.agibank.domain.funcionario.request.FuncionarioRequest;
import com.hackathon.agi.agibank.exceptions.funcionario.DesligarFuncionarioException;
import com.hackathon.agi.agibank.exceptions.funcionario.FuncionarioExceptions;
import com.hackathon.agi.agibank.mapper.FuncionarioMapper;
import com.hackathon.agi.agibank.repository.AlmoxarifadoRepository;
import com.hackathon.agi.agibank.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;
    private final AlmoxarifadoRepository almoxarifadoRepository;
    private final EquipamentoService equipamentoService;
    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper, AlmoxarifadoRepository almoxarifadoRepository, EquipamentoService equipamentoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
        this.almoxarifadoRepository = almoxarifadoRepository;
        this.equipamentoService = equipamentoService;
    }

    public Funcionario criarFuncionario(FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = funcionarioMapper.funcionarioRequestParaFuncionario(funcionarioRequest);
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(String id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioExceptions("Funcionário não encontrado"));
    }

    public List<Equipamento> desligarFuncionario(String id) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new FuncionarioExceptions("Funcionário não encontrado"));
        List<Almoxarifado> listaAlmoxarifado = almoxarifadoRepository.findByIdFuncionario(funcionarioExistente.getId());
        List<Almoxarifado> listaPendencias = listaAlmoxarifado.stream()
                .filter(alm -> alm.getDataDevolucao() == null)
                .toList();

        List<Equipamento> equipamentos = equipamentoService.listarEquipamento();
        if(listaPendencias.size() > 0){
            funcionarioExistente.setStatus(StatusFuncionario.PENDENTE);
            funcionarioRepository.save(funcionarioExistente);
            return equipamentos.stream()
                    .filter(equip -> listaPendencias.stream()
                            .map(Almoxarifado::getIdEquipamento)
                            .anyMatch(idEquip -> idEquip.equals(equip.getPatrimonio()))).toList();
        }

        funcionarioExistente.setStatus(StatusFuncionario.DESLIGADO);
        funcionarioRepository.save(funcionarioExistente);
        System.out.println(listaPendencias.size());
        return null;
    }

    public List<Funcionario> listarFuncionariosPendenciasDesligamento() {
        List<Funcionario> listaFuncionarios = listarFuncionarios();
        return listaFuncionarios.stream()
                .filter(funcionario -> funcionario.getStatus().equals(StatusFuncionario.PENDENTE))
                .toList();
    }
}

