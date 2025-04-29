package com.hackathon.agi.agibank.domain.request;

import com.hackathon.agi.agibank.domain.Enums.StatusEnum;
import lombok.Data;

@Data
public class AlterarStatusRequest {
    private StatusEnum status;
}
