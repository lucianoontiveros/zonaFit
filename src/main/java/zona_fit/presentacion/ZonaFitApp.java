package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;
public class ZonaFitApp {

    public static void main(String[] args){
        ZonaFitApp();
    }

    private static void ZonaFitApp(){
        var salir = false;
        Scanner consola = new Scanner(System.in);
        IClienteDAO clienteDAO = new ClienteDAO();

        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDAO);
            }catch(Exception e){

            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.println("""
                            -------------\s 
                            """);
        System.out.println(
                """
                     *** Zona fit (Gym) ***
                     1. Listar clentes 
                     2. Buscar cliente
                     3. Agregar cliente
                     4. Modificar cliente
                     5. Eliminar cliente
                     6. Salir
                     
                     Elije una opción:\s
                        """
        );
        System.out.println("""
                            ----------------------\s 
                            """);
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, Integer opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("--- Lista clientes ---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);

            }
            case 2 -> {
                System.out.println("--- Buscar cliente ---");
                System.out.println("Favor de ingresar el ID cliente");
                var id = Integer.parseInt(consola.nextLine());
                System.out.println("--- Buscar cliente ---");
                var cliente = new Cliente(id);
                var encontrado = clienteDAO.buscarcliente(cliente);
                System.out.println("----------------------");

                if(encontrado){
                    System.out.println("El cliente buscado es: ");
                    System.out.println(cliente);

                } else  {
                    System.out.println("El cliente no existe");
                }

            }
            case 3 -> {
                System.out.println("---Agregar nuevo cliente---");
                System.out.println("Favor de ingresar el nombre del cliente");
                var nombre = consola.nextLine();
                System.out.println("Favor de ingresar el apellido del cliente");
                var apellido = consola.nextLine();
                System.out.println("Favor de ingresar el número de membresia");
                var membresia = Integer.parseInt(consola.nextLine());
                System.out.println("-----------------------------");

                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado  = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println("El cliente fue agregado:");
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Apellido: " + cliente.getApellido());
                    System.out.println("Membresia: " + cliente.getMembresia());
                }
            }

            case 4 -> {
                System.out.println("------Modificar cliente------");
                System.out.println("Favor de ingresar el Id del cliente");
                var id = Integer.parseInt(consola.nextLine());
                System.out.println("Favor de ingresar el nombre del cliente");
                var nombre = consola.nextLine();
                System.out.println("Favor de ingresar el apellido del cliente");
                var apellido = consola.nextLine();
                System.out.println("Favor de ingresar el número de membresia");
                var membresia = Integer.parseInt(consola.nextLine());
                System.out.println("-----------------------------");
                var cliente = new Cliente(id, nombre, apellido, membresia);
                var modificado  = clienteDAO.modificarCliente(cliente);
                if(modificado){
                    System.out.println("El cliente fue modificado");
                }
            }

            case 5 -> {
                System.out.println("------Eliminar cliente------");
                System.out.println("Favor de ingresar el Id del cliente");
                var id = Integer.parseInt(consola.nextLine());
                System.out.println("----------------------------");
                var cliente = new Cliente(id);
                var Eliminado = clienteDAO.eliminarCliente(cliente);
            }
        }

        return salir;
    }
}
