package com.hackathon.agi.agibank.domain.request;

import com.hackathon.agi.agibank.domain.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CadastrarEquipamentoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String marca;
    @NotBlank
    private String categoria;
    @NotBlank
    private LocalDateTime dataCompra;
    @NotBlank
    private StatusEnum status;
}
