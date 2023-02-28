

package libreria;

import java.util.Scanner;
import libreria.service.AutorService;
import libreria.service.ClienteService;
import libreria.service.EditorialService;
import libreria.service.LibroService;
import libreria.service.PrestamoService;



public class LibreriaJPA {

    public static void main(String[] args) {
       
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        
        try {
            System.out.println("------------LIBRERIA-----------");
            boolean flag=true;
         
             do {                
                System.out.println("Ingrese una opcion:\n"
                    + "1.Manejar Libro\n"
                    + "2.Manejar Autor\n"
                    + "3.Manejar Editorial\n"
                    + "4.Manejar Cliente\n"
                    + "5.Manejar Prestamo\n"
                    + "6.salir");
             int n = leer.nextInt();
             switch(n){
                 case 1:
                     LibroService sl = new LibroService();
                     sl.menu();
                     break;
                 case 2:
                     AutorService as = new AutorService();
                     as.menu();
                     break;
                 case 3:
                     EditorialService es = new EditorialService();
                     es.menu();
                     break;
                 case 4:
                     ClienteService cs = new ClienteService();
                     cs.crearCliente();
                     break;
                 case 5:
                     PrestamoService ps = new PrestamoService();
                     ps.menu();
                     break;
                 case 6:
                     flag=false;
             }
            } while (flag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}
