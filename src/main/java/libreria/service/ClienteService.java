/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.service;

import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteCRUD;

/**
 *
 * @author NALDO
 */
public class ClienteService {
    
    private ClienteCRUD cCRUD;
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public ClienteService() {
     cCRUD=new ClienteCRUD();
    }
    
    public void crearCliente(){
        try {
            Cliente c = new Cliente();
            System.out.println("Ingresar dni:");
            c.setDocumento(leer.nextLong());
            
            System.out.println("Ingresar nombre");
            c.setNombre(leer.next());
            
            System.out.println("ingresar apellido");
            c.setApellido(leer.next());
            
            System.out.println("ingresar numero telefonico");
            c.setTelefono(leer.next());
            cCRUD.create(c);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
