
package libreria.service;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.AutorCRUD;
import libreria.persistencia.EditorialCRUD;
import libreria.persistencia.LibroCRUD;

public class LibroService {
    
   private LibroCRUD lCrud;
   Scanner leer=new Scanner(System.in).useDelimiter("\n");

    public LibroService() {
        lCrud=new LibroCRUD();
    }
    
   
   public void menu()throws Exception{
       try {
           System.out.println("--------------LIBRO--------------");
           boolean flag = true;
           do {               
               System.out.println("Ingrese que operacion quiere realizar:\n"
                       + "1.Guardar un libro\n"
                       + "2.Remover un libro\n"
                       + "3.Mostrar un libro\n"
                       + "4.Mostrar todos los libros\n"
                       + "5.Mostrar un libro por el nombre de su autor\n"
                       + "6.Mostrar un libro por su titulo\n"
                       + "7.Volver al inicio");
               int opc = leer.nextInt();
               
               switch(opc){
                   case 1:
                       Libro l1 = new Libro();
                       System.out.println("Titulo: ");
                       String titulo=leer.next();
                      
                       if (titulo.isEmpty() || titulo.equals("")){
                           throw new Exception("TITULO VACIO");
                       }
                       if(lCrud.libroExistente(titulo)){
                           throw new Exception("lIBRO EXISTENTE");
                       }else {
                           l1.setTitulo(titulo);
                       }

                       System.out.println("año: ");
                       l1.setAnio(leer.nextInt());

                       System.out.println("ejemplares: ");
                       l1.setEjemplares(leer.nextInt());

                       System.out.println("ejemplares prestados:");
                       l1.setEjemplaresPrestados(leer.nextInt());

                       System.out.println("ejmplares restantes:");
                       l1.setEjemplaresRestantes(leer.nextInt());
 
                       l1.setAlta(true);
                       
                      
                       AutorCRUD aCRUD = new AutorCRUD();

                       List<Autor> listaAutor=aCRUD.listarAutor();
                       for (Object aux : listaAutor) {
                           System.out.println(aux);
                           System.out.println("");
                       }
                        System.out.println("Ingrese el ID del autor");
                       int id = leer.nextInt();
                       l1.setAutor(aCRUD.find(id));
                     
                       
                      
                       EditorialCRUD eCRUD = new EditorialCRUD();
                       List<Editorial> listEditorial = eCRUD.listarEditorial();
                       for (Object aux1 : listEditorial) {
                           System.out.println(aux1);
                           System.out.println("");
                       }
                        System.out.println("Ingrese el ID de la editorial");
                        int id2 = leer.nextInt();
                       l1.setEditorial(eCRUD.findId(id2));
                      
                       lCrud.create(l1);
                       
                       break; 
                 
                   case 2:
                       System.out.println("Ingrese el id del libro que removera");
                       int idRemove = leer.nextInt();
                       lCrud.remove(idRemove);
                       break;
                   case 3:
                       System.out.println("Ingrese el isbn del libro que quiere mostrar");
                       int idFind = leer.nextInt();
                       Libro libro = lCrud.findId(idFind);
                       System.out.println(libro);
                       break;
                   case 4:
                       List<Libro> listLib=lCrud.listarLibros();
                       for (Object aux3 : listLib) {
                           System.out.println(aux3);
                           System.out.println("");
                       }
                       break;
                   case 5:
                       System.out.println("Ingrese el nombre del autor del libro que quiere mostrar");
                       String nAutor=leer.next();
                       Libro lib1=lCrud.findXNombreAutor(nAutor);
                       System.out.println(lib1);
                       break;
                   case 6:
                       System.out.println("Ingresar el titulo del libro a buscar");
                       String ti=leer.next();
                       Libro lTitulo=lCrud.buscarPorTitulo(ti);
                       System.out.println(lTitulo);
                       break;
                   case 7:
                       System.out.println("volviendo al inicio...");
                       flag=false;
               }
           } while (flag);
       } catch (Exception e) {
           throw e;
       }
   }
   
}
