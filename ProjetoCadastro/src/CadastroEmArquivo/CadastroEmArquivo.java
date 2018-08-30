package CadastroEmArquivo;

public class CadastroEmArquivo {

	private String nomeDoArquivo;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String celular;
	private int posicao;

	public CadastroEmArquivo() {
		super();
	}
	
	public CadastroEmArquivo(String nomeDoArquivo, String nome,
			String dataNascimento, String cpf, String celular, int posicao) {
		super();
		this.nomeDoArquivo = nomeDoArquivo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.celular = celular;
		this.posicao = posicao;
	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}
	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	@Override
	public String toString() {
		CadastroEmArquivo cadastroEmArquivo = this;
		return "Posicao: " + cadastroEmArquivo.getPosicao() + " - Nome: "
				+ cadastroEmArquivo.getNome() + " - Data de nascimento: "
				+ cadastroEmArquivo.getDataNascimento() + " - CPF: "
				+ cadastroEmArquivo.getCpf() + " - Celular: "
				+ cadastroEmArquivo.getCelular() + ".";
	}
}
