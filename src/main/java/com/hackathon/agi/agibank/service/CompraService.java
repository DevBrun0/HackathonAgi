package com.hackathon.agi.agibank.service;

import com.hackathon.agi.agibank.domain.Compra;
import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.compra.response.CompraResponse;
import com.hackathon.agi.agibank.domain.enums.StatusCompra;
import com.hackathon.agi.agibank.exceptions.compra.CompraNaoEncontradaException;
import com.hackathon.agi.agibank.mapper.CompraMapper;
import com.hackathon.agi.agibank.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final CompraMapper compraMapper;
    private final FuncionarioService funcionarioService;

    public void solicitarCompra(String categoria, String idFuncionario) {
        Compra compra = new Compra();
        compra.setCategoria(categoria);
        compra.setIdFuncionario(idFuncionario);
        compra.setStatus(StatusCompra.PENDENTE);

        compraRepository.save(compra);
    }

    public List<CompraResponse> buscarTodasSolicitacoes() {
        List<Compra> listaCompra = compraRepository.findAll();
        List<Funcionario> listaFuncionario = funcionarioService.listarFuncionarios();

        return listaCompra.stream()
                .map(compra -> {
                    Funcionario funcionario = listaFuncionario.stream()
                            .filter(f -> f.getId().equals(compra.getIdFuncionario()))
                            .findFirst()
                            .orElse(null);

                    String nomeFuncionario = (funcionario != null) ? funcionario.getNomeCompleto() : "N/A";
                    return compraMapper.compraParaCompraResponse(compra, nomeFuncionario);
                })
                .toList();
    }

    public Compra buscarSolicitacoesPorId(String id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new CompraNaoEncontradaException("Compra não encontrada!"));
    }


    public CompraResponse receberCompra(String idCompra) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new CompraNaoEncontradaException("Compra não registrada no sistema!"));
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(compra.getIdFuncionario());
        compra.setStatus(StatusCompra.ENTREGUE);
        compraRepository.save(compra);
        return compraMapper.compraParaCompraResponse(compra, funcionario.getNomeCompleto());
    }



}
