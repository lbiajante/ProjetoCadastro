package CadastroEmArquivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Listar {

	 CadastroEmArquivo cad = new CadastroEmArquivo();
	 Util util = new Util();
	 Ordenacao ordenar = new Ordenacao();
	 ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();
	
	public void listarCadastros(String path) {
		util.contarLinhas(path);
		ordenar.ordenar(path);
		try {
			FileInputStream arq = new FileInputStream(path);
			InputStreamReader input = new InputStreamReader(arq);
			BufferedReader lerCadastro = new BufferedReader(input);
			String linha = lerCadastro.readLine();
			if (linha == null) {
				System.out.println("Cadastro vazio");				
			}
			while (linha != null) {
				System.out.printf("%s\n", linha);
				linha = lerCadastro.readLine();
			}
			arq.close();
			lerCadastro.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo:",
					e.getMessage());
		}
	}
}
