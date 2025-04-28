package com.hackathon.agi.agibank.entity.request;

import com.hackathon.agi.agibank.entity.StatusEnum;
import lombok.Data;

@Data
public class AlterarStatusRequest {
    private StatusEnum status;
}
