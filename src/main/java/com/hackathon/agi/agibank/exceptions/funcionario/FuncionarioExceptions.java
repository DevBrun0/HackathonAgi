package com.hackathon.agi.agibank.exceptions.funcionario;

public class FuncionarioExceptions extends RuntimeException{

    public FuncionarioExceptions(String id){
        super("a lista do id: "+id+" não foi encontrada!");
    }
}
