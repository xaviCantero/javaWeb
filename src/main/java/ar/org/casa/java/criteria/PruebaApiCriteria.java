package ar.org.casa.java.criteria;

import ar.org.casa.java.domain.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.*;

public class PruebaApiCriteria {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        //Query utilizando el API de criteria
        CriteriaBuilder cb = null;
        CriteriaQuery<Persona> criteriaQuery = null;
        Root<Persona> fromPersona = null;
        TypedQuery<Persona> query = null;
        Persona persona = null;
        List<Persona> personas = null;
        
        //Consulta de todas las personas
        //paso 1. El objeto EntityManager crea instacia CriteriaBuilder
        cb = em.getCriteriaBuilder();
        
        //paso2. Se crea un objeto CriteriaQuery
        criteriaQuery = cb.createQuery(Persona.class);
        
        //paso3. Creamos el objeto raiz de Query
        fromPersona = criteriaQuery.from(Persona.class);
        
        //paso4. Seleccionamos lo necesario del from
        criteriaQuery.select(fromPersona);
        
        //paso5. Creamos el query typeSafe
        query = em.createQuery(criteriaQuery);
        
        //paso6. Ejecutamos la consulta
        personas = query.getResultList();
        
        mostrarPersonas(personas);
    
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for(Persona p: personas){
            log.debug(p);
        }
    }
    
}
