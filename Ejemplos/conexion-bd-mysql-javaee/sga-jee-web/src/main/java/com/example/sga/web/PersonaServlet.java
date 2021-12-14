
package com.example.sga.web;

import com.example.sga.domain.Persona;
import com.example.sga.service.PersonaServiceLocal;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PersonasServlet")
public class PersonaServlet extends HttpServlet{
    
    @Inject
    PersonaServiceLocal personaService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        List<Persona> personas = personaService.listarPersona();
        request.setAttribute("personas", personas);
        
        request.getRequestDispatcher("/listadoPersonas.jsp").forward(request, response);
    }
}
