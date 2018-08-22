import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroMain {
	private Scanner entrada;
	private boolean execute;
	private List<Cadastro> cadastro;

	public static void main(String[] args) {
		new CadastroMain();
	}

	private CadastroMain() {
		entrada = new Scanner(System.in);
		execute = true;
		cadastro = new ArrayList<Cadastro>();
		System.out.println("BEM VINDO AO CADASTRO DE USUÁRIOS");
		while (execute) {
			String opcao = menu();
			if (opcao.equals("1")) {
				cadastrar();
			} else if (opcao.equals("2")) {
				listarCadastros();
			} else if (opcao.equals("3")) {
				removerCadastro();
			} else if (opcao.equals("4")) {
				atualizarCadastro();
			} else if (opcao.equals("5")) {
				execute = false;
			} else {
				System.out.println("Opção Inválida!!!");
			}
		}
	}

	private String menu() {
		System.out.println("Selecione a opção:");
		System.out.println("1 - Novo cadastro");
		System.out.println("2 - Listar cadastros");
		System.out.println("3 - Remover cadastro");
		System.out.println("4 - Atualizar cadastro");
		System.out.println("5 - Sair");
		return entrada.nextLine();
	}

	private void cadastrar() {
		boolean cadastrando = true;
		while (cadastrando) {
			System.out.println("Cadastro de Usuário");
			Cadastro cad = new Cadastro();

			cad.setNome(textInput("Nome: "));

			System.out.println("Idade: ");
			cad.setIdade(verificaIdade());

			String cadastrar = textInput("Adicionar cadastro (S/N)?");
			if (cadastrar.equalsIgnoreCase("s")) {
				System.out.println("Cadastro adicionado!");
				cadastro.add(cad);
			} else if (cadastrar.equalsIgnoreCase("n")) {
				System.out.println("Cadastro ignorado!");
			} else {
				System.out.println("Opção inválida, cadastro ignorado!");
			}
			String continua = textInput("Continuar cadastrando (S/N)?");
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")) {
			} else {
				System.out.println("\nOpção inválida !!! \n");
				cadastrando = false;
			}
		}
	}

	private void listarCadastros() {
		if (cadastro.size() == 0) {
			System.out.println("\nNão existem cadastros !!!\n");
		} else {
			System.out.println("\nLista de Cadastros\n");
			for (int i = 0; i < cadastro.size(); i++) {
				Cadastro d = cadastro.get(i);
				System.out.println("Cadastro número: " + i);
				System.out.println("Nome: " + d.getNome());
				System.out.println("Idade: " + d.getIdade());
				System.out.println("");
			}
			System.out.println("Fim da lista");
		}
	}

	private void removerCadastro() { // método para remover cadastro, baseado no
										// código passado na listagem
		boolean confere = true;
		while (confere) {
			System.out.println("Indique o número de cadastro para remover: ");
			int codigo = verificaCodigo();
			try {
				cadastro.remove(codigo);
				confere = false;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Código inválido, posição não confere");
				confere = true;
			}
		}
	}

	private void atualizarCadastro() {
		boolean confere = true;
		Cadastro cad = new Cadastro();

		while (confere) {
			System.out.println("Indique o número de cadastro para atualizar: ");
			int codigo = verificaCodigo();
			try {
				cadastro.get(codigo);
				cad.setNome(textInput("Nome: "));
				System.out.println("Idade: ");
				cad.setIdade(verificaIdade());
				cadastro.set(codigo, cad);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Código inválido, posição não confere");
				confere = true;
			}
		}
	}

	private String textInput(String label) { // método para inserções de textos
		// e retornos de teclado
		System.out.println(label);
		return entrada.nextLine();
	}

	public int verificaIdade() { // método para evitar inserções de idades
		// indesejadas
		int idade2 = 0;
		boolean confere = true;
		while (confere) {
			String idd = entrada.nextLine();
			try {
				int inteiro = Integer.parseInt(idd); // verificar se é inteiro
				idade2 = inteiro;
				if (idade2 < 0) { // verificar se é positivo
					System.out.println("A idade precisa ser maior que zero");
					confere = true;
				} else if (idade2 > 100) { // verificar se não é Matusalém
					System.out.println("A idade precisa ser menor 100");
					confere = true;
				} else {
					confere = false;
				}
			} catch (Exception e) {
				System.out.printf("Você não digitou um número inteiro!\n");
			}
		}
		return idade2;
	}

	public int verificaCodigo() { // método para evitar inserções de códigos
									// indesejadas
		int cod = 0;
		boolean confere = true;
		while (confere) {
			String codigo = entrada.nextLine();
			try {
				int inteiro = Integer.parseInt(codigo); // verificar se é
														// inteiro
				cod = inteiro;
				if (cod < 0) { // verificar se é positivo
					System.out.println("O código precisa ser maior que zero");
					confere = true;
				} // else if (cod >= cadastro.size()){ //isola as possibilidades
					// dentro do array
					// System.out.println("Posição não existe");
					// confere = true;
					// }
				else {
					confere = false;
				}
			} catch (Exception e) {
				System.out.printf("Você não digitou um número inteiro!\n");
			}
		}
		return cod;
	}
}