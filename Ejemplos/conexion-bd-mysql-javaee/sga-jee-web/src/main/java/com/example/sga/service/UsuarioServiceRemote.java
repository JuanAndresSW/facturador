
package com.example.sga.service;

import com.example.sga.domain.Usuario;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface UsuarioServiceRemote {
    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarioPorId(Usuario user);
    public Usuario buscarUsuarioPorUsername(Usuario user);
    public void registarUsuario(Usuario user);
    public void modificarUsuario(Usuario user);
    public void eliminarUsuario(Usuario user);
}
