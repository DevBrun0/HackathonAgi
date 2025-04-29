package com.hackathon.agi.agibank.repository;

import com.hackathon.agi.agibank.domain.Equipamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipamentoRepository extends MongoRepository <Equipamento, String> {

}
