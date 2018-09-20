package cadastro;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import registro.CadastrarRegistro;

public class Cadastrar {

	CadastroPessoa cad = new CadastroPessoa();
	CadastrarRegistro cadReg = new CadastrarRegistro();
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	Scanner entrada = new Scanner(System.in);

	public void cadastrar() {
	
		System.out.println("Cadastro de Usuario");
		Id id = new Id(validaId.verificaID(
				textInput("Digite o ID a ser cadastrado")));
		cad.setPosicao(id.getId());
		String label = "Digite o nome";
		cad.setNome(string.texto(textInput(label), label));
		cad.setDataNascimento(data.data());
		cad.setCpf(cpf.validarCPF());
		label = "Digite o nome da empresa";
		cad.setEmpresa(string.texto(textInput(label), label));
		label = "Digite a area de atuacao";
		cad.setAreaDeAtuacao(string.texto(textInput(label), label));
		cad.setCelular(celular.formatarCelular());

		String cadastrar = textInput("Adicionar cadastro (S/N)?");
		boolean confere = true;
		while (confere) {
			if (cadastrar.trim().equalsIgnoreCase("s")) {
				
//				EntityManagerCadastro emcCadastro = new EntityManagerCadastro();
//				emcCadastro.cadastrarBD(cad);
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("databasePU");
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				em.persist(cad);
				em.getTransaction().commit();
				
				System.out.println("Cadastro adicionado!");
				System.out.println(cad.toString());
			
				confere = false;
			} else if (cadastrar.trim().equalsIgnoreCase("n")) {
				System.out.println("Cadastro ignorado!");
				confere = false;
			} else {
				System.out.println("Opcao invalida");
				cadastrar = textInput("Digite uma opcao valida. (S/N)");
				confere = true;
			}
		}
		
		boolean conf = true;

		do {	
			System.out.println("Deseja registrar a visita a algum local? (S/N)");
			String opcaoVisita = null;
			opcaoVisita = entrada.nextLine();
			if (opcaoVisita.trim().equalsIgnoreCase("s")) {
				cadReg.cadastrar(cad.getPosicao(), cad.getNome());								
				boolean repeat = true;
				 
				do {
					System.out.println("Deseja registrar outra visita? (S/N)");
					String opcaoContinua = null;
					opcaoContinua = entrada.nextLine();
					if (opcaoContinua.trim().equalsIgnoreCase("s")) {
						cadReg.cadastrar(cad.getPosicao(), cad.getNome());	
						conf = true;
						repeat = true;
					} else if (opcaoContinua.trim().equalsIgnoreCase("n")) {
						conf = false;
						repeat = false;
					} else {
						System.out.println("Opcao Invalida! Tente novamente!****");
						repeat = true;
					}					
				} while (repeat); 
				
			} else if (opcaoVisita.equalsIgnoreCase("n")) {
				conf = false;
			} else {
				System.out.println("Opcao invalida! Tente novamente!");
				conf = true;
			}
		} while (conf);		
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
