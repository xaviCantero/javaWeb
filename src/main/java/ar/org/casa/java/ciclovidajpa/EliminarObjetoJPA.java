package ar.org.casa.java.ciclovidajpa;

import ar.org.casa.java.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EliminarObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {

        //inicia la fabrica
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();

        //inicia transaccion
        //Iniciar una transaccion 1
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //ejecuta SQL de tipo SELECT
        Persona persona1 = em.find(Persona.class, 4);
        
        //termina la transaccion 1
        tx.commit();
        
        //objeto en estado detached
        log.debug("objeto encontrado: "+persona1);
        
        //inicia transaccion 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //ejecuta un SQL que es DELETE
        em.remove(em.merge(persona1));
        
        //termina transaccion 2
        tx2.commit();
        
        //objeto en estado detached ya eliminado
        log.debug("objeto eliminado: "+persona1);
        
        //cerramos el entity manager
        em.close();

    }

}
