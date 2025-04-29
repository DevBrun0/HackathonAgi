package com.hackathon.agi.agibank.mapper;

import com.hackathon.agi.agibank.domain.Almoxarifado;
import com.hackathon.agi.agibank.domain.almoxarifado.response.AlmoxarifadoEmprestaResponse;
import com.hackathon.agi.agibank.domain.almoxarifado.response.AlmoxarifadoListaResponse;

public class AlmoxarifadoMapper {


    public Almoxarifado historicoEmprestaRequestParaHistorico(String idFuncionario, String idEquipamento) {
        return new Almoxarifado(
                idFuncionario,
                idEquipamento
        );
    }


    public AlmoxarifadoEmprestaResponse historicoParaHistoricoEmprestaResponse(Almoxarifado almoxarifado, Funcionario funcionario, Equipamento equipamento) {
        return new AlmoxarifadoEmprestaResponse(
                almoxarifado.getId(),
                funcionario.getNomeCompleto(),
                equipamento.getNome(),
                almoxarifado.getDataEmprestimo()
        );
    }

    public AlmoxarifadoListaResponse listaAlmoxarifadoResponse(){
        return new AlmoxarifadoListaResponse(

        );
    }

}
