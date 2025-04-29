package com.hackathon.agi.agibank.domain;

import com.hackathon.agi.agibank.domain.enums.StatusCompra;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Compra {
    @Id
    private String id;
    @NotNull
    private String idFuncionario;
    @NotNull
    private String categoria;
    private StatusCompra status;


}

