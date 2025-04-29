package com.hackathon.agi.agibank.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.domain.enums.StatusFuncionario;
import com.hackathon.agi.agibank.domain.funcionario.request.FuncionarioRequest;
import com.hackathon.agi.agibank.exceptions.funcionario.FuncionarioExceptions;
import com.hackathon.agi.agibank.mapper.FuncionarioMapper;
import com.hackathon.agi.agibank.repository.FuncionarioRepository;
import com.hackathon.agi.agibank.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.mockito.Mockito.*;
class FuncionarioControllerTest {


        @InjectMocks
        private FuncionarioService funcionarioService;

        @Mock
        private FuncionarioRepository funcionarioRepository;

        @Mock
        private FuncionarioMapper funcionarioMapper;

        private Funcionario funcionario;
        private FuncionarioRequest funcionarioRequest;
        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            funcionario = new Funcionario();
            funcionario.setId("1");
            funcionario.setNomeCompleto("João Silva");
            funcionario.setCpf("12345678900");
            funcionario.setCargo("Desenvolvedor");
            funcionario.setStatus(StatusFuncionario.ATIVO);

            funcionarioRequest = new FuncionarioRequest(
                    "João Silva",
                    "12345678900",
                    "Desenvolvedor"

            );
        }


        @Test
        void deveListarTodosFuncionarios() {
            List<Funcionario> lista = List.of(funcionario);
            when(funcionarioRepository.findAll()).thenReturn(lista);

            List<Funcionario> resultado = funcionarioService.listarFuncionarios();

            assertEquals(1, resultado.size());
            assertEquals("João Silva", resultado.get(0).getNomeCompleto());
        }

        @Test
        void deveBuscarFuncionarioPorId() {
            when(funcionarioRepository.findById("1")).thenReturn(Optional.of(funcionario));

            Funcionario resultado = funcionarioService.buscarFuncionarioPorId("1");

            assertNotNull(resultado);
            assertEquals("12345678900", resultado.getCpf());
        }

        @Test
        void deveLancarExcecao_QuandoFuncionarioNaoEncontrado() {
            when(funcionarioRepository.findById("2")).thenReturn(Optional.empty());

            assertThrows(FuncionarioExceptions.class, () -> funcionarioService.buscarFuncionarioPorId("2"));
        }

        @Test
        void deveAlterarStatusDoFuncionario() {
            Funcionario funcionarioAtualizado = new Funcionario();
            funcionarioAtualizado.setStatus(StatusFuncionario.PENDENTE);

            when(funcionarioRepository.findById("1")).thenReturn(Optional.of(funcionario));
            when(funcionarioRepository.save(any())).thenReturn(funcionario);


            assertEquals(StatusFuncionario.PENDENTE, funcionarioAtualizado.getStatus());
        }



}