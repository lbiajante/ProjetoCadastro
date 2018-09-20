package cadastro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerCadastro {

	public EntityManager iniciarBD() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("databasePU");
		EntityManager em = emf.createEntityManager();
		return em;
	}
	public void cadastrarBD(CadastroPessoa cad){
		EntityManager em = this.iniciarBD();
		em.getTransaction().begin();
		em.persist(cad);
		em.getTransaction().commit();
		em.close();
		
	}
	
//	public void listarBD(CadastroPessoa cad){
//		EntityManager em = this.iniciarBD();
//		em.getTransaction().begin();
//		em.persist(cad);
//		em.getTransaction().commit();
//		em.close();

}
