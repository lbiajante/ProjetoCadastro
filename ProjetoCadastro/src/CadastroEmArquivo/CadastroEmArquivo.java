
package CadastroEmArquivo;
import java.util.Date;
import java.util.Scanner;

public class CadastroEmArquivo {

	private String nomeDoArquivo;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String celular;	
	private int posicao;

	public CadastroEmArquivo() {
	}

	public CadastroEmArquivo(String nome, String dataNascimento, String cpf,
			String celular, int posicao) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.celular = celular;
		this.posicao = posicao;		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		CadastroEmArquivo cadastroEmArquivo = this;
		return "Posição: " + cadastroEmArquivo.getPosicao()+ " - Nome: " + cadastroEmArquivo.getNome()+ 
				" - Data de nascimento: " + cadastroEmArquivo.getDataNascimento()+ " - CPF: " + cadastroEmArquivo.getCpf()+ 
				" - Celular: " + cadastroEmArquivo.getCelular()+ ".";
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String string) {
		this.dataNascimento = string;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		if(celular == null){
			celular = "Não tem celular cadastrado";
		}		
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getPosicao() {
		String posicao = String.format ("%03d", this.posicao);
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;	
	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}

}
