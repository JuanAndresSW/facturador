
package datos;

import dominio.Cliente;
import java.sql.SQLException;
import java.util.List;


public interface iClienteDaoMySQL {
    public int insert(Cliente cliente) throws SQLException;
    public int update(Cliente cliente) throws SQLException;
    public int delete(Cliente cliente) throws SQLException;
    public List<Cliente> selectAll() throws SQLException;
    public Cliente selectById(Cliente cliente) throws SQLException;
    
}
