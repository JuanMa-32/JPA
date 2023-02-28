
package libreria.service;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialCRUD;

public class EditorialService {
  Scanner leer=new Scanner(System.in).useDelimiter("\n");
  private EditorialCRUD eCRUD;

    public EditorialService() {
        eCRUD=new EditorialCRUD();
    }

   public void menu() throws Exception{
        try {
            System.out.println("------------EDITORIAL---------------");
            boolean flag=true;
            
            do {                
             System.out.println("Ingrese que operacion quiere realizar:\n"
                       + "1.Guardar una editorial\n"
                       + "2.Modificar una editorial\n "
                       + "3.Remover una editorial\n"
                       + "4.Mostrar una editorial\n"
                       + "5.Mostrar todas las editoriales\n"
                       + "6.Volver al inicio");
               int opc = leer.nextInt();
                switch (opc) {
                    case 1:
                        Editorial a = new Editorial();
                        System.out.println("Ingresar nombre:");
                       String nombre=leer.next();
                       if(nombre.isEmpty()){
                           throw new Exception("Nombre vacio");
                       }
                        if(eCRUD.editorialExistente(nombre)){
                            throw new Exception("Editorial EXISTENTE");
                        }else{
                            a.setNombre(nombre);
                        }
                        a.setAlta(true);
                        eCRUD.create(a);
                        break;
                    case 2:
                        System.out.println("ingresar ID de la editorial que modificara");
                         int id = leer.nextInt();
                         System.out.println("Ingresar el nuevo Nombre");
                         String nombre2 = leer.next();
                         eCRUD.edit(id, nombre2);
                         break;
                    case 3:
                         System.out.println("ingresar ID de la editorial que eliminara");
                         int idRemove = leer.nextInt();
                         eCRUD.remove(idRemove);
                         break;
                    case 4:
                         System.out.println("ingresar ID de la editorial que quiere mostrar");
                         int idFind = leer.nextInt();
                         Editorial editorial=eCRUD.findId(idFind);
                         System.out.println(editorial);
                         break;
                    case 5:
                         List<Editorial> listaEdi= eCRUD.listarEditorial();
                       for (Object aux : listaEdi) {
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
