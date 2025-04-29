package com.hackathon.agi.agibank.exeptions;

public class FuncionarioExceptions extends RuntimeException{

    public FuncionarioExceptions(String id){
        super("a lista do id: "+id+" não foi encontrada!");
    }
}
