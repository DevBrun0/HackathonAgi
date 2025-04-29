package com.hackathon.agi.agibank.repository;

import com.hackathon.agi.agibank.domain.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario,String> {

}
