package CadastroEmArquivo;

import java.util.Scanner;

public class ValidaNome {
	Scanner entrada = new Scanner (System.in);
	boolean confere = true;
//	String verificaNome;

	public String nome (String verificaNome, String label){
		
		String nome = verificaNome.trim();
		while (confere){
			
			if (nome.isEmpty()|| nome.trim().equals("")|| nome.trim().equals(null)) {
				System.out.println("O campo precisa ser preenchido");
				verificaNome = textInput(label);
				confere = true;
				
			} else {
				confere = false;
			}
			nome = verificaNome;
		}
		return nome;

	}
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
	

}
