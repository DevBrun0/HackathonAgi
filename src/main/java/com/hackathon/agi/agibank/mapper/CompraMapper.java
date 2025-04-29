package com.hackathon.agi.agibank.mapper;

import com.hackathon.agi.agibank.domain.Compra;
import com.hackathon.agi.agibank.domain.compra.response.CompraResponse;
import org.springframework.stereotype.Component;

@Component
public class CompraMapper {
    public CompraResponse compraParaCompraResponse(Compra compra) {
        return new CompraResponse(
                compra.getCategoria(),
                compra.getIdFuncionario(),
                compra.getStatus()
        );
    }
}
