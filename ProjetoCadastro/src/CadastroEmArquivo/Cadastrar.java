package CadastroEmArquivo;

import java.util.ArrayList;
import java.util.Scanner;

public class Cadastrar {

	CadastroEmArquivo cad = new CadastroEmArquivo();
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	ValidaNome nome = new ValidaNome();
	Util util = new Util();
	Scanner entrada = new Scanner(System.in);

	ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();

	public void cadastrar(String path) {
		boolean cadastrando = true;
		while (cadastrando) {

			System.out.println("Cadastro de Usuario");
			int pos = util.contarLinhas(path);
			cad.setPosicao(pos);			
			
			String label = "Digite o nome";
			cad.setNome(nome.nome(textInput("Digite o nome"), label));
			cad.setDataNascimento(data.data());
			cad.setCpf(cpf.validarCPF());
			cad.setCelular(celular.formatarCelular());			

			String cadastrar = textInput("Adicionar cadastro (S/N)?");
			boolean confere = true;
			while (confere){
				if (cadastrar.trim().equalsIgnoreCase("s")) {
					System.out.println("Cadastro adicionado!");
					cadastroEmArquivo.add(cad);
					String linha = cad.toString();
					System.out.println(cad.toString());
					util.escreverNoArquivo(path, linha);
					cadastrando = false;
					confere = false;
				} else if (cadastrar.trim().equalsIgnoreCase("n")) {
					System.out.println("Cadastro ignorado!");
					confere = false;
					cadastrando = true;
				} else {
					System.out.println("Opcao invalida");
					cadastrar = textInput("Digite uma opcao valida. (S/N)");
					confere = true;				
				}
			}
			String continua = textInput("Continuar cadastrando (S/N)?");						
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;				
			} else if (continua.equalsIgnoreCase("s")) {
				cadastrando = true;				
			} else {				
				System.out.println("Opcao invalida!");
				cadastrando = false;
			}
		}
	}
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}


}
