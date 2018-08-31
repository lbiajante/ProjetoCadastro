package CadastroEmArquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Remover {

	private Util util = new Util();
	private Listar listar = new Listar();
	Scanner entrada = new Scanner(System.in);

	public void removerCadastro(String path) {
		File arq = new File(path);
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			int j = 0;

			if (linha == null) {
				System.out
				.println("Arquivo de cadastro vazio, nao contem itens a serem apagados");
			} else {
				listar.listarCadastros(path);

				boolean confere = true;
				while (confere) {
					System.out
					.println("Indique o numero de cadastro para remover: ");
					String cod = entrada.nextLine();
					int cod2 = util.verificaCodigo(cod);

					if (cod2 == 0) {
						confere = true;
					} else {
						int verificaSeExiste = 0;
						posicao = (String.format("%03d", cod2)).trim();

						while (linha != null) {
							if (linha.substring(0, 12).contains(posicao) == false) {
								j++;
								String posicaoAntiga = linha.substring(0, 12);
								String posicaoNova = "Posicao: "
										+ String.format("%03d", j).trim();
								String newLine = linha.replace(posicaoAntiga,
										posicaoNova);
								salvar.add(newLine);
							} else {
								verificaSeExiste = 1;
							}
							linha = br.readLine();
						}
						confere = false;
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

						if (verificaSeExiste == 1) {
							listar.listarCadastros(path);
							System.out
							.printf("\nCadastro da "
									+ posicao.toLowerCase()
									+ " removido com sucesso. \nLista renumerada.");
						} else {
							System.out
							.printf("\nNumero de cadastro nao existe.\nCadastro nao removido.");
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao tentar remover.");
		}

	}
}
