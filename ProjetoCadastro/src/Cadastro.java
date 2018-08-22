import java.util.Scanner;

public class Cadastro {
	
	private String nome;
	//private String idade;
	private int idade;
	private int posicao;
	
	public Cadastro(String nome, int idade, int posicao) {
		this.nome = nome;
		this.idade = idade;
		this.posicao = posicao;		
	}

	public Cadastro() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public String getIdade() {
	public int getIdade(){
		return idade;
	}

	//public void setIdade(String string) {
	public void setIdade(int integer) {
		this.idade = integer;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
}