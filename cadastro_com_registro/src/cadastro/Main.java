 package cadastro;

import java.util.Scanner;


public class Main {
	public static void main(String[] args) {

		boolean execute = true;
	//	EntityManagerCadastro emc = new EntityManagerCadastro();
		Cadastrar cadastrar = new Cadastrar();
		Remover remover = new Remover();
		Atualizar atualizar = new Atualizar();
		Listar listar = new Listar();
		Scanner entrada;

		System.out.println("BEM VINDO AO CADASTRO DE PESSOAS E REGISTROS DE VISITAS");
		System.out.println("");
		
		//emc.iniciarBD();

		while (execute) {
			entrada = new Scanner(System.in);
			System.out.println("");
			System.out.println("-------------------------------------------");
			System.out.println("Digite o numero da opcao selecionada:");
			System.out.println("1 - Novo cadastro");
			System.out.println("2 - Listar cadastro ou listar registros");
			System.out.println("3 - Apagar item do cadastro ou apagar item do registro");
			System.out.println("4 - Atualizar cadastro ou adicionar registro a um cadastro");
			System.out.println("5 - Sair");
			System.out.println("-------------------------------------------");
			String opcao = entrada.next();
			if (opcao.equals("1")) {
				System.out.println("");
				cadastrar.cadastrar();
				execute =  true;
			} else if (opcao.equals("2")) {
				listar.listarCadastros();
				execute =  true;
			} else if (opcao.equals("3")) {
				remover.removerCadastro();
				execute =  true;
			} else if (opcao.equals("4")) {
				atualizar.atualizarCadastro();
				execute =  true;
			} else if (opcao.equals("5")) {
				System.out.println("Programa de cadastro finalizado!");
				System.out.println("");
				execute = false;
			} else {
				System.out.println("Opcao Invalida!");
			}
		}
	}
}
