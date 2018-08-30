package CadastroEmArquivo;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		boolean execute = true;
		String path;
		Util util = new Util();
		Cadastrar cadastrar = new Cadastrar();
		Remover remover = new Remover();
		Atualizar atualizar = new Atualizar();
		Listar listar = new Listar();
		Scanner entrada;

		System.out.println("BEM VINDO AO CADASTRO DE USUÁRIOS");
		System.out.println("");
		path = util.gerarArquivo();		

		while (execute) {
			entrada = new Scanner(System.in);
			System.out.println("");
			System.out.println("-------------------------------------------");
			System.out.println("Digite o numero da opcao selecionada:");
			System.out.println("1 - Novo cadastro");
			System.out.println("2 - Listar cadastro");
			System.out.println("3 - Apagar item do cadastro");
			System.out.println("4 - Atualizar cadastro");
			System.out.println("5 - Sair");
			System.out.println("-------------------------------------------");
			String opcao = entrada.next();
			if (opcao.equals("1")) {
				cadastrar.cadastrar(path);
				execute =  true;
			} else if (opcao.equals("2")) {
				listar.listarCadastros(path);
				execute =  true;
			} else if (opcao.equals("3")) {
				remover.removerCadastro(path);
				execute =  true;
			} else if (opcao.equals("4")) {
				atualizar.atualizarCadastro(path);
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
