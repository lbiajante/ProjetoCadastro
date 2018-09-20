package cadastro;

import java.util.Scanner;

public class ValidaStrings {
	Scanner entrada = new Scanner(System.in);
	

	// String verificaNome;

	public String texto(String verificaTexto, String label) {
		boolean confere = true;

		String campo = verificaTexto.trim();
		while (confere) {
			if (campo.trim().isEmpty() || campo.trim().equals("")
					|| campo.trim().equals(null)) {
				System.out.println("O campo precisa ser preenchido");
				verificaTexto = textInput(label);
				confere = true;
			} else {
				confere = false;
			}
			campo = verificaTexto;
		}
		return campo;
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
