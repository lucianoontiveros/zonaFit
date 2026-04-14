package zona_fit.datos;
import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        ResultSet rs;
        var SQL ="SELECT * FROM cliente ORDER BY id";
        try(Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL)) {
            rs = ps.executeQuery();
            while(rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public boolean buscarcliente(Cliente cliente) {

      ResultSet rs;
      var SQL = "SELECT * FROM cliente WHERE id = ?";
      try(Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(SQL) ){
          ps.setInt(1, cliente.getId());
          rs = ps.executeQuery();

          if(rs.next()){
              cliente.setNombre(rs.getString("nombre"));
              cliente.setApellido(rs.getString("apellido"));
              cliente.setMembresia(rs.getInt("membresia"));
              return true;
          }

      }catch(Exception e){
          System.out.println("Error en encontrar el usuario: " + e.getMessage());
      }
      return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        ResultSet rs;
        var sql = "INSERT INTO cliente(nombre, apellido, membresia) " + " VALUES (?, ?, ?)";
        try(Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            System.out.println("Cliente agregado");
            return true;
        }catch(Exception e){
            System.out.println("Error en encontrar el usuario: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        var SQL ="UPDATE cliente SET nombre = ?, apellido = ?, membresia = ?  WHERE id = ? ";
        try(Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(SQL)){
            ps.setInt(4, cliente.getId());
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println(cliente + " se encuentra modificado" );
                return true;
            } else {
                System.out.println("Cliente no encontrado");
            }


        }catch(Exception e){
            System.out.println("Error en encontrar el usuario: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {

        var SQL = "DELETE FROM cliente WHERE id= ? ";
        try(Connection con = Conexion.getConexion(); PreparedStatement ps =con.prepareStatement(SQL) ){
            ps.setInt(1, cliente.getId());

            int filas = ps.executeUpdate();
            if(filas >0){
                System.out.println("Cliente eliminado");
                return true;
            } else {
                System.out.println("Cliente no encontrado");
            }

        }catch(Exception e){
            System.out.println("Error en encontrar el usuario: " + e.getMessage());
        }
        return false;
    }

}
