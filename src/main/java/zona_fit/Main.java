package zona_fit;


import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IClienteDAO cliente = new ClienteDAO();
        var clienteMod = new Cliente(38);
        cliente.eliminarCliente(clienteMod);
    }

}