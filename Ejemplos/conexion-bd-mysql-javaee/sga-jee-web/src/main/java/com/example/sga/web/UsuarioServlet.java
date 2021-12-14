
package com.example.sga.web;

import com.example.sga.domain.Usuario;
import com.example.sga.service.UsuarioServiceLocal;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet{
    
    @Inject
    UsuarioServiceLocal usuarioService;
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        
        request.setAttribute("usuarios", usuarios);
        
        request.getRequestDispatcher("/listadoUsuarios.jsp").forward(request, response);
    }
}
