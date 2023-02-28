/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.ClienteCRUD;
import libreria.persistencia.LibroCRUD;
import libreria.persistencia.PrestamoCRUD;

/**
 *
 * @author NALDO
 */
public class PrestamoService {
    
    private PrestamoCRUD pCRUD;
    Scanner leer=new Scanner(System.in).useDelimiter("\n");

    public PrestamoService() {
      pCRUD= new PrestamoCRUD();
    }
    
    public void menu() throws Exception{
        try {
            System.out.println("-------PRESTAMOS------");
            boolean flag=true;
            do {                
                System.out.println("Ingrese que operacion realizara\n"
                        + "1.Registrar un prestamo\n"
                        + "2.Devolover un libro\n"
                        + "3.buscar todos los prestamos de un cliente\n"
                        + "4.volver al inicio");
                int opc = leer.nextInt();
                
                switch(opc){
                    case 1:
                        //de esta forma ASIGNO LA FECHA DEL MOMENTO EXACTO CUANDO SE EJECUTA EL PRESTAMO
                        Date fechaAct = new Date();
                        Prestamo p=new Prestamo();
                        p.setFechaPrestamo(fechaAct);
                        //PIDO LA FECHA DE DEVOLUCION CON UN STRING
                        System.out.println("ingrese fecha de devolucion FORMATO: año,mes,dia");
                        int anio = leer.nextInt();
                        int mes=leer.nextInt();
                        int dia=leer.nextInt();
                        Date fechaDev = new Date(anio, mes, dia);
                        p.setFechaDevolucion(fechaDev);
                        //creo uyn libroCRUD para traer los metodos y poder mostrar los libros y asignarlo al libro
                        LibroCRUD lc = new LibroCRUD();
                          List<Libro> listLib=lc.listarLibros();
                       for (Object aux3 : listLib) {
                           System.out.println(aux3);
                           System.out.println("");
                       }
                        System.out.println("Ingrese isbn del libro que quiere ");
                        p.setLibro(lc.findId(leer.nextInt()));
                       
                        //CREO UN CLIENTECRUD PARA PODER TRAER EL CLIENTE QUE QUIERE EL PRESTAMO
                        ClienteCRUD cc = new ClienteCRUD();
                        System.out.println("ingrese el id del cliente");
                        p.setCliente(cc.findId(leer.nextInt()));
                        pCRUD.create(p);
                        break;
                    case 2:
                        System.out.println("Ingresar el id del prestamo");
                        pCRUD.devolverLibro(leer.nextInt());
                        break;
                    case 3:
                        System.out.println("ingrese el id del cliente para ver todos sus prestamos");
                        List<Prestamo>prest=pCRUD.prestamosDeUnCliente(leer.nextInt());
                        for (Object aux : prest) {
                            System.out.println(aux);
                            System.out.println("");
                        }
                        break;
                    case 4:
                        System.out.println("volviendo...");
                        flag=false;
                }
            } while (flag);
        } catch (Exception e) {
            throw e;
        }
    }
}
