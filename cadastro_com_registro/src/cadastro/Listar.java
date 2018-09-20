package cadastro;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import registro.ListarRegistro;

public class Listar {
	ListarRegistro listarRegistro = new ListarRegistro();
	private Scanner entrada;

	public void listarCadastros() {

		System.out.println("Escolha uma opcao: ");
		System.out.println("1- Listar cadastros de pessoas");
		System.out.println("2- Listar registros de visitas");
		entrada = new Scanner(System.in);
		String opLista = entrada.next();
		if (opLista.equals("1")) {
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("databasePU");
				EntityManager em = emf.createEntityManager();
				List<CadastroPessoa> pessoas = em.createQuery("from CadastroPessoa", CadastroPessoa.class).getResultList();
				
				for (CadastroPessoa pessoa : pessoas){
				System.out.println(pessoa.toString());
				System.out.println();
				}
		}else if (opLista.equals("2")){
			listarRegistro.listarRegistros();
		
		}
	}
}
