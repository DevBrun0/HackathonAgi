package com.hackathon.agi.agibank.entity.request;

import com.hackathon.agi.agibank.entity.StatusEnum;
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
    private LocalDateTime dataCompra;
    @NotBlank
    private StatusEnum status;
}
