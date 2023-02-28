
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorCRUD {
    
    private EntityManager em = Persistence.createEntityManagerFactory("libreriaPU").createEntityManager();
    
    public void create(Autor a){
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void edit(Integer id,String nombre){
        try {
            Autor au = em.find(Autor.class, id);
            au.setNombre(nombre);
            
            em.getTransaction().begin();
            em.merge(au);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void remove(Integer id){
        try {
              Autor au = em.find(Autor.class, id);
              em.getTransaction().begin();
              em.remove(au);
              em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Autor find(Integer id){
        try {
              Autor au = em.find(Autor.class, id);
              return au;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Autor> listarAutor(){
        try {
            List<Autor> autorList = em.createQuery("SELECT a FROM Autor a").getResultList();
            return autorList;
        } catch (Exception e) {
            throw e;
        }
    }
    
     public boolean autorExistente(String nombre){
        try {
            boolean existe=false;
             List<Autor> lista=listarAutor();
             for (Autor aux : lista) {
                if(aux.getNombre().equals(nombre)){
                    existe=true;
                }
            }
             return existe;
        } catch (Exception e) {
            throw e;
        }
    }
}
