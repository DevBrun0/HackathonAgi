package com.hackathon.agi.agibank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hackathon.agi.agibank.exceptions.almoxarifado.*;
import com.hackathon.agi.agibank.exceptions.compra.CompraNaoEncontradaException;
import com.hackathon.agi.agibank.exceptions.funcionario.FuncionarioExceptions;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler({
            AlterarStatusException.class,
            CompraNaoEncontradaException.class,
            FuncionarioExceptions.class,
            ListarEquipamentoNaoEncontradoException.class,
            EquipamentoNaoDisponivelException.class,
            EquipamentoFuncionarioNullException.class,
            DevolveEquipamentoNullException.class,
            AlmoxarifadoNaoEncontradoException.class
    })
    public ResponseEntity<String> handleCustomExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Erro interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
