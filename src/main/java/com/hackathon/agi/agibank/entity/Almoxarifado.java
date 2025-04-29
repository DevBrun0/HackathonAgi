package com.hackathon.agi.agibank.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Almoxarifado {
    @Id
    private String id;

    private String idEquipamento;
    private String idFuncionario;
    private LocalDateTime dataEmprestimo = LocalDateTime.now();
    private LocalDateTime dataDevolucao;

    public Almoxarifado(String idFuncionario, String idEquipamento) {
        this.idFuncionario = idFuncionario;
        this.idEquipamento = idEquipamento;
    }
}
