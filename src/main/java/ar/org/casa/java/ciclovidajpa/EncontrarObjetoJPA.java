package ar.org.casa.java.ciclovidajpa;

import ar.org.casa.java.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EncontrarObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {

        //iniciamos la fabrica
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //crea un objeto. Objeto en estado transitivo
        Persona persona1 = new Persona("Pedro", "Luna", "pluna@mail.com", "13135566");

        //inicia transaccion
        tx.begin();

        //Ejecuta SQL
        em.persist(persona1);

        log.debug("Objeto persistido - aun sin commit: " + persona1);

        //commit/rollback
        tx.commit();

        //Objeto en estado detache
        log.debug("Objeto persistido - estado detached: " + persona1);

        //cerramos el entity manager
        em.close();

    }
}
