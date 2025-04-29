package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.domain.Almoxarifado;
import com.hackathon.agi.agibank.domain.almoxarifado.request.AlmoxarifadoEmprestaRequest;
import com.hackathon.agi.agibank.domain.almoxarifado.response.AlmoxarifadoEmprestaResponse;
import com.hackathon.agi.agibank.repository.AlmoxarifadoRepository;
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
    private final AlmoxarifadoRepository almoxarifadoRepository;

    @PostMapping("/emprestar")
    public ResponseEntity<?> emprestarEquipamento(@RequestBody AlmoxarifadoEmprestaRequest almoxarifadoEmprestaRequest) {
        AlmoxarifadoEmprestaResponse almoxarifadoEmpresta = almoxarifadoService.emprestarEquipamento(almoxarifadoEmprestaRequest);
        return ResponseEntity.ok(almoxarifadoEmpresta);
    }

    @PatchMapping("/devolver/{id}")
    public ResponseEntity<?> devolverEquipamento(@PathVariable String id){
        almoxarifadoService.devolverEquipamento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<?> listarAlmoxarifado(){
        List<Almoxarifado> listaAlmoxarifado = almoxarifadoService.listarAlmoxarifado();
        return ResponseEntity.status(HttpStatus.OK).body(listaAlmoxarifado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarAlmoxarifadoPorId(@PathVariable String id){
        Almoxarifado listaPorId = almoxarifadoService.listarAlmoxarifadoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(listaPorId);
    }

    @DeleteMapping
    public void deleta(){
        almoxarifadoRepository.deleteAll();
    }
}
