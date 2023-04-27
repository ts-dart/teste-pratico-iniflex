package gerenciamentoDeFuncionarios;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.text.DecimalFormat;

public class Principal {
  private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
	private static Map<String, ArrayList<Funcionario>> funcionariosPorFuncao = new HashMap<>();

  public static void main( String[] args ) {
    Principal.inserirTodosOsFuncionários();
		Principal.removerOFuncionarioJoaoDaLista();
		Principal.funcionariosReceberamAumentoDeSalárioDeDezPorCento();
		Principal.agrupaOsFuncionariosPorFuncaoEmUmMAP();
		System.out.println(Principal.funcionariosPorFuncao);
		System.out.println(Principal.funcionariosQueFazemAniversarioNoMes10e12());
		System.out.println(Principal.funcionarioComAMaiorIdade());
		System.out.println(Principal.funcionariosPorOrdemAlfabetica());
		System.out.println(Principal.totalDosSalariosDosFuncionarios());
		System.out.println(Principal.quantosSalariosMinimosGanhaCadaFuncionario());

		//System.out.println(Principal.funcionarios);
  }

  static void inserirTodosOsFuncionários() {
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

	static void removerOFuncionarioJoaoDaLista() {
		for (int i = 0; i < Principal.funcionarios.size(); i++) {
			if (Principal.funcionarios.get(i).getNome().equals("João")) {
				Principal.funcionarios.remove(i);
			};
		}
	}

	static void funcionariosReceberamAumentoDeSalárioDeDezPorCento() {
		for (Funcionario funcionario : Principal.funcionarios) {
			Double novoSalario = new Double(funcionario.getSalario()
				.replace(".", "").replace(",", ".")) * 1.1;
			funcionario.setSalario(new BigDecimal(novoSalario));
		}
	}

	static void agrupaOsFuncionariosPorFuncaoEmUmMAP() {
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

	static ArrayList<Funcionario> funcionariosQueFazemAniversarioNoMes10e12() {
		ArrayList<Funcionario> auxList = new ArrayList<>();
		for (Funcionario funcionario : Principal.funcionarios) {
			String mes = funcionario.getDataNascimento().split("/")[1];
			if (mes.equals("10") || mes.equals("12")) {
				auxList.add(funcionario);
			}
		}
		return auxList;
	}

	static String funcionarioComAMaiorIdade() {
		int idade = LocalDate.now().getYear() 
			- new Integer(Principal.funcionarios.get(0).getDataNascimento().split("/")[2]);
		String nome = Principal.funcionarios.get(0).getNome();
		for (Funcionario funcionario : Principal.funcionarios) {
			int auxIdade = LocalDate.now().getYear() 
				- new Integer(funcionario.getDataNascimento().split("/")[2]);
			if (auxIdade > idade) {
				idade = auxIdade;
				nome = funcionario.getNome();
			}
		}
		return "Nome: " + nome + ", " + "Idade: " + idade;
	}

	static ArrayList<Funcionario> funcionariosPorOrdemAlfabetica() {
		ArrayList<Funcionario> listaOrdenada = Principal.funcionarios;
		Comparator<Funcionario> comparador = new Comparator<Funcionario>() {
			public int compare(Funcionario f1, Funcionario f2) {
					return f1.getNome().compareTo(f2.getNome());
			}
		};
		Collections.sort(listaOrdenada, comparador);
		return listaOrdenada;
	}

	static String totalDosSalariosDosFuncionarios() {
		Double totalSalario = 0.0;
		for (Funcionario funcionario : Principal.funcionarios) {
			totalSalario += new Double(funcionario.getSalario().replace(".", "").replace(",", "."));
		}
		return "Soma dos salários dos funcionários: " 
			+ new DecimalFormat("#,##0.00").format(totalSalario);
	}

	static Map<String, String> quantosSalariosMinimosGanhaCadaFuncionario() {
		Map<String, String> funcionariosSalariosMinimos = new HashMap<>();
		for (Funcionario funcionario : Principal.funcionarios) {
			Double qtSalMin = new Double(funcionario.getSalario().replace(".", "").replace(",", "."))
				/ 1212.00;
			DecimalFormat df = new DecimalFormat("#.00");
			funcionariosSalariosMinimos.put(funcionario.getNome(), df.format(qtSalMin));
		}
		return funcionariosSalariosMinimos;
	}
}


