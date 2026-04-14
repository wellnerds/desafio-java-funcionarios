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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 3.1 Inserir funcionários (EXEMPLO - adapte conforme tabela)
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 Remover João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // Formatadores
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat moedaFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        System.out.println("=== LISTA DE FUNCIONÁRIOS ===");

        // 3.3 Imprimir
        funcionarios.forEach(f -> {
            String info = String.format("%s - %s - R$ %s - %s",
                    f.getNome(),
                    f.getDataNascimento().format(dataFormatter),
                    moedaFormat.format(f.getSalario()),
                    f.getFuncao());
            System.out.println(info);
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