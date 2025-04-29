package com.hackathon.agi.agibank.service;

import com.hackathon.agi.agibank.domain.Compra;
import com.hackathon.agi.agibank.domain.compra.response.CompraResponse;
import com.hackathon.agi.agibank.domain.nums.StatusCompra;
import com.hackathon.agi.agibank.exceptions.compra.CompraNaoEncontradaException;
import com.hackathon.agi.agibank.mapper.CompraMapper;
import com.hackathon.agi.agibank.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final CompraMapper compraMapper;

    public void solicitarCompra(String categoria, String idFuncionario) {
        Compra compra = new Compra();
        compra.setCategoria(categoria);
        compra.setIdFuncionario(idFuncionario);
        compra.setStatus(StatusCompra.PENDENTE);

        compraRepository.save(compra);
    }

    public List<CompraResponse> buscarTodasSolicitacoes() {
        List<Compra> listaCompra = compraRepository.findAll();

        return listaCompra.stream()
                .map(compra -> compraMapper.compraParaCompraResponse(compra))
                .toList();
    }

    public List<CompraResponse> buscarSolicitacoesPorFuncionario(String idFuncionario) {
        List<Compra> listaCompra = compraRepository.findAll();

        return listaCompra.stream()
                .filter(compra -> compra.getIdFuncionario().equals(idFuncionario))
                .map(compra -> compraMapper.compraParaCompraResponse(compra))
                .toList();
    }

    public CompraResponse receberCompra(String idCompra) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new CompraNaoEncontradaException("Compra não registrada no sistema!"));

        compra.setStatus(StatusCompra.ENTREGUE);
        compraRepository.save(compra);
        return compraMapper.compraParaCompraResponse(compra);
    }



}
