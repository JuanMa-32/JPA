/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;

/**
 *
 * @author NALDO
 */
public class ClienteCRUD {
    
    private EntityManager em = Persistence.createEntityManagerFactory("libreriaPU").createEntityManager();
    
    public void create(Cliente cliente){
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Cliente findId(int id){
        try {
            Cliente c = em.find(Cliente.class, id);
            return c;
        } catch (Exception e) {
            throw e;
        }
    }
}
