package registro;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ListarRegistro {

	public void listarRegistros() {					
				
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("databasePU");
				EntityManager em = emf.createEntityManager();
				List<RegistroVisita> registros = em.createQuery("from RegistroVisita", RegistroVisita.class).getResultList();
				
				for (RegistroVisita registro : registros){
				System.out.println(registro.toString());
				System.out.println();
				
			}			
		}
	}

