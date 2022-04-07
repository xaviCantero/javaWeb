package ar.org.casa.java.asociaciones;

import ar.org.casa.java.domain.Persona;
import ar.org.casa.java.domain.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.*;

public class ClienteAsociacionesJPA {
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        List<Persona> personas = em.createNamedQuery("Persona.findAll").getResultList();
        
        //cerramos la conexion
        em.close();
        
        //Imprimimos los objetos de tipo persona
        for(Persona persona: personas){
            log.debug("Persona: "+persona);
            //recuperamos los objetos de tipo usuario
            for(Usuario usuario: persona.getUsuarioList()){
                log.debug("Usuario: "+usuario);
            }
        }
        
    }
    
}
