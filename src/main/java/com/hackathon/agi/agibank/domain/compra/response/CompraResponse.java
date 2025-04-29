package com.hackathon.agi.agibank.domain.compra.response;

import com.hackathon.agi.agibank.domain.nums.StatusCompra;

public record CompraResponse(String categoria, String nomeFuncionario, StatusCompra status) {
}
