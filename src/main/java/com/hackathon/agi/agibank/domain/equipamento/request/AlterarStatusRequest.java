package com.hackathon.agi.agibank.domain.equipamento.request;

import com.hackathon.agi.agibank.domain.enums.StatusEstado;
import lombok.Data;

@Data
public class AlterarStatusRequest {
    private StatusEstado status;
}
