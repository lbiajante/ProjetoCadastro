package CadastroEmArquivo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Util {

	CadastroEmArquivo cad = new CadastroEmArquivo();
	Scanner entrada = new Scanner(System.in);
	private String path;

	public int verificaCodigo(String codigo) {
		int cod = 0;
		boolean confere = true;

		try {
			while (confere) {
				cod = Integer.parseInt(codigo.trim());
				if (cod <= 0) {
					System.out.println("O codigo precisa ser maior que zero");
					confere = true;
				} else {
					confere = false;
				}
			}
		} catch (NumberFormatException e) {
			System.out.printf("Voce nao digitou um número inteiro!\n");
			cod = 0;
		}
		return cod;
	}

	public String gerarArquivo() {
		boolean confere = true;
		path = textInput("Digite o nome do arquivo a ser criado ou lido");
		path = path.trim();
		while (confere) {
			if (path.isEmpty() || path.trim().equals("")
					|| path.trim().equals(null)) {
				System.out
						.println("O campo nome do arquivo nao pode ser em branco");
				path = textInput("Digite o nome do arquivo");
				confere = true;
			} else {
				confere = false;
			}
		}
		path = path + ".txt";
		try {
			FileWriter criadorDeArquivo = new FileWriter(path, true);
			criadorDeArquivo.flush();
			criadorDeArquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na criacao do arquivo");
		}
		return path;
	}

	public int contarLinhas(String path) {
		int numeroLinhas = 0;
		try {
			File arq = new File(path);
			long tamanhoCad = arq.length();
			FileInputStream fs = new FileInputStream(arq);
			DataInputStream in = new DataInputStream(fs);
			@SuppressWarnings("resource")
			LineNumberReader lineRead = new LineNumberReader(
					new InputStreamReader(in));
			lineRead.skip(tamanhoCad);
			numeroLinhas = lineRead.getLineNumber() + 1;
			cad.setPosicao(numeroLinhas);
			System.out.println("O arquivo tem " + (numeroLinhas - 1)
					+ " linhas!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numeroLinhas;
	}

	public void escreverNoArquivo(String path, String linha) {
		try {
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(linha);
			pw.flush();
			pw.close();

		} catch (IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
