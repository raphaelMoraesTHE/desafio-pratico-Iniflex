
import model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {
	
    public static void main(String[] args) {

    	// Formatadores
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00");
        
        
        // 3.1 Inserir todos os funcionários, na mesma ordem e informações da tabela.
        List<Funcionario> funcionarios = new ArrayList<>();
        	funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        	funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        	funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        	funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        	funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        	funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        	funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        	funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        	funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        	funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 Remover o funcionário “João” da lista.
        	Funcionario remover = null;
            for (Funcionario f : funcionarios) {
                if (f.getNome().equals("João")) {
                    remover = f;
                    break;
                }
            }
            if (remover != null) {
                funcionarios.remove(remover);
            }

        // 3.3 Imprimir todos os funcionários com todas suas informações, sendo que:
        //		• informação de data deve ser exibido no formato dd/mm/aaaa;
        //		• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
            System.out.println("---- Funcionários ----");
            for (Funcionario f : funcionarios) {
                System.out.println("Nome: " + f.getNome()
                        + " | Data Nascimento: " + f.getDataNascimento().format(dateFormatter)
                        + " | Salário: R$ " + decimalFormatter.format(f.getSalario())
                        + " | Função: " + f.getFuncao());
            }

        // 3.4 Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
            for (Funcionario f : funcionarios) {
                BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
                f.setSalario(novoSalario);
            }

        // 3.5 Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
            Map<String, List<Funcionario>> porFuncao = new HashMap<>();
            for (Funcionario f : funcionarios) {
                if (!porFuncao.containsKey(f.getFuncao())) {
                    porFuncao.put(f.getFuncao(), new ArrayList<>());
                }
                porFuncao.get(f.getFuncao()).add(f);
            }

        // 3.6 Imprimir os funcionários, agrupados por função.
            System.out.println("\n---- Funcionários agrupados por função ----");
            for (String funcao : porFuncao.keySet()) {
                System.out.println("Função: " + funcao);
                for (Funcionario f : porFuncao.get(funcao)) {
                    System.out.println(" - " + f.getNome());
                }
            }

        // 3.8 Imprimir os funcionários que fazem aniversário no mês 10 (Outubro) e 12 (Dezembro).
        System.out.println("\n---- Aniversariantes em Outubro e Dezembro ----");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println(f.getNome() + " - " + f.getDataNascimento().format(dateFormatter));
            }
        }

        // 3.9 Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario maisVelho = funcionarios.get(0);
        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = f;
            }
        }
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\nFuncionário mais velho: " + maisVelho.getNome() + " | Idade: " + idade);

        // 3.10  Imprimir a lista de funcionários por ordem alfabética.
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));
        System.out.println("\n---- Funcionários em ordem alfabética ----");
        for (Funcionario f : funcionarios) {
            System.out.println(f.getNome());
        }
        
        // 3.11 Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.getSalario());
        }
        System.out.println("\nTotal dos salários: R$ " + decimalFormatter.format(totalSalarios));

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n---- Equivalência em salários mínimos ----");
        for (Funcionario f : funcionarios) {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtd + " salários mínimos.");
        }
    }
}