package CadastroEmArquivo;

import java.util.Scanner;

public class ValidaNome {
	Scanner entrada = new Scanner (System.in);
	boolean confere = true;
	String verificaNome;

	public String nome (){
		while (confere){
			verificaNome = textInput ("Digite o nome");
			if (verificaNome.trim().equals(null)|| verificaNome.trim().equals("")) {
				System.out.println("O campo nome precisa ser preenchido");
				confere = true;
			} else {
				confere = false;
			}
		}
		return verificaNome;

	}
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
