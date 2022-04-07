package ar.org.casa.java.cascade;

import ar.org.casa.java.domain.Persona;
import ar.org.casa.java.domain.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.*;

public class PersistenciaCascadaJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        //Crea un nuevo objeto
        //Objeto en estado transitivo
        Persona persona1 = new Persona("Hugo", "Hernandez", "hhernandez@gmail.com", "55778822");
        
        //Crea un objeto usuario (tiene dependencia con el objeto persona)
        Usuario usuario1 = new Usuario("hhernandez", "123", persona1);
        
        //Persistimos el objeto usuario unicamente
        em.persist(usuario1);
        
        //commit transaccion
        tx.commit();
        
        //objetos en estado detached
        log.debug("objeto persistido persona: "+persona1);
        log.debug("objeto persistido usuario: "+usuario1);
        
        em.close();
        
    }
    
}
