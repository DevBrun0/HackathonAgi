package com.hackathon.agi.agibank.entity;

import com.hackathon.agi.agibank.entity.Enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Funcionario {
    @Id
    String id;
    String nomeCompleto;
    String cpf;
    String cargo;
    Status status;
}
