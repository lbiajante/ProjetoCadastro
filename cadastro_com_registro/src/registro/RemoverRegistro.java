package registro;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RemoverRegistro {

	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void removerRegistro() {

		boolean confere2 = true;
		boolean confere = true;
		String confereCod = null;
		String codigo = null;

		do {
			try {
				while (confere) {
					codigo = textInput("Digite um ID para ser removido ou 's' para sair");
					if (codigo.trim().equalsIgnoreCase("s")) {
						confere = false;
						confere2 = false;
					} else {
						int cod = Integer.parseInt(codigo.trim());
						if (cod <= 0) {
							System.out
							.println("O codigo precisa ser maior que zero");
							confere = true;
							confere2 = true;
						} else {
							confereCod = String.format("%06d", cod).trim();

							EntityManagerFactory emf = Persistence.createEntityManagerFactory("databasePU");
							EntityManager em = emf.createEntityManager();									
							RegistroVisita registro = em.find(RegistroVisita.class, confereCod);

							if (registro != null){
								em.getTransaction().begin();
								em.remove(registro);
								em.getTransaction().commit();
								confere = false;
								confere2 = false;
								System.out.println("Cadastro removido com sucesso");
							} else {
								System.out.println("Nao existe passoa cadastrada com esse ID");
							}

						}	
					}
				}
			}
			catch (NumberFormatException e) {
				System.out.printf("Voce nao digitou um numero inteiro!\n");
				codigo = textInput("Digite um numero inteiro");
				confere2 = true;
			}
		} while (confere2);
	}
}

