/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Prestamo;


/**
 *
 * @author NALDO
 */
public class PrestamoCRUD {
    private EntityManager em = Persistence.createEntityManagerFactory("libreriaPU").createEntityManager();
    
    public void create(Prestamo p1) throws Exception{
        try {
            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
              LibroCRUD lc = new LibroCRUD();
            lc.editPrestamo(p1.getLibro().getIsbn());
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void devolverLibro(Integer id) throws Exception{
        try {
            //BUSCO EL PRESTAMO POR SU ID
            Prestamo p1 = em.find(Prestamo.class, id);
            
            //creo un libro crud para llamar al metodo donde devuelve un libro
            LibroCRUD lc = new LibroCRUD();
            lc.editDevolucion(p1.getLibro().getIsbn());
            
            //elimino el prestamo 
            em.getTransaction().begin();
            em.remove(p1);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    public List<Prestamo> prestamosDeUnCliente(Integer id){
        try {
           List<Prestamo>lista= em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.id=:id").setParameter("id", id).getResultList();
           return lista;
        } catch (Exception e) {
            throw e;
        }
    }
}
