package com.hackathon.agi.agibank.domain;

import com.hackathon.agi.agibank.domain.enums.StatusCompra;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Compra {
    @Id
    private String id;
    private String idFuncionario;
    private String categoria;
    private StatusCompra status;


}

