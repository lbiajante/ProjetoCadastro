package CadastroEmArquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ordenacao {	

	 CadastroEmArquivo cad = new CadastroEmArquivo();	

	public void ordenar(String path) {
		File arq = new File(path);
		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			int j = 0;
			while (linha != null) {
				j++;
				String posicaoAntiga = linha.substring(0, 12);
				String posicaoNova = "Posicao: "
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
			System.out.println("Erro ao tentar ordenar.");
		}
	}

}
