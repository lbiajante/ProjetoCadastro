package CadastroEmArquivo;

import java.util.Scanner;

public class ValidaCelular {

	private Scanner entrada = new Scanner(System.in);
	private String celularFormatado = null;
	private ValidaCPF cpf = new ValidaCPF();
	boolean confere = true;

	public String formatarCelular() {
		String opcaoCelular = textInput("Deseja cadastrar um celular? (S/N)?");
		if (opcaoCelular.equalsIgnoreCase("s")) {
			while (confere) {
				try {
					System.out.println("Digite o celular somente numeros com DDD: ");
					String cf = entrada.next();
					if (cf.matches("[1-9][1-9][2-9][0-9]{8}")) {
						cf = cpf.formatString(cf, "(##) #####-####");
						celularFormatado = cf;
						confere = false;
					} else {
						System.out.println("Numero em formato invalido");
						confere = true;
					}
				} catch (Exception e) {
					System.out.println("Numero em formato invalido");
					confere = true;
				}
			}
		} else if (opcaoCelular.equalsIgnoreCase("n")) {
			celularFormatado = ("Celular nao cadastrado");
			System.out.println("Sem telefone");
		} else {
			System.out.println("Opcao invalida! Celular nao cadastrado!");
		}
		return celularFormatado;
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
