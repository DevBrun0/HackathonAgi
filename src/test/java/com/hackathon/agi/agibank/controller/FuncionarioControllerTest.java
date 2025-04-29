package com.hackathon.agi.agibank.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.hackathon.agi.agibank.domain.Enums.Status;
import com.hackathon.agi.agibank.domain.Funcionario;
import com.hackathon.agi.agibank.exeptions.FuncionarioExceptions;
import com.hackathon.agi.agibank.repository.FuncionarioRepository;
import com.hackathon.agi.agibank.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class FuncionarioControllerTest {


        @InjectMocks
        private FuncionarioService funcionarioService;

        @Mock
        private FuncionarioRepository funcionarioRepository;

        private Funcionario funcionario;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            funcionario = new Funcionario();
            funcionario.setId("1");
            funcionario.setNomeCompleto("João Silva");
            funcionario.setCpf("12345678900");
            funcionario.setCargo("Desenvolvedor");
            funcionario.setStatus(Status.ATIVO);
        }

        @Test
        void deveCriarFuncionarioComSucesso() {
            when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

            Funcionario resultado = funcionarioService.criarFuncionario(funcionario);

            assertNotNull(resultado);
            assertEquals("João Silva", resultado.getNomeCompleto());
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
            funcionarioAtualizado.setStatus(Status.PENDENTE);

            when(funcionarioRepository.findById("1")).thenReturn(Optional.of(funcionario));
            when(funcionarioRepository.save(any())).thenReturn(funcionario);

            Funcionario resultado = funcionarioService.alterarStatus("1", funcionarioAtualizado);

            assertEquals(Status.PENDENTE, resultado.getStatus());
        }



}