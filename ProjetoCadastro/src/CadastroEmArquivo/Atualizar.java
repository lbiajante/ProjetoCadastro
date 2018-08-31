package CadastroEmArquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Atualizar {
	Util util = new Util();
	Scanner entrada = new Scanner(System.in);
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	ValidaNome nome = new ValidaNome();
	CadastroEmArquivo cad = new CadastroEmArquivo();
	ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void atualizarCadastro(String path) {
		File arq = new File(path);
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();

			if (linha == null) {
				System.out
				.println("Arquivo de cadastro vazio, nao contem itens a serem apagados");
			} else {
				boolean confere = true;
				while (confere) {
					System.out
					.println("Indique o numero de cadastro para atualizar: ");
					String cod = entrada.nextLine();
					int cod2 = util.verificaCodigo(cod);

					if (cod2 == 0) {
						confere = true;
					} else {
						confere = false;
						posicao = (String.format("%03d", cod2)).trim();
						System.out.println();
						int verificaSeExiste = 0;
						
						while (linha != null) {							
							if (linha.substring(8, 12).contains(posicao) == true) {
								verificaSeExiste = 1;
								cad.setPosicao(cod2);
								cad.setNome(nome
										.nome(textInput("Digite o nome atualizado")));
								cad.setDataNascimento(data.data());
								cad.setCpf(cpf.validarCPF());
								cad.setCelular(celular.formatarCelular());
								cadastroEmArquivo.add(cad);
								salvar.add(cad.toString());
								System.out.println("Atualizacao realizada");
							} else if (linha.substring(0, 12).contains(posicao) == false) {
								salvar.add(linha);
							}
							linha = br.readLine();
						}
						if (verificaSeExiste == 0) {
							System.out
							.println("Numero de cadastro nao encontrado");
						}
						br.close();
						fr.close();
						FileWriter fw = new FileWriter(arq);
						BufferedWriter bw = new BufferedWriter(fw);

						for (int i = 0; i < salvar.size(); i++) {
							bw.write(salvar.get(i));
							bw.newLine();
						}
						bw.flush();
						fw.flush();
						bw.close();
						fw.close();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao tentar atualizar.");
		}

	}
}
