package com.hackathon.agi.agibank.controller;

import com.hackathon.agi.agibank.domain.Equipamento;
import com.hackathon.agi.agibank.domain.ErroResponse;
import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.enums.StatusFuncionario;
import com.hackathon.agi.agibank.domain.funcionario.request.FuncionarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FuncionarioApi {

    @Operation(summary = "Criação de funcionário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionário criado com sucesso",
                            content = @Content(schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\"mensagem\": \"Campo nome é obrigatório\"}"
                                    )
                            )),
                    @ApiResponse(responseCode = "404", description = "Não encontrado",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class)))
            })
    Funcionario criar(@RequestBody FuncionarioRequest funcionarioRequest);

    @Operation(summary = "Visualização de funcionários",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionários listados com sucesso",
                            content = @Content(schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\"mensagem\": \"Erro ao listar funcionários\"}"
                                    )
                            )),
                    @ApiResponse(responseCode = "404", description = "Não encontrado",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class)))
            })
    List<Funcionario> listar();

    @Operation(summary = "Buscar funcionário por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionário encontrado",
                            content = @Content(schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\"mensagem\": \"ID inválido fornecido\"}"
                                    )
                            )),
                    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\"mensagem\": \"Funcionário não encontrado\"}"
                                    )
                            ))
            })
    Funcionario buscarPorId(@PathVariable String id);

    @Operation(summary = "Alterar status do funcionário",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status alterado com sucesso",
                            content = @Content(schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\"mensagem\": \"Status inválido\"}"
                                    )
                            )),
                    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                            content = @Content(
                                    schema = @Schema(implementation = ErroResponse.class),
                                    examples = @ExampleObject(
                                            value = "{\"mensagem\": \"Funcionário não encontrado para alteração\"}"
                                    )
                            ))
            })
    List<Equipamento> desligarFuncionario(@PathVariable String id);
}
