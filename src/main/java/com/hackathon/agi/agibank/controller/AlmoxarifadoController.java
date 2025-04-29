package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.entity.Almoxarifado;
import com.hackathon.agi.agibank.entity.almoxarifado.request.AlmoxarifadoEmprestaRequest;
import com.hackathon.agi.agibank.entity.almoxarifado.response.AlmoxarifadoDevolveResponse;
import com.hackathon.agi.agibank.entity.almoxarifado.response.AlmoxarifadoEmprestaResponse;
import com.hackathon.agi.agibank.service.AlmoxarifadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almoxarifado")
@RequiredArgsConstructor
public class AlmoxarifadoController {

    private final AlmoxarifadoService almoxarifadoService;

    @PostMapping("/emprestar")
    public ResponseEntity<?> emprestarEquipamento(@RequestBody AlmoxarifadoEmprestaRequest almoxarifadoEmprestaRequest) {
        AlmoxarifadoEmprestaResponse almoxarifadoEmpresta = almoxarifadoService.emprestarEquipamento(almoxarifadoEmprestaRequest);
        return ResponseEntity.ok(almoxarifadoEmpresta);
    }

    @PatchMapping("/devolver/{id}")
    public ResponseEntity<?> devolverEquipamento(@RequestParam String id){
        almoxarifadoService.devolverEquipamento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<?> listarAlmoxarifado(Almoxarifado almoxarifado){
        List<Almoxarifado> listaAlmoxarifado = almoxarifadoService.listarAlmoxarifado();
        return ResponseEntity.status(HttpStatus.OK).body(listaAlmoxarifado);
    }


}
