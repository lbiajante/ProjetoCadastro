package registro;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class CadastrarRegistro {

	RegistroVisita reg = new RegistroVisita();
	ValidaDataRegistro data = new ValidaDataRegistro();	
	ValidaStringsRegistro string = new ValidaStringsRegistro();
	ValidaIdRegistro validaId = new ValidaIdRegistro();
	Scanner entrada = new Scanner(System.in);

	public void cadastrar(String IdPessoa, String nomePessoa) {
		
		

	//	ArrayList<RegistroVisita> registroEmArquivo = new ArrayList<RegistroVisita>();

		System.out.println("Cadastro de Registro");
//		util.gerarArquivo(path);
		IdRegistro id = new IdRegistro(validaId.verificaID(textInput("Digite o ID do local visitado")));
		reg.setPosicao(id.getId());
		String label = "Digite o local";
		reg.setLocal(string.texto(textInput(label), label));
		reg.setData(data.data());
		reg.setHora(data.hora());
		reg.setIDpessoa(IdPessoa);
		reg.setNomePessoa(nomePessoa);
		String cadastrar = textInput("Adicionar Registro (S/N)?");
		boolean confere = true;
		while (confere) {
			if (cadastrar.trim().equalsIgnoreCase("s")) {
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("databasePU");
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				em.persist(reg);
				em.getTransaction().commit();
				
				System.out.println("Registro adicionado!");
//		
				confere = false;
			} else if (cadastrar.trim().equalsIgnoreCase("n")) {
				System.out.println("Registro ignorado!");
				confere = false;
			} else {
				System.out.println("Opcao invalida");
				cadastrar = textInput("Digite uma opcao valida. (S/N)");
				confere = true;
			}
		}
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
