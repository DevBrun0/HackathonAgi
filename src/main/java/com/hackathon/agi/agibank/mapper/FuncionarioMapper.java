package com.hackathon.agi.agibank.mapper;

import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.funcionario.request.FuncionarioRequest;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {
    public Funcionario funcionarioRequestParaFuncionario(FuncionarioRequest funcionarioRequest) {
        return new Funcionario(
                funcionarioRequest.nomeCompleto(),
                funcionarioRequest.cpf(),
                funcionarioRequest.cargo()
        );
    }
}
