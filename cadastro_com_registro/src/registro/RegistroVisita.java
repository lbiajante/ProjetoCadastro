package registro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "Registro_de_visitas")
public class RegistroVisita implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "ID")
	private String posicao;
	@Column (name = "lugar")
	private String local;
	@Column (name = "data_visita")
	private String data;
	@Column (name = "hora_visita")
	private String hora;
	@Column (name = "ID_pessoa")
	private String IDpessoa;
	@Column (name = "visitante")
	private String nomePessoa;

	public String getIDpessoa() {
		return IDpessoa;
	}

	public void setIDpessoa(String iDpessoa) {
		IDpessoa = iDpessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public RegistroVisita() {
		super();
	}

	public RegistroVisita(String posicao, String local, String data,
			String hora, String iDpessoa, String nomePessoa) {
		super();
		this.posicao = posicao;
		this.local = local;
		this.data = data;
		this.hora = hora;
		IDpessoa = iDpessoa;
		this.nomePessoa = nomePessoa;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		RegistroVisita registroEmArquivo = this;
		return "Local Visitado: ID - " + registroEmArquivo.getPosicao()
				+ ", Local - " + registroEmArquivo.getLocal() + ", Data - "
				+ registroEmArquivo.getData() + ", Hora - "
				+ registroEmArquivo.getHora() + "\n Visitado por:  ID - "
				+ registroEmArquivo.getIDpessoa() + ", Nome - "
				+ registroEmArquivo.getNomePessoa() + ".\n";
	}
}
