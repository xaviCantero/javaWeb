package ar.org.casa.java.ciclovidajpa;

import ar.org.casa.java.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActualizarObjetoSesionLarga {
    
    
        static Logger log = LogManager.getRootLogger();
    
        public static void main(String[] args){
            
            //inicia la fabrica
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
            EntityManager em = emf.createEntityManager();

        
            //inicia transaccion
            //Iniciar una transaccion 1
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            //ejecutamos SQL de tipo SELECT
            Persona persona1 = em.find(Persona.class, 1);
            
            log.debug("Objeto encontrado: "+persona1);
            
            //setValue (nuevoValor)
            persona1.setEmail("jjuarez@mail.com");
            
            persona1.setEmail("j.juarez@mail.com");
            
            //termina la transaccion
            tx.commit();
            
            //objeto en estado detached
            log.debug("objeto modificado: "+persona1);
                        
            //cerramos el entity manager
            em.close();
            
        }    
    
}
