package test.java.service;

import model.Funcionario;
import org.junit.jupiter.api.Test;
import service.FuncionarioService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioServiceTest {

    @Test
    void deveAplicarAumento() {
        Funcionario f = new Funcionario(
                "Teste",
                LocalDate.of(2000,1,1),
                new BigDecimal("1000"),
                "Dev"
        );

        FuncionarioService service = new FuncionarioService();
        service.aplicarAumento(List.of(f), 0.10);

        assertEquals(new BigDecimal("1100.0"), f.getSalario());
    }
}