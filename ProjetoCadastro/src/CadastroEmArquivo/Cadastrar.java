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
	boolean cadastrando = true;	
	ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();

	public void cadastrar(String path) {

		while (cadastrando) {
			System.out.println("Cadastro de Usuario");
			util.contarLinhas(path);
			cad.setNome(nome.nome());
			cad.setDataNascimento(data.data());
			cad.setCpf(cpf.validarCPF());
			cad.setCelular(celular.formatarCelular());
			
			String cadastrar = textInput("Adicionar cadastro (S/N)?");
			if (cadastrar.equalsIgnoreCase("s")) {
				System.out.println("Cadastro adicionado!");
				cadastroEmArquivo.add(cad);
				String linha = cad.toString();
				System.out.println(cad.toString());
				util.escreverNoArquivo(path, linha);
				cadastrando = false;
			} else if (cadastrar.equalsIgnoreCase("n")) {
				System.out.println("Cadastro ignorado!");
			} else {
				System.out.println("Opcao invalida, cadastro ignorado!");
			}
			String continua = textInput("Continuar cadastrando (S/N)?");
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;
				break;
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
