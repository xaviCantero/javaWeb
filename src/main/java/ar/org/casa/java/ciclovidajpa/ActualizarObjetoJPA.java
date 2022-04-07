package ar.org.casa.java.ciclovidajpa;

import ar.org.casa.java.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActualizarObjetoJPA {
    
        static Logger log = LogManager.getRootLogger();
    
        public static void main(String[] args){
            
            //inicia la fabrica
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
            EntityManager em = emf.createEntityManager();

        
            //inicia transaccion
            //Iniciar una transaccion 1
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            //ejecuta SQL de tipo SELECT
            //el id proporcionado debe existir en la base de datos
            Persona persona1 = em.find(Persona.class, 1);
            
            //termina la transaccion
            tx.commit();
            
            //objeto en estado detached
            log.debug("objeto recuperado: "+ persona1);
            
            // SetValue (nuevoValor)
            persona1.setApellido("Juarez");
            
            //inicia transaccion2
            EntityTransaction tx2 = em.getTransaction();
            tx2.begin();
            
            //modificamos el objeto
            em.merge(persona1);
            
            //termina transaccion 2
            tx2.commit();
            
            //objeto en estado detached ya modificado
            log.debug("objeto recuperado: "+persona1);
                        
            //cerramos el entity manager
            em.close();
            
        }
    
}
