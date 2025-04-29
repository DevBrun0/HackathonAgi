package com.hackathon.agi.agibank.service;

import com.hackathon.agi.agibank.domain.Almoxarifado;
import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.almoxarifado.request.AlmoxarifadoEmprestaRequest;
import com.hackathon.agi.agibank.domain.almoxarifado.response.AlmoxarifadoEmprestaResponse;
import com.hackathon.agi.agibank.domain.enums.StatusEquipamentosEmprestimo;
import com.hackathon.agi.agibank.domain.enums.StatusEstado;
import com.hackathon.agi.agibank.domain.enums.StatusFuncionario;
import com.hackathon.agi.agibank.exceptions.almoxarifado.AlmoxarifadoNaoEncontradoException;
import com.hackathon.agi.agibank.exceptions.almoxarifado.DevolveEquipamentoNullException;
import com.hackathon.agi.agibank.exceptions.almoxarifado.EquipamentoNaoDisponivelException;
import com.hackathon.agi.agibank.mapper.AlmoxarifadoMapper;
import com.hackathon.agi.agibank.repository.AlmoxarifadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlmoxarifadoService {

    private final AlmoxarifadoRepository almoxarifadoRepository;
    private final FuncionarioService funcionarioService;
    private final EquipamentoService equipamentoService;
    private final CompraService compraService;
    private final AlmoxarifadoMapper almoxarifadoMapper;

    public AlmoxarifadoService(AlmoxarifadoRepository almoxarifadoRepository, FuncionarioService funcionarioService, EquipamentoService equipamentoService, CompraService compraService, AlmoxarifadoMapper almoxarifadoMapper) {
        this.almoxarifadoRepository = almoxarifadoRepository;
        this.funcionarioService = funcionarioService;
        this.equipamentoService = equipamentoService;
        this.compraService = compraService;
        this.almoxarifadoMapper = almoxarifadoMapper;
    }

    public AlmoxarifadoEmprestaResponse emprestarEquipamento(AlmoxarifadoEmprestaRequest historicoRequest) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(historicoRequest.idFuncionario());

        List<Equipamento> listaEquipamentosDisponiveis = verificaCategoriasLivres(historicoRequest.categoria());

        if (listaEquipamentosDisponiveis.size() == 0) {
            compraService.solicitarCompra(historicoRequest.categoria(), funcionario.getId());
            throw new EquipamentoNaoDisponivelException("Equipamento não disponivel!");
        }
        Equipamento equipamento = listaEquipamentosDisponiveis.getFirst();
        Almoxarifado almoxarifado = almoxarifadoMapper.historicoEmprestaRequestParaHistorico(historicoRequest.idFuncionario(), equipamento.getPatrimonio());
        almoxarifadoRepository.save(almoxarifado);
        AlmoxarifadoEmprestaResponse almoxarifadoResponse = almoxarifadoMapper.historicoParaHistoricoEmprestaResponse(almoxarifado, funcionario, equipamento);

        equipamentoService.alterarStatusEquipamento(equipamento.getPatrimonio(), StatusEquipamentosEmprestimo.EMPRESTADO);

        return almoxarifadoResponse;
    }

    public void devolverEquipamento(String idEquipamento) {
        Equipamento equipamento = equipamentoService.equipamentoPorId(idEquipamento);

        List<Almoxarifado> equipamentoPorId = almoxarifadoRepository.findByIdEquipamento(equipamento.getPatrimonio());
        Almoxarifado almoxarifado = equipamentoPorId.stream()
                .filter(alm -> alm.getDataDevolucao() == null)
                .findFirst().get();
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(almoxarifado.getIdFuncionario());
        almoxarifado.setDataDevolucao(LocalDateTime.now());
        almoxarifadoRepository.save(almoxarifado);
        if (funcionario.getStatus() == StatusFuncionario.PENDENTE) {
            List<Almoxarifado> listaPendencias = listarPendenciasFuncionario(funcionario.getId());
            if (listaPendencias.size() == 0) {
                funcionarioService.desligarFuncionario(funcionario.getId());
            }
        }
        if (almoxarifado == null) throw new DevolveEquipamentoNullException("Equipamento já se encontra devolvido.");

        equipamentoService.alterarStatusEquipamento(equipamento.getPatrimonio(), StatusEquipamentosEmprestimo.LIVRE);
    }

    public List<Almoxarifado> listarAlmoxarifado() {
        return almoxarifadoRepository.findAll();
    }

    public Almoxarifado listarAlmoxarifadoPorId(String id){
       return almoxarifadoRepository.findById(id)
               .orElseThrow(() -> new AlmoxarifadoNaoEncontradoException("Equipamento não encontrado"));
    }

    public List<Almoxarifado> listarPendenciasFuncionario(String idFuncionario) {
        List<Almoxarifado> listaAlmoxarifado = almoxarifadoRepository.findByIdFuncionario(idFuncionario);
        List<Almoxarifado> listaPendencias = listaAlmoxarifado.stream()
                .filter(alm -> alm.getDataDevolucao() == null)
                .toList();
        return listaPendencias;
    }

    public List<Equipamento> verificaCategoriasLivres(String categoria) {
        return equipamentoService.listarEquipamento().stream()
                .filter(equipamento -> equipamento.getStatus() == StatusEquipamentosEmprestimo.LIVRE)
                .filter(equipamento -> equipamento.getEstado() != StatusEstado.INUTILIZAVEL)
                .filter(equipamento -> equipamento.getCategoria().equals(categoria))
                .collect(Collectors.toList());
    }

}
