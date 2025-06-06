package com.hackathon.agi.agibank.service;


import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.enums.StatusEquipamentosEmprestimo;
import com.hackathon.agi.agibank.domain.equipamento.request.AlterarStatusRequest;
import com.hackathon.agi.agibank.domain.equipamento.request.CadastrarEquipamentoRequest;
import com.hackathon.agi.agibank.exceptions.AlterarStatusException;
import com.hackathon.agi.agibank.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public void cadastrarEquipamento(CadastrarEquipamentoRequest request){
        var equipamento = Equipamento.builder()
                .nome(request.getNome())
                .marca(request.getMarca())
                .estado(request.getStatus())
                .categoria(request.getCategoria())
                .dataCompra(request.getDataCompra())
                .status(StatusEquipamentosEmprestimo.LIVRE)
                .build();
        equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listarEquipamento(){
        return equipamentoRepository.findAll();
    }

    public void alterarEstado(String id, AlterarStatusRequest request){
        Optional<Equipamento> equipamentoOpt = equipamentoRepository.findById(id);
        if(equipamentoOpt.isEmpty()){
           throw new RuntimeException("Equipamento não Encontrado id: " + id);
        }
        Equipamento equipamento = equipamentoOpt.get();
        equipamento.setEstado(request.getStatus());
        equipamentoRepository.save(equipamento);
    }

    public Equipamento equipamentoPorId(String id) {
       Optional<Equipamento> equipamentoOpt = equipamentoRepository.findById(id);
        if(equipamentoOpt.isEmpty()){
            throw new RuntimeException("Equipamento não Encontrado id: " + id);
        }
        return equipamentoOpt.get();
    }

    public Equipamento alterarStatusEquipamento(String idEquipamento, StatusEquipamentosEmprestimo status) {

        Equipamento alterarEquipamento = equipamentoRepository.findById(idEquipamento)
                .orElseThrow(() -> new AlterarStatusException("Não foi possivel alterar status do equipamento"));


        alterarEquipamento.setStatus(status);

        return equipamentoRepository.save(alterarEquipamento);
    }
}
