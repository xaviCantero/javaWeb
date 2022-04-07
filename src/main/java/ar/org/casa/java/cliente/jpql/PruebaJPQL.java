package ar.org.casa.java.cliente.jpql;

import ar.org.casa.java.domain.Persona;
import ar.org.casa.java.domain.Usuario;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import org.apache.logging.log4j.*;

public class PruebaJPQL {
   static Logger log = LogManager.getRootLogger();
   
   public static void main(String[] args){
       
       String jpql = null;
       Query q = null;
       List<Persona> personas = null;
       Persona persona = null;
       Iterator iter = null;
       Object[] tupla = null;
       List<String> nombres = null;
       List<Usuario> usuarios = null;
       
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
       EntityManager em = emf.createEntityManager();
       //EntityTransaction tx = em.getTransaction();
       
       //Consulta de todos los objetos de tipo Persona
       log.debug("\nConsulta de todas las personas: ");
       jpql = "select p from Persona p";
       personas = em.createQuery(jpql).getResultList();
       //mostrarPersonas( personas );
   
       //Consulta de la Persona con id = 1
       log.debug("\nConsulta de la Persona con el id = 1");
       jpql = "select p from Persona p where p.idPersona = 1";
       persona = (Persona) em.createQuery(jpql).getSingleResult();
       //log.debug(persona);
       
       //Consulta de la Persona con Nombre
       log.debug("\nConsulta de la Persona con nombre = 'Karla'");
       jpql = "select p from Persona p where p.nombre = 'Karla'";
       personas = em.createQuery(jpql).getResultList();
       //mostrarPersonas(personas);
       
       //Consulta de datos individuales, se crea un arreglo(tupla) de tipo object de 3 columnas
       log.debug("\nConsulta de datos individuales, se crea un arreglo(tupla) de tipo object de 3 columnas");
       jpql = "select p.nombre as Nombre, p.apellido as Apellido, p.email as Email from Persona p";
       iter = em.createQuery(jpql).getResultList().iterator();
       while(iter.hasNext()){
           tupla = (Object[]) iter.next();
           String nombre = (String) tupla[0];
           String apellido = (String) tupla[1];
           String email = (String) tupla[2];
           //log.debug("nombre: "+nombre+", apellido: "+apellido+", email: "+email);
       }
       
       //Obtiene el objeto Persona y el Id, se crea un arreglo de tipo Object con 2 columnas
       log.debug("\nObtiene el objeto Persona y el Id, se crea un arreglo de tipo Object con 2 columnas");
       jpql = "select p, p.idPersona from Persona p";
       iter = em.createQuery(jpql).getResultList().iterator();
       while(iter.hasNext()){
           tupla = (Object[]) iter.next();
           persona = (Persona) tupla[0];
           int idPersona = (int) tupla[1];
           //log.debug("Objeto persona: "+persona);
           //log.debug("id persona : "+idPersona);
       }
       
       //consulta de todas las personas
       log.debug("\nconsulta de todas las personas");
       jpql = "select new ar.org.casa.java.domain.Persona(p.idPersona) from Persona p";
       personas = em.createQuery(jpql).getResultList();
       //mostrarPersonas(personas);
       
       //Regresa el valor minima y maximo de idPersona. Tambien el total de idPersona
       log.debug("\nRegresa el valor minima y maximo de idPersona. Tambien el total de idPersona");
       jpql = "select min(p.idPersona) as MinId, MAX(p.idPersona) as MaxId, count(p.idPersona) as Contador from Persona p";
       iter = em.createQuery(jpql).getResultList().iterator();
       while(iter.hasNext()){
          tupla = (Object[]) iter.next();
          Integer idMin = (Integer) tupla[0]; 
          Integer idMax = (Integer) tupla[1];
          Long count = (Long) tupla[2];
          //log.debug("idMin: "+idMin+", idMax: "+idMax+", count: "+count);
       }
       
       //Cuenta los nombres de las personas que son distintos
       log.debug("\nCuenta los nombres de las personas que son distintos");
       jpql = "select count(distinct p.nombre) from Persona p";
       Long contador = (Long) em.createQuery(jpql).getSingleResult();
       //log.debug("numero de personas con nombre distinto: "+contador);
       
       //Concatena y convierte a mayusculas el nombre y apellido
       log.debug("\nConcatena y convierte a mayusculas el nombre y apellido");
       jpql = "select CONCAT(p.nombre, ' ', p.apellido) as Nombre from Persona p";
       nombres = em.createQuery(jpql).getResultList();
       for(String nombreCompleto: nombres){
           //log.debug(nombreCompleto);
       }
       
       //Obtiene el objeto persona con id igual al parametro proporcionado
       log.debug("\nObtiene el objeto persona con id igual al parametro proporcionado");
       int idPersona = 1;
       jpql = "select p from Persona p where p.idPersona = :id";
       q = em.createQuery(jpql);
       q.setParameter("id", idPersona);
       persona = (Persona) q.getSingleResult();
       //log.debug(persona);
       
       //Obtiene las personas que contengan una letra A en el nombre, sin importar si es mayuscula o minuscula
       log.debug("\nObtiene las personas que contengan una letra A en el nombre, sin importar si es mayuscula o minuscula");
       jpql = "select p from Persona p where upper(p.nombre) like upper(:parametro)";
       String parametroString = "%a%";
       q = em.createQuery(jpql);
       q.setParameter("parametro", parametroString);
       personas = q.getResultList();
       //mostrarPersonas(personas);
       
       //Uso between
       log.debug("\nUso between");
       jpql = "select p from Persona p where p.idPersona between 1 and 4";
       personas = em.createQuery(jpql).getResultList();
       //mostrarPersonas(personas);
       
       //Uso del ordenamiento
       log.debug("\nUso del ordenamiento");
       jpql = "select p from Persona p where p.idPersona > 1 order by p.nombre desc, p.apellido desc";
       personas = em.createQuery(jpql).getResultList();
       //mostrarPersonas(personas);
       
       //Uso de subquery
       log.debug("\nUso de subquery");
       jpql = "select p from Persona p where p.idPersona in (select min(p1.idPersona) from Persona p1)";
       personas = em.createQuery(jpql).getResultList();
       //mostrarPersonas(personas);
       
       //Uso de join con lazy loading
       log.debug("\nUso de join con lazy loading");
       jpql = "select u from Usuario u join u.persona p";
       usuarios = em.createQuery(jpql).getResultList();
       //mostrarUsuarios(usuarios);
       
       //Uso de left join con eager loading
       log.debug("\nUso de left join con eager loading");
       jpql = "select u from Usuario u left join fetch u.persona";
       usuarios = em.createQuery(jpql).getResultList();
       mostrarUsuarios(usuarios);
       
   }

    private static void mostrarPersonas(List<Persona> personas) {
        for(Persona p: personas){
            log.debug(p);
        }
    }

    private static void mostrarUsuarios(List<Usuario> usuarios) {
        for(Usuario u: usuarios){
            log.debug(u);
        }
    }
   
}
