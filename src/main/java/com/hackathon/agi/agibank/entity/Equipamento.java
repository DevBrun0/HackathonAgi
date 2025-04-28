package com.hackathon.agi.agibank.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class Equipamento {
    private String nome;
    private String marca;
    private LocalDateTime dataCompra;
    private StatusEnum status;
    private String id;
}
