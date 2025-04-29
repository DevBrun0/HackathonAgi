package com.hackathon.agi.agibank.domain.compra.response;

import com.hackathon.agi.agibank.domain.enums.StatusCompra;

public record CompraResponse(String id, String categoria, String nomeFuncionario, StatusCompra status) {
}
