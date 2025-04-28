package com.hackathon.agi.agibank.repository;

import com.hackathon.agi.agibank.entity.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FuncionarioRepository extends MongoRepository<Funcionario,String> {

}
