package com.hackathon.agi.agibank.repository;

import com.hackathon.agi.agibank.domain.Equipamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends MongoRepository <Equipamento, String> {

}
