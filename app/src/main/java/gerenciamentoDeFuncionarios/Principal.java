package gerenciamentoDeFuncionarios;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Principal {
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static Map<String, ArrayList<Funcionario>> funcionariosPorFuncao = new HashMap<>();

    public static void main( String[] args ) {
        Principal.inserirTodosOsFuncionários();

        Scanner scanner = new Scanner(System.in);
        byte respCode = 0;

        do {
            System.out.println("\n\n\n\n\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Entre com o número correspondente à opção desejada:");
            System.out.println("1 - Remover o funcionário “João” da lista.");
            System.out.println("2 - Os funcionários receberam 10% de aumento de salário.");
            System.out.println("3 - Imprimir os funcionários, agrupados por função.");
            System.out.println("4 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.");
            System.out.println("5 - Imprimir o funcionário com a maior idade.");
            System.out.println("6 - Imprimir a lista de funcionários por ordem alfabética.");
            System.out.println("7 - Imprimir o total dos salários dos funcionários.");
            System.out.println("8 - Imprimir quantos salários mínimos ganha cada funcionário.");
            System.out.println("9 - Imprimir todos os funcionários com todas suas informações.");
            System.out.println("10 - Finalizar sistema.");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("Sua resposta: ");
            respCode = scanner.nextByte();

            System.out.println("\nResultado:\n");
            if (respCode == 1) {
                Principal.removerOFuncionarioJoaoDaLista();
                System.out.println("Funcionário João removido");
            }
            if (respCode == 2) {
                Principal.funcionariosReceberamAumentoDeSalárioDeDezPorCento();
                System.out.println("Todos os funcionários receberam 10% de aumento de salário");
            }
            if (respCode == 3) {
                Principal.agrupaOsFuncionariosPorFuncaoEmUmMAP();
                System.out.println(Principal.funcionariosPorFuncao);
            }
            if (respCode == 4) System.out.println(Principal.funcionariosQueFazemAniversarioNoMes10e12());
            if (respCode == 5) System.out.println(Principal.funcionarioComAMaiorIdade());
            if (respCode == 6) System.out.println(Principal.funcionariosPorOrdemAlfabetica());
            if (respCode == 7) System.out.println(Principal.totalDosSalariosDosFuncionarios());
            if (respCode == 8) System.out.println(Principal.quantosSalariosMinimosGanhaCadaFuncionario());
            if (respCode == 9) System.out.println(Principal.funcionarios);
            if (respCode == 10) System.out.println("Sistema finalizado.");
        } while (respCode != 10);

        scanner.close();
    }

    public static void inserirTodosOsFuncionários() {
        Principal.funcionarios.add(new Funcionario("Maria", "18/10/2000", new BigDecimal("2009.44"), "Operador"));
        Principal.funcionarios.add(new Funcionario("João", "12/05/1990", new BigDecimal("2284.38"), "Operador"));
        Principal.funcionarios.add(new Funcionario("Caio", "02/05/1961", new BigDecimal("9836.14"), "Coordenador"));
        Principal.funcionarios.add(new Funcionario("Miguel", "14/10/1988", new BigDecimal("19119.88"), "Diretor"));
        Principal.funcionarios.add(new Funcionario("Alice", "05/01/1995", new BigDecimal("2234.68"), "Recepcionista"));
        Principal.funcionarios.add(new Funcionario("Heitor", "19/11/1999", new BigDecimal("1582.72"), "Operador"));
        Principal.funcionarios.add(new Funcionario("Arthur", "31/03/1993", new BigDecimal("4071.84"), "Contador"));
        Principal.funcionarios.add(new Funcionario("Laura", "08/07/1994", new BigDecimal("3017.45"), "Gerente"));
        Principal.funcionarios.add(new Funcionario("Heloísa", "24/05/2003", new BigDecimal("1606.85"), "Eletricista"));
        Principal.funcionarios.add(new Funcionario("Helena", "02/09/1996", new BigDecimal("2799.93"), "Gerente"));
    }

    public static void removerOFuncionarioJoaoDaLista() {
        for (int i = 0; i < Principal.funcionarios.size(); i++) {
            if (Principal.funcionarios.get(i).getNome().equals("João")) {
                Principal.funcionarios.remove(i);
            };
        }
    }

    public static void funcionariosReceberamAumentoDeSalárioDeDezPorCento() {
        for (Funcionario funcionario : Principal.funcionarios) {
            Double novoSalario = new Double(funcionario.getSalario()
                    .replace(".", "").replace(",", ".")) * 1.1;
            funcionario.setSalario(new BigDecimal(novoSalario));
        }
    }

    public static void agrupaOsFuncionariosPorFuncaoEmUmMAP() {
        Principal.funcionariosPorFuncao.clear();
        for (Funcionario i : Principal.funcionarios) {
            String funcao = i.getFuncao();
            if (!Principal.funcionariosPorFuncao.containsKey(funcao)) {
                ArrayList<Funcionario> auxList = new ArrayList<>();
                for (Funcionario f : Principal.funcionarios) {
                    if (funcao.equals(f.getFuncao())) {
                        auxList.add(f);
                    }
                }
                Principal.funcionariosPorFuncao.put(funcao, auxList);
            }
        }
    }

    public static ArrayList<Funcionario> funcionariosQueFazemAniversarioNoMes10e12() {
        ArrayList<Funcionario> auxList = new ArrayList<>();
        for (Funcionario funcionario : Principal.funcionarios) {
            String mes = funcionario.getDataNascimento().split("/")[1];
            if (mes.equals("10") || mes.equals("12")) {
                auxList.add(funcionario);
            }
        }
        return auxList;
    }

    public static String funcionarioComAMaiorIdade() {
        int idade = LocalDate.now().getYear() - new Integer(Principal.funcionarios.get(0).getDataNascimento().split("/")[2]);
        String nome = Principal.funcionarios.get(0).getNome();
        for (Funcionario funcionario : Principal.funcionarios) {
            int auxIdade = LocalDate.now().getYear() - new Integer(funcionario.getDataNascimento().split("/")[2]);
            if (auxIdade > idade) {
                idade = auxIdade;
                nome = funcionario.getNome();
            }
        }
        return "Nome: " + nome + ", " + "Idade: " + idade;
    }

    public static ArrayList<Funcionario> funcionariosPorOrdemAlfabetica() {
        ArrayList<Funcionario> listaOrdenada = new ArrayList<>(Principal.funcionarios);
        Comparator<Funcionario> comparador = new Comparator<Funcionario>() {
            public int compare(Funcionario f1, Funcionario f2) {
                return f1.getNome().compareTo(f2.getNome());
            };
        };
        Collections.sort(listaOrdenada, comparador);
        return listaOrdenada;
    }

    public static String totalDosSalariosDosFuncionarios() {
        Double totalSalario = 0.0;
        for (Funcionario funcionario : Principal.funcionarios) {
            totalSalario += new Double(funcionario.getSalario().replace(".", "").replace(",", "."));
        }
        return "Soma dos salários dos funcionários: "
                + new DecimalFormat("#,##0.00").format(totalSalario);
    }

    public static Map<String, String> quantosSalariosMinimosGanhaCadaFuncionario() {
        Map<String, String> funcionariosSalariosMinimos = new HashMap<>();
        for (Funcionario funcionario : Principal.funcionarios) {
            Double qtSalMin = new Double(funcionario.getSalario().replace(".", "").replace(",", ".")) / 1212.00;
            DecimalFormat df = new DecimalFormat("#.00");
            funcionariosSalariosMinimos.put(funcionario.getNome(), df.format(qtSalMin));
        }
        return funcionariosSalariosMinimos;
    }
}


