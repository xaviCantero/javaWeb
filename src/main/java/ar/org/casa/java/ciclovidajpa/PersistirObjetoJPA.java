package ar.org.casa.java.ciclovidajpa;

import ar.org.casa.java.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.*;

public class PersistirObjetoJPA {
    
        static Logger log = LogManager.getRootLogger();
    
        public static void main(String[] args){
            
            //inicia la fabrica
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
            EntityManager em = emf.createEntityManager();

        
            //inicia transaccion
            //Iniciar una transaccion
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            //ejecuta SQL de tipo select
            Persona persona1 = em.find(Persona.class, 1);
            
            //termina la transaccion
            tx.commit();
            
            //objeto en estado de detached
            log.debug("Objeto recuperado: "+persona1);
            
            //cerramos el entity manager
            em.close();
            
        }
}
