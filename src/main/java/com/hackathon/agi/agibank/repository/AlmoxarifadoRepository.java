package com.hackathon.agi.agibank.repository;

import com.hackathon.agi.agibank.entity.Almoxarifado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlmoxarifadoRepository extends MongoRepository<Almoxarifado, String> {
    List<Almoxarifado> findByIdEquipamento(String id);
}
