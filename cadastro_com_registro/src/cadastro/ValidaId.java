package cadastro;

import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ValidaId {

	ArrayList<CadastroPessoa> lista = new ArrayList<CadastroPessoa>();
	CadastroPessoa cad = new CadastroPessoa();
	Scanner entrada = new Scanner(System.in);

	public int verificaID(String codigo) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("databasePU");
		EntityManager em = emf.createEntityManager();
		int cod = 0;
		boolean confere = true;
		String confereCod = null;

		do {
			try {
				while (confere) {
					cod = Integer.parseInt(codigo.trim());
					if (cod <= 0) {
						System.out
								.println("O codigo precisa ser maior que zero");
						confere = true;
						cod = 0;
					} else {
						confereCod = String.format("%06d", cod).trim();						
						CadastroPessoa pessoa = em.find(CadastroPessoa.class,
								confereCod);
						if (pessoa != null) {
							System.out
									.println("Esse ID esta sendo usado, por favor digite outro");
							codigo = entrada.next();
							confere = true;
							cod = 0;
							break;

						} else {
							confere = false;
						}
					}
				}
			} catch (NumberFormatException e) {
				System.out.printf("Voce nao digitou um numero inteiro!\n");
				codigo = textInput("Digite um numero inteiro");
				cod = 0;
			}
		} while (cod == 0);
		return cod;
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
