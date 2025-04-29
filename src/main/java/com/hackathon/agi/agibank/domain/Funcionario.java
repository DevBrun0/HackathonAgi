package com.hackathon.agi.agibank.domain;

import com.hackathon.agi.agibank.domain.enums.StatusFuncionario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Funcionario {
    @Id
    @Schema(description = "id do funcionário", example = "s21uiewb23iurb34f")
    String id;

    @Schema(description = "Nome completo do funcionário", example = "João Silva")
    String nomeCompleto;

    @Schema(description = "CPF do funcionário (somente números)", example = "12345678900")
    String cpf;

    @Schema(description = "Cargo ocupado pelo funcionário", example = "Desenvolvedor Back-end")
    String cargo;

    @Schema(description = "Status atual do funcionário", example = "ATIVO")
    StatusFuncionario status = StatusFuncionario.ATIVO;

    public Funcionario(String nomeCompleto, String cpf, String cargo) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.cargo = cargo;
    }
}
