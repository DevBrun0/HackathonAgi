package com.hackathon.agi.agibank.service;


import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.request.AlterarStatusRequest;
import com.hackathon.agi.agibank.domain.request.CadastrarEquipamentoRequest;
import com.hackathon.agi.agibank.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private EquipamentoRepository equipamentoRepository;

    public void cadastrarEquipamento(CadastrarEquipamentoRequest request){
        var equipamento = Equipamento.builder()
                .nome(request.getNome())
                .marca(request.getMarca())
                .status(request.getStatus())
                .categoria(request.getCategoria())
                .dataCompra(request.getDataCompra())
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
        equipamento.setStatus(request.getStatus());
        equipamentoRepository.save(equipamento);
    }

    public Equipamento equipamentoPorId(String id) {
       Optional<Equipamento> equipamentoOpt = equipamentoRepository.findById(id);
        if(equipamentoOpt.isEmpty()){
            throw new RuntimeException("Equipamento não Encontrado id: " + id);
        }
        return equipamentoOpt.get();
    }
}
