package com.hackathon.agi.agibank.service;

import com.hackathon.agi.agibank.domain.Almoxarifado;
import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.almoxarifado.request.AlmoxarifadoEmprestaRequest;
import com.hackathon.agi.agibank.domain.almoxarifado.response.AlmoxarifadoEmprestaResponse;
import com.hackathon.agi.agibank.exceptions.almoxarifado.AlmoxarifadoNaoEncontradoException;
import com.hackathon.agi.agibank.exceptions.almoxarifado.DevolveEquipamentoNullException;
import com.hackathon.agi.agibank.exceptions.almoxarifado.EquipamentoNaoDisponivelException;
import com.hackathon.agi.agibank.mapper.AlmoxarifadoMapper;
import com.hackathon.agi.agibank.repository.AlmoxarifadoRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlmoxarifadoService {

    private final AlmoxarifadoRepository almoxarifadoRepository;
    private final FuncionarioService funcionarioService;
    private final EquipamentoService equipamentoService;
    private final CompraService compraService;
    private final AlmoxarifadoMapper almoxarifadoMapper;

    public AlmoxarifadoEmprestaResponse emprestarEquipamento(AlmoxarifadoEmprestaRequest historicoRequest) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(historicoRequest.idFuncionario());

        List<Equipamento> equipamentosDisponiveis = categoriaLivre(historicoRequest.categoria());
        if (equipamentosDisponiveis.size() == 0) {
            compraService.solicitarCompra(historicoRequest.categoria(), historicoRequest.idFuncionario());
            throw new EquipamentoNaoDisponivelException("Equipamento não livre!");
        }
        Equipamento equipamento = equipamentosDisponiveis.getFirst();
        Almoxarifado almoxarifado = almoxarifadoMapper.historicoEmprestaRequestParaHistorico(historicoRequest.idFuncionario(), equipamento.getPatrimonio());
        almoxarifadoRepository.save(almoxarifado);
        AlmoxarifadoEmprestaResponse almoxarifadoResponse = almoxarifadoMapper.historicoParaHistoricoEmprestaResponse(almoxarifado, funcionario, equipamento);
        return almoxarifadoResponse;
    }

    public void devolverEquipamento(String idEquipamento) {
        Equipamento equipamento = equipamentoService.equipamentoPorId(idEquipamento);

        List<Almoxarifado> listaAlmoxarifado = almoxarifadoRepository.findByIdEquipamento(equipamento.getPatrimonio());
        Almoxarifado almoxarifado = listaAlmoxarifado.stream()
                .filter(alm -> alm.getDataDevolucao() == null)
                .findFirst().get();
        if (almoxarifado == null) throw new DevolveEquipamentoNullException("Equipamento já se encontra devolvido.");
    }

    public List<Equipamento> categoriaLivre(String categoria) {
        List<Equipamento> equipamentosLiberados = new ArrayList<>();

        List<Almoxarifado> listCompletaAlmoxarifado = almoxarifadoRepository.findAll();

        listCompletaAlmoxarifado.stream()
                .filter(almoxarifado -> almoxarifado.getDataDevolucao() != null)
                .forEach(almoxarifado -> equipamentosLiberados.add(
                        equipamentoService.equipamentoPorId(almoxarifado.getIdEquipamento()))
                );

        List<Equipamento> listaEquipamentoFiltrada = equipamentosLiberados.stream()
                .filter(equipamento -> equipamento.getCategoria().equals(categoria))
                .toList();
        return listaEquipamentoFiltrada;
    }

    public List<Almoxarifado> listarAlmoxarifado() {
        return almoxarifadoRepository.findAll();
    }

    public Almoxarifado listarAlmozarifadoPorId(String id){
           return almoxarifadoRepository.findById(id)
                   .orElseThrow(() -> new AlmoxarifadoNaoEncontradoException("Equipamento não encontrado"));
    }
}
