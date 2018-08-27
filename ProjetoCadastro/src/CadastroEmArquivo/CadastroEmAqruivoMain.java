package CadastroEmArquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CadastroEmAqruivoMain {
	private Scanner entrada;
	private boolean execute;
	private ArrayList<CadastroEmArquivo> cadastroEmArquivo;
	private String caminhoDoArquivo;

	public static void main(String[] args) {
		new CadastroEmAqruivoMain();
	}

	// --------------------------- Main
	private CadastroEmAqruivoMain() {

		entrada = new Scanner(System.in);
		execute = true;
		cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();
		System.out.println("BEM VINDO AO CADASTRO DE USUÁRIOS");
		System.out.println("");
		this.gerarArquivo();		

		while (execute) {
			String opcao = menu();
			if (opcao.equals("1")) {
				cadastrar();
			} else if (opcao.equals("2")) {
				this.importar(cadastroEmArquivo);
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
	// -----------------------------Menu-----------------------------//
	private String menu() {
		System.out.println("Selecione a opção:");
		System.out.println("1 - Novo cadastro");
		System.out.println("2 - Listar cadastros");
		System.out.println("3 - Remover cadastro");
		System.out.println("4 - Atualizar cadastro");
		System.out.println("5 - Sair");
		return entrada.nextLine();
	}
	// ----------------------------Cadastro------------------------------------//
	private void cadastrar() {

		boolean cadastrando = true;
		while (cadastrando) {
			System.out.println("Cadastro de Usuário");
			CadastroEmArquivo cad = new CadastroEmArquivo();

			cad.setPosicao(this.contarLinhas());
			cad.setNome(textInput("Digite o nome: "));
			cad.setDataNascimento(this.data());
			textInput("");

			cad.setCpf(textInput("Digite o CPF: "));

			String opcaoCelular = textInput("Deseja cadastrar um celular? (S/N)?");
			if (opcaoCelular.equalsIgnoreCase("s")) {
				cad.setCelular(textInput("Digite o número de Celular: "));
			} else if (opcaoCelular.equalsIgnoreCase("n")) {
				System.out.println("Ok!");
				cad.setCelular(null);
			} else {
				System.out.println("Opção inválida! Celular não cadastrado!");
			}

			String cadastrar = textInput("Adicionar cadastro (S/N)?");
			if (cadastrar.equalsIgnoreCase("s")) {
				System.out.println("Cadastro adicionado!");
				cadastroEmArquivo.add(cad);
				this.escreverNoArquivo();
				cadastrando = false;
			} else if (cadastrar.equalsIgnoreCase("n")) {
				System.out.println("Cadastro ignorado!");
			} else {
				System.out.println("Opção inválida, cadastro ignorado!");
			}		
		}
	}

	//-----Listar
	public void importar(ArrayList<CadastroEmArquivo> cadastroimport) {
		try {
			FileInputStream cad = new FileInputStream(this.caminhoDoArquivo);
			InputStreamReader input = new InputStreamReader(cad);
			BufferedReader lerCadastro = new BufferedReader(input);
			String linha = lerCadastro.readLine();
			if (linha == null) {
				System.out.println("Cadastro vazio");
			}
			while (linha != null) {
				System.out.printf("%s\n", linha);
				linha = lerCadastro.readLine();
			}
			cad.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.",
					e.getMessage());
		}
	}

	// ------------------Remover Cadastro--------------------------//
	private void removerCadastro() {
		boolean confere = true;
		File arq = new File(this.caminhoDoArquivo);
		int cod2;
		int count = 0;
		String posicao;		

		try{
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();

			System.out.println("Indique o número de cadastro para remover: ");
			cod2 = this.verificaCodigo();
			posicao = ("Posição: "+cod2).trim();

			while (linha != null){
				if (linha.contains(posicao)== false){		
					salvar.add(linha);
					System.out.println(linha);					
				}
				linha = br.readLine();
			}
			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter (arq, true);
			fw2.close();

			FileWriter fw = new FileWriter(arq);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++){
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

		} catch (IOException e){
			System.out.println("Erro ao tentar remover.");
		}
	}

	// ----------------------Atualizar Cadastro--------------------//
	private void atualizarCadastro() {
		boolean confere = true;
		File arq = new File(this.caminhoDoArquivo);
		int cod2;
		String posicao;		

		try{
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			CadastroEmArquivo cad2 = new CadastroEmArquivo();
			System.out.println("Indique o número de cadastro para atualizar: ");
			cod2 = this.verificaCodigo();
			posicao = ("Posição: "+cod2).trim();
			System.out.println(cod2 + posicao);

			while (linha != null){
				if (linha.contains(posicao)== false){		
					salvar.add(linha);
				}else if (linha.contains(posicao)== true){
					////////////////
				}
				linha = br.readLine();
			}
			br.close();
			fr.close();

			FileWriter fw2 = new FileWriter (arq, true);
			fw2.close();

			FileWriter fw = new FileWriter(arq);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++){
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

		} catch (IOException e){
			System.out.println("Erro ao tentar remover.");
		}
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public int verificaCodigo() {
		int cod = 0;
		boolean confere = true;
		while (confere) {
			String codigo = entrada.nextLine();
			try {
				cod = Integer.parseInt(codigo);				
				System.out.println(cod);
				if (cod <= 0) {
					System.out.println("O código precisa ser maior que zero");
					confere = true;
				} else {
					confere = false;
				}
			} catch (Exception e) {
				System.out.printf("Você não digitou um número inteiro!\n");
			}
		}
		return cod;
	}

	//----Pega a data em formato string de números, verifica se é data válida e reconverte para string com formato específico 
	private String data() {
		boolean confere = true;
		Date dtns = null;

		while (confere) {
			try {
				SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
				System.out.println("Digite a data de nascimento com o formato: ddmmaaaa");
				Date date = dataFormatIn.parse(entrada.next());
				dtns = date;
				confere = false;
			} catch (Exception e) {
				System.out.println("Data inválida");
			}
		}
		String dataNasc = null;
		try {
			SimpleDateFormat dataFormatOut = new SimpleDateFormat("dd/MM/yyyy");
			String dataToString = dataFormatOut.format(dtns); 
			dataNasc = dataToString;

		} catch (Exception e){
			e.printStackTrace();
		}

		return dataNasc;
	}
	//--------------Escrever
	public void escreverNoArquivo(){

		try{			
			FileWriter fw = new FileWriter(this.caminhoDoArquivo, false);
			PrintWriter pw = new PrintWriter(fw);
			for (CadastroEmArquivo cadArq : cadastroEmArquivo){
				pw.println(cadArq.toString());
			}
			pw.flush();
			pw.close();

		}catch(IOException e){
			System.out.println("Erro ao cadastrar!");
		}
	}
	//-------------Contador de Posições		
	public int contarLinhas() {
		CadastroEmArquivo cad = new CadastroEmArquivo();
		int numeroLinhas = 0;
		try {
			File arquivoCad = new File(this.caminhoDoArquivo);

			long tamanhoCad = arquivoCad.length();
			FileInputStream fs = new FileInputStream(arquivoCad);
			DataInputStream in = new DataInputStream(fs);

			LineNumberReader lineRead = new LineNumberReader(new InputStreamReader(in));
			lineRead.skip(tamanhoCad);				
			numeroLinhas = lineRead.getLineNumber() + 1;
			//	System.out.println("O ARQUIVO CONTEM " + numeroLinhas + " LINHAS!!!!!!!");

		} catch (IOException e) {

		}
		return numeroLinhas;
	}
	public void gerarArquivo(){

		CadastroEmArquivo cad = new CadastroEmArquivo();

		cad.setNomeDoArquivo(textInput("Digite o nome do arquivo a ser criado ou lido"));
		String path = "C:\\Users\\" + System.getProperty("user.name").toString()+ "\\Desktop\\" + cad.getNomeDoArquivo()+".txt" ;
		cad.setNomeDoArquivo(path);
		this.caminhoDoArquivo = path;
		try{
			FileWriter criadorDeArquivo = new FileWriter(path, true);
			criadorDeArquivo.flush();
			criadorDeArquivo.close();
		}
		catch (IOException e){
			System.out.println("Erro na criação do arquivo");
		}
	}

}