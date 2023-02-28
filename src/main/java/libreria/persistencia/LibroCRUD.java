/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Libro;

/**
 *
 * @author NALDO
 */
public class LibroCRUD {
    
    //creamos un entity manager
    private EntityManager em = Persistence.createEntityManagerFactory("libreriaPU").createEntityManager();
    
    public void create(Libro l){
        try {
          
            //Iniciamos una transacción con el método getTransaction().begin();
            em.getTransaction().begin();
           
            //Persistimos el objeto
            em.persist(l);
            /*
            Terminamos la transacción con el método commit. Commit en programación
significa confirmar un conjunto de cambios, en este caso persistir el objeto*/
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void editPrestamo(Integer id) throws Exception{
        try {
            //busco el libro a editar por su ID
             Libro lib = em.find(Libro.class, id);
            if (lib.getEjemplares() > lib.getEjemplaresPrestados()) {
                lib.setEjemplaresPrestados(lib.getEjemplaresPrestados() + 1);
                lib.setEjemplaresRestantes(lib.getEjemplaresRestantes() - 1);
            }else{
                throw new Exception("ERROR");
            }
           
             //iniciamos una transaccion
            em.getTransaction().begin();
            //actualizo el libro con el metodo marge();
            em.merge(lib);
            //confirmo el edit con el metodo commit();
            em.getTransaction().commit();
            
        } catch (Exception e) {
             throw e;
        }
    }
      public void editDevolucion(Integer id) throws Exception{
        try {
            //busco el libro a editar por su ID
             Libro lib = em.find(Libro.class, id);
            if (lib.getEjemplaresPrestados() != 0) {
                lib.setEjemplaresPrestados(lib.getEjemplaresPrestados() - 1);
                lib.setEjemplaresRestantes(lib.getEjemplaresRestantes() + 1);
            }else{
                throw new Exception("ERROR");
            }
          
             //iniciamos una transaccion
            em.getTransaction().begin();
            //actualizo el libro con el metodo marge();
            em.merge(lib);
            //confirmo el edit con el metodo commit();
            em.getTransaction().commit();
            
        } catch (Exception e) {
             throw e;
        }
    }
    
    public Libro findId(Integer id) throws Exception{
        try {
            //busco un libro por su id con el metodo fin()
            Libro lib = em.find(Libro.class, id);
            return lib;
        } catch (Exception e) {
            throw new Exception("no se encontro libro");
        }
    }
    
    public void remove(Integer id) throws Exception{
        try {
            //busco el libro a eliminar por su id con el metodo find();
             Libro lib = em.find(Libro.class, id);
             //inicion una transaccion
             em.getTransaction().begin();
             //lo elimino con el metodo remove();
             em.remove(lib);
             //confirmo con un commit
             em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("no se encontro libro");
        }
    }
    
    public  List<Libro> listarLibros(){
            try {
                List<Libro> libroList = em.createQuery("SELECT l FROM Libro l").getResultList();
              return libroList;
            } catch (Exception e) {
                throw e;
            }
    }
    
    public Libro findXNombreAutor(String name) throws Exception{
        
        try {
            Libro libro1 = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre = :nombre"
                    ).setParameter("nombre", name).getSingleResult();
            return libro1;
        } catch (Exception e) {
             throw new Exception("no se encontro libro");
        }
    }
    
    public Libro buscarPorTitulo(String tit) throws Exception{
        try {
         
           Libro li1= (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :tit"
            ).setParameter("tit", tit).getSingleResult();
           return li1;
        } catch (Exception e) {
              throw new Exception("no se encontro libro");
        }
    }
    
    public boolean libroExistente(String nombre){
        try {
           List<Libro>lista= listarLibros();
           boolean existe=false;
            for (Libro libro : lista) {
                if(libro.getTitulo().equals(nombre)){
                    existe=true;
                }
            }
            return existe;
        } catch (Exception e) {
            throw e;
        }
    }
}
