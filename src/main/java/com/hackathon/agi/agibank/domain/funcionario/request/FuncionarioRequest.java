package com.hackathon.agi.agibank.domain.funcionario.request;

import com.hackathon.agi.agibank.domain.enums.StatusFuncionario;

public record FuncionarioRequest(String nomeCompleto, String cpf, String cargo) {
}
