package com.hackathon.agi.agibank.domain;

import com.hackathon.agi.agibank.domain.nums.StatusEstado;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Builder
@Data
public class Equipamento {
    @Id
    private String patrimonio;
    private String nome;
    private String marca;
    private String categoria;
    private LocalDateTime dataCompra;
    private StatusEstado status;
}
