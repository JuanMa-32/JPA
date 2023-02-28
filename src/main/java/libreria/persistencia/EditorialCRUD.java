/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

/**
 *
 * @author NALDO
 */
public class EditorialCRUD {
    
    private EntityManager em = Persistence.createEntityManagerFactory("libreriaPU").createEntityManager();
    
    public void create(Editorial e1){
        try {
          em.getTransaction().begin();
          em.persist(e1);
          em.getTransaction().commit();
        }catch (Exception e) {
            throw e;
        }
    }
    
    public void edit(Integer id,String nombre){
        try {
            //busco la editorial
            Editorial edi = em.find(Editorial.class, id);
            edi.setNombre(nombre);
            //edito la editorial en la base de datos 
            em.getTransaction().begin();
            em.merge(edi);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void remove(Integer id ){
        try {
            Editorial edi = em.find(Editorial.class, id);
            
            em.getTransaction();
            em.remove(edi);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Editorial findId(Integer id){
        try {
            Editorial edi = em.find(Editorial.class, id);
            return edi;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Editorial> listarEditorial(){
        try {
            List<Editorial> ediList= em.createQuery("SELECT e FROM Editorial e").getResultList();
            return ediList;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public boolean editorialExistente(String nombre){
        try {
            boolean existe=false;
             List<Editorial> lista=listarEditorial();
             for (Editorial editorial : lista) {
                if(editorial.getNombre().equals(nombre)){
                    existe=true;
                }
            }
             return existe;
        } catch (Exception e) {
            throw e;
        }
    }
}
