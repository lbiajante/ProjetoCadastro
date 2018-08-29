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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.text.MaskFormatter;

public class CadastroEmAqruivoMain {
	private Scanner entrada;
	private boolean execute;
	private ArrayList<CadastroEmArquivo> cadastroEmArquivo;
	private String caminhoDoArquivo;
	CadastroEmArquivo cad = new CadastroEmArquivo();

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

		// --------------- execução do menu
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
				System.out.println("Programa de cadastro finalizado!");
				System.out.println("");
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

			cad.setPosicao(this.contarLinhas());
			cad.setNome(textInput("Digite o nome: "));
			cad.setDataNascimento(this.data());
			textInput("");
			cad.setCpf(this.validarCPF());
			textInput("");
			cad.setCelular(this.formatarCelular());
			textInput("");

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

			String continua = textInput("Continuar cadastrando (S/N)?");
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")) {
				cadastrando = true;
			} else {
				System.out.println("\nOpção inválida !!! \n");
				cadastrando = false;
			}
		}
	}

	// ---------------------------------Listar---------------------------------//
	private void listarCadastros() {
		this.contarLinhas();
		this.importar(cadastroEmArquivo);
	}

	// ------------------Remover Cadastro--------------------------//
	private void removerCadastro() {
		File arq = new File(this.caminhoDoArquivo);
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			int j = 0;
			System.out.println("Indique o número de cadastro para remover: ");
			posicao = ("Posição: " + String.format("%03d",
					this.verificaCodigo())).trim();

			while (linha != null) {
				if (linha.contains(posicao) == false) {
					j++;
					String posicaoAntiga = linha.substring(0, 12);
					String posicaoNova = "Posição: "
							+ String.format("%03d", j).trim();
					String newLine = linha.replace(posicaoAntiga, posicaoNova);
					salvar.add(newLine);
				}
				linha = br.readLine();
			}

			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter(arq, true);
			fw2.close();

			FileWriter fw = new FileWriter(arq);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++) {
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

			this.importar(cadastroEmArquivo);
			System.out.println("Cadastro da " + posicao.toLowerCase()
					+ " removido com sucesso. Lista renumerada.");

		} catch (IOException e) {
			System.out.println("Erro ao tentar remover.");
		}

	}

	// ----------------------Atualizar Cadastro--------------------//
	private void atualizarCadastro() {
		File arq = new File(this.caminhoDoArquivo);
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			System.out.println("Indique o número de cadastro para atualizar: ");
			int cod2 = this.verificaCodigo();
			posicao = ("Posição: " + String.format("%03d", cod2));

			while (linha != null) {
				if (linha.contains(posicao) == false) {
					salvar.add(linha);
				} else if (linha.contains(posicao) == true) {
					cad.setPosicao(cod2);
					cad.setNome(textInput("Digite o nome atualizado: "));
					cad.setDataNascimento(this.data());
					textInput("");
					cad.setCpf(this.validarCPF());
					textInput("");
					cad.setCelular(this.formatarCelular());
					textInput("");
					cadastroEmArquivo.add(cad);
					salvar.add(cad.toString());
					System.out.println("Atualização realizada");
				} else {
				}
				linha = br.readLine();
			}
			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter(arq, true);
			fw2.close();

			FileWriter fw = new FileWriter(arq);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++) {
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

		} catch (IOException e) {
			System.out.println("Erro ao tentar atualizar.");
		}		
	}
	// ----sysout + scanner
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
	// --------------verifica se codigo é inteiro válido
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
	// -----abre o arquivo
	public void importar(ArrayList<CadastroEmArquivo> cadastroimport) {

		this.ordenar();
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
			lerCadastro.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.",
					e.getMessage());
		}
	}

	public void ordenar() {

		File arq = new File(this.caminhoDoArquivo);
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			int j = 0;
			while (linha != null) {
				j++;
				String posicaoAntiga = linha.substring(0, 12);
				String posicaoNova = "Posição: "
						+ String.format("%03d", j).trim();
				String newLine = linha.replace(posicaoAntiga, posicaoNova);
				salvar.add(newLine);
				linha = br.readLine();
			}
			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter(arq, true);
			fw2.close();

			FileWriter fw = new FileWriter(arq);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++) {
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

		} catch (IOException e) {
			System.out.println("Erro ao tentar remover.");
		}
	}

	// ----Pega a data em formato string de números, verifica se é data válida e
	// reconverte para string com formato específico
	private String data() {
		boolean confere = true;
		Date dtns = null;

		while (confere) {
			try {
				SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
				System.out
						.println("Digite a data de nascimento com o formato: ddmmaaaa");
				dataFormatIn.setLenient(false);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataNasc;
	}

	// --------------Escrever
	public void escreverNoArquivo() {

		try {
			FileWriter fw = new FileWriter(this.caminhoDoArquivo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(this.cad.toString());
			pw.flush();
			pw.close();

		} catch (IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
	}

	// -------------Contador de Posições
	public int contarLinhas() {
		int numeroLinhas = 0;
		try {
			File arquivoCad = new File(this.caminhoDoArquivo);

			long tamanhoCad = arquivoCad.length();
			FileInputStream fs = new FileInputStream(arquivoCad);
			DataInputStream in = new DataInputStream(fs);

			LineNumberReader lineRead = new LineNumberReader(
					new InputStreamReader(in));
			lineRead.skip(tamanhoCad);
			numeroLinhas = lineRead.getLineNumber() + 1;
			System.out.println("O ARQUIVO CONTEM " + numeroLinhas
					+ " LINHAS!!!!!!!");
		} catch (IOException e) {
		}
		return numeroLinhas;
	}

	public void gerarArquivo() {
		CadastroEmArquivo cad = new CadastroEmArquivo();
		cad.setNomeDoArquivo(textInput("Digite o nome do arquivo a ser criado ou lido"));

		String path = cad.getNomeDoArquivo()+".txt";
	//	String path = "C:\\Users\\"
		//		+ System.getProperty("user.name").toString() + "\\Desktop\\"
			//	+ cad.getNomeDoArquivo() + ".txt";
		cad.setNomeDoArquivo(path);
		this.caminhoDoArquivo = path;
		try {
			FileWriter criadorDeArquivo = new FileWriter(path, true);
			criadorDeArquivo.flush();
			criadorDeArquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na criação do arquivo");
		}
	}

	// -------------------ValidarCpf
	private String validarCPF() {
		String CPF = null;
		CadastroEmArquivo cad = new CadastroEmArquivo();
		boolean confere = true;

		while (confere) {
			try {
				System.out.println("Digite o CPF (somente números)");
				CPF = entrada.next();
				ValidaCPF.isCPF(CPF);
				cad.setCpf(ValidaCPF.imprimeCPF(CPF));
				CPF = ValidaCPF.imprimeCPF(CPF);
				confere = false;
			} catch (Exception e) {
				System.out.printf("Erro, CPF invalido !!!\n");
				confere = true;
			}
		}
		return CPF;
	}

	public static String formatString(String campo, String mascara) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter(mascara);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(campo);
		} catch (ParseException ex) {
			return campo;
		}
	}

	// ------------------ValidarCelular
	private String formatarCelular() {
		String celularFormatado = null;
		String opcaoCelular = textInput("Deseja cadastrar um celular? (S/N)?");
		boolean confere = true;
		if (opcaoCelular.equalsIgnoreCase("s")) {
			while (confere) {
				try {
					System.out.println("Digite o número de Celular: ");
					String cf = entrada.next();
					if (cf.matches("[1-9][1-9][2-9][0-9]{8}")) {
						cf = formatString(cf, "(##) #####-####");
						celularFormatado = cf;
						System.out.println(celularFormatado);
						confere = false;
					} else {
						System.out.println("Número em formato inválido");
						confere = true;
					}
				} catch (Exception e) {
					System.out.println("Número em formato inválido");
					confere = true;
				}
			}
		} else if (opcaoCelular.equalsIgnoreCase("n")) {
			celularFormatado = (null);
			System.out.println("Sem telefone. Aperte Enter");
		} else {
			System.out.println("Opção inválida! Celular não cadastrado!");
		}
		return celularFormatado;
	}
}