package main;

import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 Inserir funcionários (EXEMPLO - adapte conforme tabela)
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2000"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("3000"), "Gerente"));
        funcionarios.add(new Funcionario("Ana", LocalDate.of(1985, 12, 2), new BigDecimal("2500"), "Operador"));

        // 3.2 Remover João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // Formatadores
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat moedaFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        System.out.println("=== LISTA DE FUNCIONÁRIOS ===");

        // 3.3 Imprimir
        funcionarios.forEach(f -> {
            System.out.println(
                    f.getNome() + " | " +
                    f.getFuncao() + " | " +
                    dataFormatter.format(f.getDataNascimento()) + " | R$ " +
                    moedaFormat.format(f.getSalario())
            );
        });

        // 3.4 Aumento de 10%
        funcionarios.forEach(f -> {
            f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")));
        });

        // 3.5 Agrupar por função
        Map<String, List<Funcionario>> agrupados =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 Imprimir agrupados
        System.out.println("\n=== AGRUPADOS POR FUNÇÃO ===");
        agrupados.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(f -> System.out.println(" - " + f.getNome()));
        });

        // 3.8 Aniversário mês 10 e 12
        System.out.println("\n=== ANIVERSARIANTES (OUTUBRO E DEZEMBRO) ===");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10
                          || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome()));

        // 3.9 Mais velho
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("\nMais velho: " + maisVelho.getNome() + " - " + idade + " anos");
        }

        // 3.10 Ordem alfabética
        System.out.println("\n=== ORDEM ALFABÉTICA ===");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));

        // 3.11 Total salários
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal salários: R$ " + moedaFormat.format(total));

        // 3.12 Salários mínimos
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\n=== SALÁRIOS MÍNIMOS ===");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + qtd + " salários mínimos");
        });
    }
}