
package libreria.service;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorCRUD;

public class AutorService {

    private AutorCRUD aCRUD;
    Scanner leer=new Scanner(System.in).useDelimiter("\n");

    public AutorService() {
    aCRUD=new AutorCRUD();
    }
    
    public void menu() throws Exception{
        try {
            System.out.println("------------AUTOR---------------");
            boolean flag=true;
            
            do {                
             System.out.println("Ingrese que operacion quiere realizar:\n"
                       + "1.Guardar un autor\n"
                       + "2.Modificar un autor\n "
                       + "3.Remover un autor\n"
                       + "4.Mostrar un autor\n"
                       + "5.Mostrar todos los autores\n"
                       + "6.Volver al inicio");
               int opc = leer.nextInt();
                switch (opc) {
                    case 1:
                        Autor a = new Autor();
                        System.out.println("Ingresar nombre:");
                        String nombreA=leer.next();
                        if(nombreA.isEmpty()){
                            throw new Exception("nombre VACIO");
                        }
                        if(aCRUD.autorExistente(nombreA)){
                            throw new Exception("Autor EXISTENTE");
                        }else{
                            a.setNombre(nombreA);
                        }
                        a.setAlta(true);
                        aCRUD.create(a);
                        break;
                    case 2:
                        System.out.println("ingresar ID del autor que modificara");
                         int id = leer.nextInt();
                         System.out.println("Ingresar el nuevo Nombre");
                         String nombre = leer.next();
                         aCRUD.edit(id, nombre);
                         break;
                    case 3:
                         System.out.println("ingresar ID del autor que eliminara");
                         int idRemove = leer.nextInt();
                         aCRUD.remove(idRemove);
                         break;
                    case 4:
                         System.out.println("ingresar ID del autor que quiere mostrar");
                         int idFind = leer.nextInt();
                         Autor autor= aCRUD.find(idFind);
                         System.out.println(autor);
                         break;
                    case 5:
                         List<Autor> listaAutor=aCRUD.listarAutor();
                       for (Object aux : listaAutor) {
                           System.out.println(aux);
                           System.out.println("");
                       }
                       break;
                    case 6:
                        System.out.println("saliendo...");
                        flag=false;
                
                }
               
            } while (flag);
        } catch (Exception e) {
            throw e;
        }
    }
}
