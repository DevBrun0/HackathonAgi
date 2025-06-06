package com.hackathon.agi.agibank.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Almoxarifado {
    @Id
    private String id;

    @NotNull
    private String idEquipamento;
    @NotNull
    private String idFuncionario;
    private LocalDateTime dataEmprestimo = LocalDateTime.now();
    private LocalDateTime dataDevolucao;

    public Almoxarifado(String idFuncionario, String idEquipamento) {
        this.idFuncionario = idFuncionario;
        this.idEquipamento = idEquipamento;
    }
}
