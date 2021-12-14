
package com.example.sga.datos;

import com.example.sga.domain.Usuario;
import java.util.List;


public interface UsuarioDao {
    public List<Usuario> findAllUsuario();
    public Usuario findUsuarioById(Usuario user);
    public Usuario findUsuarioByUsername(Usuario user);
    public void insertUsuario(Usuario user);
    public void updateUsuario(Usuario user);
    public void deleteUsuario(Usuario user);
}
